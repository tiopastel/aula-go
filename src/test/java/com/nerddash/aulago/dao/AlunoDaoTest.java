package com.nerddash.aulago.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.weld.executor.DaemonThreadFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.nerddash.aulago.db.ConnectionFactory;
import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.model.Pessoa.Sexo;

import junit.framework.Assert;


public class AlunoDaoTest {
	
	@Mock
    protected static EntityManager em;
	
	private AlunoDao dao;
	private Aluno aluno;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		em = Mockito.mock(EntityManager.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
		dao = new AlunoDao(em);
		
//		Operation operation = Operations.sequenceOf(OperacoesComunsBD.LIMPA_TUDO, OperacoesComunsBD.RESET_AUTOINCREMENT_H2);
		aluno = new Aluno();
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setCpf(153);
		aluno.setSenha("100xxx");
		aluno.setSexo(Sexo.MASCULINO);
		aluno.setEmail("flavio@email.com");
		
		
	
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public final void testInsert() {
		Assert.assertNotNull(dao.insert(aluno));
		
	}

	@Test
	public final void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public final void testListAll() {
		fail("Not yet implemented");
	}

}
