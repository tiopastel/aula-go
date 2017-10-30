package tech.nerddash.aulago.model;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tech.nerddash.aulago.model.Professor;

public class ProfessorTest {

	private Professor professor;

	@Before
	public void setUp() throws Exception {
		
		professor = new Professor();
		professor.setNome("Flávio Arantes");
		professor.setSenha("100xxx");
		professor.setEmail("flavioProf@email.com");
		professor.setFormacao("ADS");
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testAddLeads() {
		professor.addLeads(1000);
		assertThat(professor.getLeads(), equalTo(1000));
	}

	@Test
	public void testRemoveLead() {
		
		if(professor.getLeads() < 1) {
			professor.addLeads(10);
		}
		
		for (int i = 0; i < professor.getLeads(); i++) {
			professor.removeLead();
		}
		
		assertThat(professor.getLeads(), equalTo(0));
	}

}
