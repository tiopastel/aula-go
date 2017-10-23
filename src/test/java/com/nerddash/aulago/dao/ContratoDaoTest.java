package com.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Aula;
import com.nerddash.aulago.model.Contrato;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.model.Oferta;
import com.nerddash.aulago.model.Professor;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

public class ContratoDaoTest extends AbstractRepositoryTest{

	private OfertaDao daoOferta;
	private ProfessorDao daoProfessor;
	private AulaDao daoAula;
	private Professor professor;
	private Aula aula;
	private Oferta oferta;
	private ContratoDao dao;
	private ArrayList<Contrato> contratos;
	private ArrayList<Oferta> ofertas;
	private Contrato contrato;
	private Aluno aluno;
	private AlunoDao daoAluno;

	@Before
	public void setUp() throws Exception {
		
		this.entityObject = new Contrato();
		
		dao = new ContratoDao(em);
		daoOferta = new OfertaDao(em);
		daoProfessor = new ProfessorDao(em);
		daoAula = new AulaDao(em);
		daoAluno = new AlunoDao(em);
		
		
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
			
		
		aluno = new Aluno();
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio@email.com");
		aluno.setCurso("Curso");
		
		daoAluno.insert(aluno);
		
		em.refresh(aluno);
		em.refresh(professor);
		em.refresh(aula);
		
		ofertas = new ArrayList<Oferta>();
		contratos = new ArrayList<Contrato>();
		
		for (int i = 0; i < 10; i++) {
			oferta = new Oferta();
			oferta.setProfessor(professor);
			oferta.setAula(aula);
			oferta.setDataFinal(LocalDate.now().plusDays(i+1));
			oferta.setPreco(15.50*(i*1.1));
			ofertas.add(daoOferta.insert(oferta));
		}
		
		for (Oferta oferta : ofertas) {
			contrato = new Contrato();
			contrato.setAluno(aluno);
			contrato.setOferta(oferta);
			contratos.add(contrato);
		}
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		resetTable(aluno);
		resetTable(aula);
		resetTable(professor);
		resetTable(oferta);
	}

	@Test
	public void deveInserirListarTodosContratosDepoisRemover2Contratos() {

		for (int j = 0; j < contratos.size(); j++) {
			dao.insert(contratos.get(j));
			assertThat("Não pode encontrar o registro " + j, contratos.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(contratos.get(4));
		dao.delete(contratos.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}
	
	@Test
	public void deveInserirListarTodosContratosDepoisRemover2ContratosDeNovo() {

		for (int j = 0; j < contratos.size(); j++) {
			dao.insert(contratos.get(j));
			assertThat("Não pode encontrar o registro " + j, contratos.get(j).getId(), equalTo((long) 1 + j));
		}
		assertThat("Não consegiu listar todos as tuplas", dao.listAll().size(), equalTo(10));

		dao.delete(contratos.get(4));
		dao.delete(contratos.get(7));

		assertThat("Houve algum problema ao excluir as tuplas", dao.listAll().size(), equalTo(8));
	}

}
