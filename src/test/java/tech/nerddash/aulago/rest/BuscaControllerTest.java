package tech.nerddash.aulago.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import tech.nerddash.aulago.dao.BuscaDao;
import tech.nerddash.aulago.model.Aluno;
import tech.nerddash.aulago.model.Aula;
import tech.nerddash.aulago.model.Busca;
import tech.nerddash.aulago.rest.BuscaController;

public class BuscaControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private BuscaDao dao;
	private MockResult result;
	private MockValidator validator;
	private Aluno aluno;
	private Busca busca;
	private BuscaController buscaController;
	private Aula aula;

	@Before
	public void setUp() throws Exception {

		result = new MockResult();
		validator = new MockValidator();
		aula = mock(Aula.class);
		aluno = mock(Aluno.class);
		dao = mock(BuscaDao.class);

		buscaController = new BuscaController(validator, result, dao);

		busca = new Busca();
		busca.setAluno(aluno);
		busca.setAula(aula);
		busca.setDataFinal(LocalDate.now().plusDays(30));
	

	}

	@Test
	public void deveInserirUmBusca() throws Exception {
		
		when(dao.insert(busca)).thenReturn(busca);
		assertEquals(busca, buscaController.insere(busca));
		verify(dao, times(1)).insert(busca);

	}

	@Test
	public void deveDevolverUmBusca() {

		when(dao.get(anyLong())).thenReturn(busca);
		assertEquals(busca, buscaController.get(busca));
		verify(dao, times(1)).get(anyLong());

	}

	@Test
	public void deveRemoverUmBusca() {

		when(dao.delete(busca)).thenReturn(true);
		when(dao.get(anyLong())).thenReturn(busca);
		assertTrue(buscaController.delete(busca));
		verify(dao, times(1)).get(anyLong());
		verify(dao, times(1)).delete(busca);

	}

	@Test
	public void deveAtualizarBusca() {

		busca.setDataFinal(LocalDate.now().plusDays(60));	
		
		when(dao.update(busca)).thenReturn(busca);
		assertEquals(busca, buscaController.atualiza(busca));
		verify(dao, times(1)).update(busca);

	}

}
