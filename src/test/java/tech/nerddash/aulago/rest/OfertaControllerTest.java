package tech.nerddash.aulago.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import tech.nerddash.aulago.dao.OfertaDao;
import tech.nerddash.aulago.model.Aula;
import tech.nerddash.aulago.model.Oferta;
import tech.nerddash.aulago.model.Professor;
import tech.nerddash.aulago.rest.OfertaController;

public class OfertaControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private OfertaDao dao;
	private MockResult result;
	private MockValidator validator;
	private Professor professor;
	private Oferta oferta;
	private OfertaController ofertaController;
	private Aula aula;

	@Before
	public void setUp() throws Exception {

		result = new MockResult();
		validator = new MockValidator();
		aula = mock(Aula.class);
		professor = mock(Professor.class);
		dao = mock(OfertaDao.class);

		ofertaController = new OfertaController(validator, result, dao);

		oferta = new Oferta();
		oferta.setProfessor(professor);
		oferta.setAula(aula);
		oferta.setDataFinal(LocalDate.now().plusDays(30));
		oferta.setPreco(35.00);
		oferta.setHoraInicio(LocalTime.of(13, 0));
		oferta.setHoraTermino(LocalTime.of(17, 0));		

	}

	@Test
	public void deveInserirUmOferta() throws Exception {
		
		when(dao.insert(oferta)).thenReturn(oferta);
		assertEquals(oferta, ofertaController.insere(oferta));
		verify(dao, times(1)).insert(oferta);

	}

	@Test
	public void deveDevolverUmOferta() {

		when(dao.get(anyLong())).thenReturn(oferta);
		assertEquals(oferta, ofertaController.get(oferta));
		verify(dao, times(1)).get(anyLong());

	}

	@Test
	public void deveRemoverUmOferta() {

		when(dao.delete(oferta)).thenReturn(true);
		when(dao.get(anyLong())).thenReturn(oferta);
		assertTrue(ofertaController.delete(oferta));
		verify(dao, times(1)).get(anyLong());
		verify(dao, times(1)).delete(oferta);

	}

	@Test
	public void deveAtualizarOferta() {

		oferta.setDataFinal(LocalDate.now().plusDays(60));
		oferta.setPreco(40.00);
		oferta.setHoraInicio(LocalTime.of(8, 0));
		oferta.setHoraTermino(LocalTime.of(11, 0));		
		
		when(dao.update(oferta)).thenReturn(oferta);
		assertEquals(oferta, ofertaController.atualiza(oferta));
		verify(dao, times(1)).update(oferta);

	}

}
