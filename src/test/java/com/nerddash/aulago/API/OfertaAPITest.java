package com.nerddash.aulago.API;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aula;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.model.Oferta;
import com.nerddash.aulago.model.Professor;

import io.restassured.path.json.JsonPath;

public class OfertaAPITest extends AbstractRestApiTest {

	private Oferta oferta;
	private Oferta resposta;
	private JsonPath retorno;
	private Professor professor;
	private Aula aula;

	@Before
	public void setUp() throws Exception {
		
		aula = new Aula();
		aula.setMateria("Matemática Discreta");
		aula.setNivel(Nivel.SUPERIOR);
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(aula).expect()
				.statusCode(200).when().post("/aula").andReturn().jsonPath();
		
		aula = retorno.getObject("aula", Aula.class);
		
		
		professor = new Professor();
		professor.setNome("Nepunumucemo");
		professor.setFormacao("IF Trevoso");
		professor.setSenha("100xxx");
		professor.setEmail("flavio@email.com");
		
		retorno = given().header("Accept", "application/json").contentType("application/json").body(professor).expect()
				.statusCode(200).when().post("/professor").andReturn().jsonPath();
		
		professor = retorno.getObject("professor", Professor.class);
		

		oferta = new Oferta();
		oferta.setProfessor(professor);
		oferta.setAula(aula);
		oferta.setDataFinal(LocalDate.now().plusDays(30));
		oferta.setPreco(35.00);
		oferta.setHoraInicio(LocalTime.of(13, 0));
		oferta.setHoraTermino(LocalTime.of(17, 0));	


		retorno = given().header("Accept", "application/json").contentType("application/json").body(oferta).expect()
				.statusCode(200).when().post("/oferta").andReturn().jsonPath();

		oferta = retorno.getObject("oferta", Oferta.class);

	}

	@After
	public void tearDown() throws Exception {
		given().get("/oferta/resetTabela");
		given().get("/aula/resetTabela");
		given().get("/professor/resetTabela");
	}

	@Test
	public void get() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().get("/oferta/1").andReturn().jsonPath();

		resposta = retorno.getObject("oferta", Oferta.class);

		assertEquals("Nepunumucemo", resposta.getProfessor().getNome());
		assertEquals("IF Trevoso", resposta.getProfessor().getFormacao());
		assertEquals("flavio@email.com", resposta.getProfessor().getEmail());
		
		assertEquals("Matemática Discreta", resposta.getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getAula().getNivel());
		
		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals(BigDecimal.valueOf(35.00), resposta.getPreco());
		assertEquals(LocalDate.now().plusDays(30), resposta.getDataFinal());
		assertEquals(LocalTime.of(13, 0), resposta.getHoraInicio());
		assertEquals(LocalTime.of(17, 0), resposta.getHoraTermino());
		

	}

	@Test
	public void insert() {

		oferta = new Oferta();
		oferta.setProfessor(professor);
		oferta.setAula(aula);
		oferta.setDataFinal(LocalDate.now().plusDays(60));
		oferta.setPreco(50.00);
		oferta.setHoraInicio(LocalTime.of(8, 0));
		oferta.setHoraTermino(LocalTime.of(11, 0));	


		retorno = given().header("Accept", "application/json").contentType("application/json").body(oferta).expect()
				.statusCode(200).when().post("/oferta").andReturn().jsonPath();

		resposta = retorno.getObject("oferta", Oferta.class);

		Long expectedId = (long) 2;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Nepunumucemo", resposta.getProfessor().getNome());
		assertEquals("IF Trevoso", resposta.getProfessor().getFormacao());
		assertEquals("flavio@email.com", resposta.getProfessor().getEmail());
		
		assertEquals("Matemática Discreta", resposta.getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getAula().getNivel());

		assertEquals(BigDecimal.valueOf(50.00), resposta.getPreco());
		assertEquals(LocalDate.now().plusDays(60), resposta.getDataFinal());
		assertEquals(LocalTime.of(8, 0), resposta.getHoraInicio());
		assertEquals(LocalTime.of(11, 0), resposta.getHoraTermino());


	}

	@Test
	public void delete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().delete("/oferta/1").andReturn().jsonPath();

		resposta = retorno.getObject("oferta", Oferta.class);

		assertEquals(BigDecimal.valueOf(35.00), resposta.getPreco());
		assertEquals(LocalDate.now().plusDays(30), resposta.getDataFinal());
		assertEquals(LocalTime.of(13, 0), resposta.getHoraInicio());
		assertEquals(LocalTime.of(17, 0), resposta.getHoraTermino());
	}

	@Test
	public void update() {

		oferta.setDataFinal(LocalDate.now().plusDays(90));
		oferta.setPreco(100.00);
		oferta.setHoraInicio(LocalTime.of(0, 0));
		oferta.setHoraTermino(LocalTime.of(6, 0));	
		

		retorno = given().header("Accept", "application/json").contentType("application/json").body(oferta).expect()
				.statusCode(200).when().put("/oferta").andReturn().jsonPath();

		resposta = retorno.getObject("oferta", Oferta.class);

		assertEquals(BigDecimal.valueOf(100.00), resposta.getPreco());
		assertEquals(LocalDate.now().plusDays(90), resposta.getDataFinal());
		assertEquals(LocalTime.of(0, 0), resposta.getHoraInicio());
		assertEquals(LocalTime.of(6, 0), resposta.getHoraTermino());

	}
}
