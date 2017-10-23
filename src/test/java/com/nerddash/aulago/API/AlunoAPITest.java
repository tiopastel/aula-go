package com.nerddash.aulago.API;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;

import io.restassured.path.json.JsonPath;

public class AlunoAPITest extends AbstractRestApiTest {

	private Aluno aluno;
	private Aluno resposta;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {

		aluno = new Aluno();
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio@email.com");
		aluno.setCurso("ADS");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aluno).expect()
				.statusCode(200).when().post("/aluno").andReturn().jsonPath();

		aluno = retorno.getObject("aluno", Aluno.class);

	}

	@After
	public void tearDown() throws Exception {
		given().get("/aluno/resetTabela");
	}

	@Test
	public void get() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().get("/aluno/1").andReturn().jsonPath();

		resposta = retorno.getObject("aluno", Aluno.class);

		assertEquals("Flávio Arantes", resposta.getNome());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());
		assertEquals("flavio@email.com", resposta.getEmail());

	}

	@Test
	public void insert() {

		aluno = new Aluno();
		aluno.setNome("Victor Gomes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setSenha("333xxx");
		aluno.setEmail("vitao@email.com");
		aluno.setCurso("ADS");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aluno).expect()
				.statusCode(200).when().post("/aluno").andReturn().jsonPath();

		resposta = retorno.getObject("aluno", Aluno.class);

		Long expectedId = (long) 2;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Victor Gomes", resposta.getNome());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());
		assertEquals("vitao@email.com", resposta.getEmail());

	}

	@Test
	public void delete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().delete("/aluno/1").andReturn().jsonPath();

		resposta = retorno.getObject("aluno", Aluno.class);

		assertEquals("Flávio Arantes", resposta.getNome());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());
		assertEquals("flavio@email.com", resposta.getEmail());

	}

	@Test
	public void update() {

		aluno.setNome("Victor Gomes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setEmail("vitao@email.com");
		aluno.setCurso("ADS");

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aluno).expect()
				.statusCode(200).when().put("/aluno").andReturn().jsonPath();

		resposta = retorno.getObject("aluno", Aluno.class);

		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Victor Gomes", resposta.getNome());
		assertEquals(Nivel.SUPERIOR, resposta.getNivel());
		assertEquals("vitao@email.com", resposta.getEmail());

	}
}
