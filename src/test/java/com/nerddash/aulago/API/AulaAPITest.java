package com.nerddash.aulago.API;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aula;
import com.nerddash.aulago.model.Nivel;

import io.restassured.path.json.JsonPath;

public class AulaAPITest extends AbstractRestApiTest {

	private Aula aula;
	private Aula resposta;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {

		aula = new Aula();
		aula.setMateria("Matemática Discreta");
		aula.setNivel(Nivel.SUPERIOR);


		retorno = given().header("Accept", "application/json").contentType("application/json").body(aula).expect()
				.statusCode(200).when().post("/aula").andReturn().jsonPath();

		aula = retorno.getObject("aula", Aula.class);


	}

	@After
	public void tearDown() throws Exception {
		given().get("/aula/resetTabela");
	}

	@Test
	public void get() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().get("/aula/1").andReturn().jsonPath();

		resposta = retorno.getObject("aula", Aula.class);

		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Matemática Discreta", resposta.getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());


	}

	@Test
	public void insert() {

		aula = new Aula();
		aula.setMateria("Estrutura de Dados");
		aula.setNivel(Nivel.SUPERIOR);


		retorno = given().header("Accept", "application/json").contentType("application/json").body(aula).expect()
				.statusCode(200).when().post("/aula").andReturn().jsonPath();

		resposta = retorno.getObject("aula", Aula.class);

		Long expectedId = (long) 2;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Estrutura de Dados", resposta.getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());


	}

	@Test
	public void delete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().delete("/aula/1").andReturn().jsonPath();

		resposta = retorno.getObject("aula", Aula.class);

		assertEquals("Matemática Discreta", resposta.getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());

	}

	@Test
	public void update() {

		aula.setMateria("Estrutura de Dados"); 
		aula.setNivel(Nivel.BASICO);
		

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aula).expect()
				.statusCode(200).when().put("/aula").andReturn().jsonPath();

		resposta = retorno.getObject("aula", Aula.class);

		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Estrutura de Dados", resposta.getMateria());
		assertEquals(Nivel.BASICO, resposta.getNivel());

	}
}

