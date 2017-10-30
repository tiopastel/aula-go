package tech.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tech.nerddash.aulago.model.Aula;
import tech.nerddash.aulago.model.Nivel;
import tech.nerddash.aulago.model.Oferta;
import tech.nerddash.aulago.model.Professor;
import tech.nerddash.aulago.repository.AbstractRepositoryTest;

public class OfertaDaoTest extends AbstractRepositoryTest{

	private OfertaDao dao;
	private ProfessorDao daoProfessor;
	private AulaDao daoAula;
	private List<Oferta> ofertas;
	private Professor professor;
	private Aula aula;

	@Before
	public void setUp() throws Exception {
		
		this.dbLanguage = DbLanguage.H2;
		this.entityObject = new Oferta();
		
		dao = new OfertaDao(em);
		daoProfessor = new ProfessorDao(em);
		daoAula = new AulaDao(em);
		
		ofertas = new ArrayList<Oferta>();
		
		professor = new Professor();
		
		professor.setNome("Flávio Arantes");
		professor.setSenha("100xxx");
		professor.setEmail("flavio@email.com");
		professor.setFormacao("ADS");
			
		daoProfessor.insert(professor);
		
		aula = new Aula();
		
		aula.setMateria("Matemática Discreta");
		aula.setNivel(Nivel.SUPERIOR);
		
		
		daoAula.insert(aula);
		
		em.refresh(professor);
		em.refresh(aula);
		
		for (int i = 0; i < 10; i++) {
			Oferta oferta = new Oferta();
			oferta.setProfessor(professor);
			oferta.setAula(aula);
			oferta.setDataFinal(LocalDate.now().plusDays(i+1));
			oferta.setPreco(15.50*(i*1.1));
			ofertas.add(oferta);
		}
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		resetTable(professor);
		resetTable(aula);
	}

	@Test
	public void deveInserirListarTodosOfertasDepoisRemover2Ofertas() {

		for (int j = 0; j < ofertas.size(); j++) {
			dao.insert(ofertas.get(j));
			assertThat("Não pode encontrar o registro " + j, ofertas.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(ofertas.get(4));
		dao.delete(ofertas.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}
	
	@Test
	public void deveInserirListarTodosOfertasDepoisRemover2OfertasDeNovo() {

		for (int j = 0; j < ofertas.size(); j++) {
			dao.insert(ofertas.get(j));
			assertThat("Não pode encontrar o registro " + j, ofertas.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(ofertas.get(4));
		dao.delete(ofertas.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}
}
