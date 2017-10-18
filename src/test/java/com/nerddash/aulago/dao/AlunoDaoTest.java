package com.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

public class AlunoDaoTest extends AbstractRepositoryTest {

	private List<Aluno> alunos;
	private AlunoDao dao;

	@Before
	public void setUp() throws Exception {

		dao = new AlunoDao(em);

		alunos = new ArrayList<Aluno>();

		for (int i = 0; i < 10; i++) {

			Aluno aluno = new Aluno();
			aluno.setNome("Flávio Arantes");
			aluno.setNivel(Nivel.SUPERIOR);
			aluno.setSenha("100xxx");
			aluno.setEmail("flavio" + i + "@email.com");
			aluno.setCurso("Curso");
			alunos.add(aluno);
		}

	}

	@After
	public void tearDown() throws Exception {
		em.clear();
		Query query = em.createNativeQuery("DELETE FROM ALUNOS; ALTER TABLE ALUNOS ALTER COLUMN id RESTART WITH 1;");
		query.executeUpdate();
	}


	@Test
	public final void deveInserirListarTodosAlunosDepoisRemover2Alunos() {

		for (int j = 0; j < alunos.size(); j++) {
			dao.insert(alunos.get(j));
			assertThat("Não pode encontrar o registro "+j, alunos.get(j).getId(), equalTo((long) 1 + j));
		}		
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));
		
		dao.delete(alunos.get(4));
		dao.delete(alunos.get(7));
		
		assertThat("Houve algum problema ao excluir as tuplas",dao.listAll().size(), equalTo(8));

	}

}
