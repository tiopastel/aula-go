package com.nerddash.aulago.dao;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.model.Pessoa.Sexo;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

public class AlunoDaoTest extends AbstractRepositoryTest {

	private Aluno aluno;
	private AlunoDao dao;


	@Before
	public void setUp() throws Exception {

		dao = new AlunoDao(em);

		aluno = new Aluno();

		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setCpf(153);
		aluno.setSenha("100xxx");
		aluno.setSexo(Sexo.MASCULINO);
		aluno.setEmail("flavio@email.com");
		aluno.setDataNascimento(new Date());
		aluno.setCurso("Curso");
		aluno.setEndereco("Adress");



	}

	@After
	public void tearDown() throws Exception {
		Query query = em.createNativeQuery("DELETE FROM ALUNOS; ALTER TABLE ALUNOS ALTER COLUMN id RESTART WITH 1;");
		query.executeUpdate();
	}

	@Test
	public final void deveInserirAluno() {
		dao.insert(aluno);
		em.refresh(aluno);
		assertTrue(aluno.getId() == 1);
	}
	
	@Test
	public final void deveInserirVariosAlunos() {
		dao.insert(aluno);
		em.refresh(aluno);
		assertTrue(aluno.getId() == 2);
	}



}
