package com.nerddash.aulago.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.dao.AlunoDao;
import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class AlunoControllerTest extends AbstractRepositoryTest{

	private AlunoDao dao;
	private MockResult result;
	private MockValidator validator;
	private Aluno aluno;
	private AlunoController alunoController;
	private Long[] id = new Long[10];


	@Before
	public void setUp() throws Exception {
		
		this.entityClass = Aluno.class;
		
		result = new MockResult();
		validator = new MockValidator();		
		dao = new AlunoDao(em);	
		
		alunoController = new AlunoController(result, dao, validator);	
		
		for (int i = 0; i < 10; i++) {

			aluno = new Aluno();
			aluno.setNome("Flávio Arantes");
			aluno.setNivel(Nivel.SUPERIOR);
			aluno.setSenha("100xxx");
			aluno.setEmail("flavio" + i + "@email.com");
			aluno.setCurso("Curso");
			dao.insert(aluno);
			id[i] = (long) i+1;
		}	
		
	}
	
	@Test
	public void deveInserirUmAluno() {
		
		aluno = new Aluno();
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio" + 11 + "@email.com");
		aluno.setCurso("Curso");
		
		assertEquals(aluno, alunoController.insere(aluno));
		
	}


	@Test
	public void deveDevolverUmAluno() {
		aluno = dao.get(id[0]);
		assertEquals(aluno, alunoController.get(aluno));
		
	}
	
	@Test
	public void deveRemoverUmAluno() {		
		assertTrue(alunoController.delete(aluno));		
	}
	
	@Test
	public void deveAtualizarAluno() {		

		aluno.setNome("Victor Junior");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("xxx300");
		aluno.setEmail("victor" + 31 + "@email.com");
		aluno.setCurso("ADS");
		
		assertEquals(aluno, alunoController.atualiza(aluno));
		
	}

}
