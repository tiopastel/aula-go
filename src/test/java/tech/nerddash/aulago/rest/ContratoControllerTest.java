package tech.nerddash.aulago.rest;

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

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import tech.nerddash.aulago.dao.ContratoDao;
import tech.nerddash.aulago.model.Aluno;
import tech.nerddash.aulago.model.Contrato;
import tech.nerddash.aulago.model.Oferta;
import tech.nerddash.aulago.rest.ContratoController;

public class ContratoControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private ContratoDao dao;
	private MockResult result;
	private MockValidator validator;
	private Aluno aluno;
	private Contrato contrato;
	private ContratoController contratoController;
	private Oferta oferta;

	@Before
	public void setUp() throws Exception {

		result = new MockResult();
		validator = new MockValidator();
		oferta = mock(Oferta.class);
		aluno = mock(Aluno.class);
		dao = mock(ContratoDao.class);

		contratoController = new ContratoController(validator, result, dao);

		contrato = new Contrato();
		contrato.setAluno(aluno);
		contrato.setOferta(oferta);
		contrato.setAvaliacao(3);
	

	}

	@Test
	public void deveInserirUmContrato() throws Exception {
		
		when(dao.insert(contrato)).thenReturn(contrato);
		assertEquals(contrato, contratoController.insere(contrato));
		verify(dao, times(1)).insert(contrato);

	}

	@Test
	public void deveDevolverUmContrato() {

		when(dao.get(anyLong())).thenReturn(contrato);
		assertEquals(contrato, contratoController.get(contrato));
		verify(dao, times(1)).get(anyLong());

	}

	@Test
	public void deveRemoverUmContrato() {

		when(dao.delete(contrato)).thenReturn(true);
		when(dao.get(anyLong())).thenReturn(contrato);
		assertTrue(contratoController.delete(contrato));
		verify(dao, times(1)).get(anyLong());
		verify(dao, times(1)).delete(contrato);

	}

	@Test
	public void deveAtualizarContrato() {

		contrato.setAvaliacao(5);	
		
		when(dao.update(contrato)).thenReturn(contrato);
		assertEquals(contrato, contratoController.atualiza(contrato));
		verify(dao, times(1)).update(contrato);

	}

}
