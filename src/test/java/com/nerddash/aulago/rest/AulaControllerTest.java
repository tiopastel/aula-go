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

import com.nerddash.aulago.dao.AulaDao;
import com.nerddash.aulago.model.Aula;
import com.nerddash.aulago.model.Nivel;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;

public class AulaControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private AulaDao dao;
	private MockResult result;
	private MockValidator validator;
	private Aula aula;
	private AulaController aulaController;

	@Before
	public void setUp() throws Exception {

		result = new MockResult();
		validator = new MockValidator();
		dao = mock(AulaDao.class);

		aulaController = new AulaController(validator, result, dao);

		aula = new Aula();
		aula.setMateria("Matemática Discreta");
		aula.setNivel(Nivel.SUPERIOR);


	}

	@Test
	public void deveInserirUmAula() throws Exception {

		when(dao.insert(aula)).thenReturn(aula);
		assertEquals(aula, aulaController.insere(aula));
		verify(dao, times(1)).insert(aula);

	}

	@Test
	public void deveDevolverUmAula() {

		when(dao.get(anyLong())).thenReturn(aula);
		assertEquals(aula, aulaController.get(aula));
		verify(dao, times(1)).get(anyLong());

	}

	@Test
	public void deveRemoverUmAula() {

		when(dao.delete(aula)).thenReturn(true);
		when(dao.get(anyLong())).thenReturn(aula);
		assertTrue(aulaController.delete(aula));
		verify(dao, times(1)).get(anyLong());
		verify(dao, times(1)).delete(aula);

	}

	@Test
	public void deveAtualizarAula() {


		aula.setMateria("Matemática Simples");
		aula.setNivel(Nivel.BASICO);

		when(dao.update(aula)).thenReturn(aula);
		assertEquals(aula, aulaController.atualiza(aula));
		verify(dao, times(1)).update(aula);

	}

}
