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
import tech.nerddash.aulago.dao.ProfessorDao;
import tech.nerddash.aulago.model.Professor;
import tech.nerddash.aulago.rest.ProfessorController;

public class ProfessorControllerTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	private ProfessorDao dao;
	private MockResult result;
	private MockValidator validator;
	private Professor professor;
	private ProfessorController professorController;

	@Before
	public void setUp() throws Exception {

		result = new MockResult();
		validator = new MockValidator();
		dao = mock(ProfessorDao.class);

		professorController = new ProfessorController(validator, result, dao);

		professor = new Professor();
		professor.setNome("Flávio Arantes");
		professor.setFormacao("Letras UFTM");
		professor.setSenha("100xxx");
		professor.setEmail("flavio@email.com");

	}

	@Test
	public void deveInserirUmProfessor() throws Exception {

		when(dao.insert(professor)).thenReturn(professor);
		assertEquals(professor, professorController.insere(professor));
		verify(dao, times(1)).insert(professor);

	}

	@Test
	public void deveDevolverUmProfessor() {

		when(dao.get(anyLong())).thenReturn(professor);
		assertEquals(professor, professorController.get(professor));
		verify(dao, times(1)).get(anyLong());

	}

	@Test
	public void deveRemoverUmProfessor() {

		when(dao.delete(professor)).thenReturn(true);
		when(dao.get(anyLong())).thenReturn(professor);
		assertTrue(professorController.delete(professor));
		verify(dao, times(1)).get(anyLong());
		verify(dao, times(1)).delete(professor);

	}

	@Test
	public void deveAtualizarProfessor() {

		professor.setNome("Victor Junior");
		professor.setFormacao("Análise e Desenvolvimento de sistemas");
		professor.setSenha("xxx300");
		professor.setEmail("victor" + 31 + "@email.com");

		when(dao.update(professor)).thenReturn(professor);
		assertEquals(professor, professorController.atualiza(professor));
		verify(dao, times(1)).update(professor);

	}

}
