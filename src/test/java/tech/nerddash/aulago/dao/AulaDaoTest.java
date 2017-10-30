package tech.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tech.nerddash.aulago.model.Aula;
import tech.nerddash.aulago.model.Nivel;
import tech.nerddash.aulago.repository.AbstractRepositoryTest;

public class AulaDaoTest extends AbstractRepositoryTest {
	
	private List<Aula> aulas;
	private AulaDao dao;
	private Aula aula;

	@Before
	public void setUp() throws Exception {
		
		this.dbLanguage = DbLanguage.H2;
		this.entityObject = new Aula();
		
		dao = new AulaDao(em);
		
		aulas = new ArrayList<Aula>();
		
		for (int i = 0; i < 10; i++) {
			aula = new Aula();
			aula.setMateria("Matemática Discreta");
			aula.setNivel(Nivel.SUPERIOR);
			aulas.add(aula);
		}

	}

	@Test
	public void deveInserirListarTodosAulasDepoisRemover2Aulas() {
		for (int j = 0; j < aulas.size(); j++) {
			dao.insert(aulas.get(j));
			assertThat("Não pode encontrar o registro " + j, aulas.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(aulas.get(4));
		dao.delete(aulas.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}
	
	@Test
	public void deveInserirListarTodosAulasDepoisRemover2AulasDeNovo() {
		for (int j = 0; j < aulas.size(); j++) {
			dao.insert(aulas.get(j));
			assertThat("Não pode encontrar o registro " + j, aulas.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(aulas.get(4));
		dao.delete(aulas.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}

}
