package com.nerddash.aulago.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aula;
import com.nerddash.aulago.model.Horario;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.repository.AbstractRepositoryTest;

public class AulaDaoTest extends AbstractRepositoryTest {
	
	private List<Aula> aulas;
	private AulaDao dao;

	@Before
	public void setUp() throws Exception {

		this.entityClass = Aula.class;
		
		dao = new AulaDao(em);
		
		aulas = new ArrayList<Aula>();
		
		for (int i = 0; i < 10; i++) {
			Aula aula = new Aula();
			aula.setHorario(new Horario(LocalTime.now(), LocalTime.MIDNIGHT));
			aula.setMateria("Matemática Discreta");
			aula.setNivel(Nivel.SUPERIOR);
			aula.setPreco(15.50);	
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

}
