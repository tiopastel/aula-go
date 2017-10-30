package tech.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import tech.nerddash.aulago.model.Professor;
import tech.nerddash.aulago.repository.AbstractRepositoryTest;

public class ProfessorDaoTest extends AbstractRepositoryTest {

	private ProfessorDao dao;
	private ArrayList<Professor> professores;


	@Before
	public void setUp() throws Exception {
		
		this.dbLanguage = DbLanguage.H2;
		this.entityObject= new Professor();

		dao = new ProfessorDao(em);

		professores = new ArrayList<Professor>();

		for (int i = 0; i < 10; i++) {

			Professor professor = new Professor();
			professor.setNome("Flávio Arantes");
			professor.addLeads(1000);
			professor.setSenha("100xxx");
			professor.setEmail("flavioProf" + i + "@email.com");
			professor.setFormacao("ADS");
			professores.add(professor);
		}

	}

	@Test
	public void deveInserirListarTodosProfsDepoisRemover2Profs() {
		for (int j = 0; j < professores.size(); j++) {
			dao.insert(professores.get(j));
			assertThat("Não pode encontrar o registro " + j, professores.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(professores.get(4));
		dao.delete(professores.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));

	}
	
	@Test
	public void deveInserirListarTodosProfsDepoisRemover2ProfsDeNovo() {
		for (int j = 0; j < professores.size(); j++) {
			dao.insert(professores.get(j));
			assertThat("Não pode encontrar o registro " + j, professores.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(professores.get(4));
		dao.delete(professores.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));

	}

}
