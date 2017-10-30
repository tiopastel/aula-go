package tech.nerddash.aulago.API;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.aulago.model.Professor;

public class ProfessorAPITest extends AbstractRestApiTest {

	private Professor professor;
	private Professor resposta;
	private JsonPath retorno;

	@Before
	public void setUp() throws Exception {

		professor = new Professor();
		professor.setNome("Flávio Arantes");
		professor.setFormacao("Letras UFTM");
		professor.setSenha("100xxx");
		professor.setEmail("flavio@email.com");


		retorno = given().header("Accept", "application/json").contentType("application/json").body(professor).expect()
				.statusCode(200).when().post("/professor").andReturn().jsonPath();

		professor = retorno.getObject("professor", Professor.class);

	}

	@After
	public void tearDown() throws Exception {
		given().get("/professor/resetTabela");
	}

	@Test
	public void get() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().get("/professor/1").andReturn().jsonPath();

		resposta = retorno.getObject("professor", Professor.class);

		assertEquals("Flávio Arantes", resposta.getNome());
		assertEquals("Letras UFTM", resposta.getFormacao());
		assertEquals("flavio@email.com", resposta.getEmail());

	}

	@Test
	public void insert() {

		professor = new Professor();
		professor.setNome("Victor Gomes");
		professor.setFormacao("ADS IFTM");
		professor.setSenha("333xxx");
		professor.setEmail("vitao@email.com");


		retorno = given().header("Accept", "application/json").contentType("application/json").body(professor).expect()
				.statusCode(200).when().post("/professor").andReturn().jsonPath();

		resposta = retorno.getObject("professor", Professor.class);

		Long expectedId = (long) 2;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Victor Gomes", resposta.getNome());
		assertEquals("ADS IFTM", resposta.getFormacao());
		assertEquals("vitao@email.com", resposta.getEmail());

	}

	@Test
	public void delete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().delete("/professor/1").andReturn().jsonPath();

		resposta = retorno.getObject("professor", Professor.class);

		assertEquals("Flávio Arantes", resposta.getNome());
		assertEquals("Letras UFTM", resposta.getFormacao());
		assertEquals("flavio@email.com", resposta.getEmail());

	}

	@Test
	public void update() {

		professor.setNome("Victor Gomes");
		professor.setFormacao("ADS IFTM");
		professor.setEmail("vitao@email.com");
		

		retorno = given().header("Accept", "application/json").contentType("application/json").body(professor).expect()
				.statusCode(200).when().put("/professor").andReturn().jsonPath();

		resposta = retorno.getObject("professor", Professor.class);

		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Victor Gomes", resposta.getNome());
		assertEquals("ADS IFTM", resposta.getFormacao());
		assertEquals("vitao@email.com", resposta.getEmail());

	}
}
