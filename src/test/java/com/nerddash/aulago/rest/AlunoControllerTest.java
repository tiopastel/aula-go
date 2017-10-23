package com.nerddash.aulago.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.nerddash.aulago.dao.AlunoDao;
import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class AlunoControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private AlunoDao dao;
	private MockResult result;
	private MockValidator validator;
	private Aluno aluno;
	private AlunoController alunoController;

	@Before
	public void setUp() throws Exception {

		result = new MockResult();
		validator = new MockValidator();
		dao = mock(AlunoDao.class);

		alunoController = new AlunoController(validator, result, dao);

		aluno = new Aluno();
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio@email.com");
		aluno.setCurso("Curso");

	}

	@Test
	public void deveInserirUmAluno() throws Exception {

		when(dao.insert(aluno)).thenReturn(aluno);
		assertEquals(aluno, alunoController.insere(aluno));
		verify(dao, times(1)).insert(aluno);

	}

	@Test
	public void deveDevolverUmAluno() {

		when(dao.get(anyLong())).thenReturn(aluno);
		assertEquals(aluno, alunoController.get(aluno));
		verify(dao, times(1)).get(anyLong());

	}

	@Test
	public void deveRemoverUmAluno() {

		when(dao.delete(aluno)).thenReturn(true);
		when(dao.get(anyLong())).thenReturn(aluno);
		assertTrue(alunoController.delete(aluno));
		verify(dao, times(1)).get(anyLong());
		verify(dao, times(1)).delete(aluno);

	}

	@Test
	public void deveAtualizarAluno() {

		aluno.setNome("Victor Junior");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("xxx300");
		aluno.setEmail("victor" + 31 + "@email.com");
		aluno.setCurso("ADS");

		when(dao.update(aluno)).thenReturn(aluno);
		assertEquals(aluno, alunoController.atualiza(aluno));
		verify(dao, times(1)).update(aluno);

	}

}
