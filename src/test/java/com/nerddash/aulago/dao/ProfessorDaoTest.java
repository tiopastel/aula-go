package com.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.Query;
import javax.persistence.Table;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Professor;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

public class ProfessorDaoTest extends AbstractRepositoryTest {

	private ProfessorDao dao;
	private ArrayList<Professor> professores;
	private final String COLUMN_NAME = Professor.class.getAnnotation(Table.class).name();

	@Before
	public void setUp() throws Exception {

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

	@After
	public void tearDown() throws Exception {
		em.clear();
		Query query = em
				.createNativeQuery("DELETE FROM "+COLUMN_NAME+"; ALTER TABLE "+COLUMN_NAME+" ALTER COLUMN id RESTART WITH 1;");
		query.executeUpdate();
	}

	@Test
	public void test() {
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
