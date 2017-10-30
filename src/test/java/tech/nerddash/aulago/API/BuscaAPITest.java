package tech.nerddash.aulago.API;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.aulago.model.Aluno;
import tech.nerddash.aulago.model.Aula;
import tech.nerddash.aulago.model.Busca;
import tech.nerddash.aulago.model.Nivel;

public class BuscaAPITest extends AbstractRestApiTest {

	private Busca busca;
	private Busca resposta;
	private JsonPath retorno;
	private Aluno aluno;
	private Aula aula;

	@Before
	public void setUp() throws Exception {

		aula = new Aula();
		aula.setMateria("Matemática Discreta");
		aula.setNivel(Nivel.SUPERIOR);

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aula).expect()
				.statusCode(200).when().post("/aula").andReturn().jsonPath();

		aula = retorno.getObject("aula", Aula.class);

		aluno = new Aluno();
		aluno.setNome("Nepunumucemo");
		aluno.setCurso("IF Trevoso");
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio@email.com");
		aluno.setNivel(Nivel.MEDIO);

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aluno).expect()
				.statusCode(200).when().post("/aluno").andReturn().jsonPath();

		aluno = retorno.getObject("aluno", Aluno.class);

		busca = new Busca();
		busca.setAluno(aluno);
		busca.setAula(aula);
		busca.setDataFinal(LocalDate.now().plusDays(30));

		retorno = given().header("Accept", "application/json").contentType("application/json").body(busca).expect()
				.statusCode(200).when().post("/busca").andReturn().jsonPath();

		busca = retorno.getObject("busca", Busca.class);

	}

	@After
	public void tearDown() throws Exception {
		given().get("/busca/resetTabela");
		given().get("/aula/resetTabela");
		given().get("/aluno/resetTabela");
	}

	@Test
	public void get() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().get("/busca/1").andReturn().jsonPath();

		resposta = retorno.getObject("busca", Busca.class);

		assertEquals("Nepunumucemo", resposta.getAluno().getNome());
		assertEquals("IF Trevoso", resposta.getAluno().getCurso());
		assertEquals("flavio@email.com", resposta.getAluno().getEmail());

		assertEquals("Matemática Discreta", resposta.getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getAula().getNivel());

		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals(LocalDate.now().plusDays(30), resposta.getDataFinal());

	}

	@Test
	public void insert() {

		busca = new Busca();
		busca.setAluno(aluno);
		busca.setAula(aula);
		busca.setDataFinal(LocalDate.now().plusDays(60));

		retorno = given().header("Accept", "application/json").contentType("application/json").body(busca).expect()
				.statusCode(200).when().post("/busca").andReturn().jsonPath();

		resposta = retorno.getObject("busca", Busca.class);

		Long expectedId = (long) 2;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Nepunumucemo", resposta.getAluno().getNome());
		assertEquals("IF Trevoso", resposta.getAluno().getCurso());
		assertEquals("flavio@email.com", resposta.getAluno().getEmail());

		assertEquals("Matemática Discreta", resposta.getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getAula().getNivel());

		assertEquals(LocalDate.now().plusDays(60), resposta.getDataFinal());

	}

	@Test
	public void delete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().delete("/busca/1").andReturn().jsonPath();

		resposta = retorno.getObject("busca", Busca.class);

		assertEquals(LocalDate.now().plusDays(30), resposta.getDataFinal());

	}

	@Test
	public void update() {

		busca.setDataFinal(LocalDate.now().plusDays(90));

		retorno = given().header("Accept", "application/json").contentType("application/json").body(busca).expect()
				.statusCode(200).when().put("/busca").andReturn().jsonPath();

		resposta = retorno.getObject("busca", Busca.class);

		assertEquals(LocalDate.now().plusDays(90), resposta.getDataFinal());

	}
}
