package com.nerddash.aulago.dao;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Aula;
import com.nerddash.aulago.model.Busca;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

public class BuscaDaoTest extends AbstractRepositoryTest{

	private ArrayList<Busca> buscas;
	private BuscaDao dao;
	private AlunoDao daoAluno;
	private AulaDao daoAula;
	private Aluno aluno;
	private Aula aula;


	@Before
	public void setUp() throws Exception {
		
		this.entityObject = new Busca();
		
		dao = new BuscaDao(em);
		daoAluno = new AlunoDao(em);
		daoAula = new AulaDao(em);
		
		buscas = new ArrayList<Busca>();
		
		aluno = new Aluno();
		
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio@email.com");
		aluno.setCurso("Curso");
		
		daoAluno.insert(aluno);
		
		
		
		aula = new Aula();
		
		aula.setMateria("Matemática Discreta");
		aula.setNivel(Nivel.SUPERIOR);
		
		daoAula.insert(aula);
		
		em.refresh(aluno);
		em.refresh(aula);
		
		for (int i = 0; i < 10; i++) {
			Busca busca = new Busca();
			busca.setAluno(aluno);
			busca.setAula(aula);
			busca.setDataFinal(LocalDate.now().plusDays(i+1));
			buscas.add(busca);
		}
	}
	
	
	@After
	public void tearDown() throws Exception {		
		super.tearDown();
		resetTable(aluno);
		resetTable(aula);
	}


	@Test
	public void deveInserirListarTodosBuscasDepoisRemover2Buscas() {

		for (int j = 0; j < buscas.size(); j++) {
			dao.insert(buscas.get(j));
			assertThat("Não pode encontrar o registro " + j, buscas.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(buscas.get(4));
		dao.delete(buscas.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}
	
	@Test
	public void deveInserirListarTodosBuscasDepoisRemover2BuscasDeNovo() {

		for (int j = 0; j < buscas.size(); j++) {
			dao.insert(buscas.get(j));
			assertThat("Não pode encontrar o registro " + j, buscas.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(buscas.get(4));
		dao.delete(buscas.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}

}
