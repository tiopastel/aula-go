package tech.nerddash.aulago.API;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.restassured.path.json.JsonPath;
import tech.nerddash.aulago.model.Aluno;
import tech.nerddash.aulago.model.Aula;
import tech.nerddash.aulago.model.Contrato;
import tech.nerddash.aulago.model.Nivel;
import tech.nerddash.aulago.model.Oferta;
import tech.nerddash.aulago.model.Professor;

public class ContratoAPITest extends AbstractRestApiTest {

	private Contrato contrato;
	private Contrato resposta;
	private JsonPath retorno;
	private Aluno aluno;
	private Oferta oferta;
	private Aula aula;
	private Professor professor;

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
		professor.setEmail("prof@email.com");
		
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

		aluno = new Aluno();
		aluno.setNome("Flávio Barcelos");
		aluno.setCurso("IF Trevoso");
		aluno.setSenha("100xxx");
		aluno.setEmail("flavio@email.com");
		aluno.setNivel(Nivel.MEDIO);

		retorno = given().header("Accept", "application/json").contentType("application/json").body(aluno).expect()
				.statusCode(200).when().post("/aluno").andReturn().jsonPath();

		aluno = retorno.getObject("aluno", Aluno.class);

		contrato = new Contrato();
		contrato.setAluno(aluno);
		contrato.setOferta(oferta);
		contrato.setAvaliacao(3);


		retorno = given().header("Accept", "application/json").contentType("application/json").body(contrato).expect()
				.statusCode(200).when().post("/contrato").andReturn().jsonPath();

		contrato = retorno.getObject("contrato", Contrato.class);

	}

	@After
	public void tearDown() throws Exception {
		given().get("/contrato/resetTabela");
		given().get("/oferta/resetTabela");
		given().get("/aluno/resetTabela");
	}

	@Test
	public void get() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().get("/contrato/1").andReturn().jsonPath();

		resposta = retorno.getObject("contrato", Contrato.class);

		assertEquals("Flávio Barcelos", resposta.getAluno().getNome());
		assertEquals("IF Trevoso", resposta.getAluno().getCurso());
		assertEquals("flavio@email.com", resposta.getAluno().getEmail());

		assertEquals("Matemática Discreta", resposta.getOferta().getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getOferta().getAula().getNivel());

		Long expectedId = (long) 1;

		assertEquals(expectedId, resposta.getId());
		assertEquals(3, resposta.getAvaliacao());

	}

	@Test
	public void insert() {

		contrato = new Contrato();
		contrato.setAluno(aluno);
		contrato.setOferta(oferta);
		contrato.setAvaliacao(5);;

		retorno = given().header("Accept", "application/json").contentType("application/json").body(contrato).expect()
				.statusCode(200).when().post("/contrato").andReturn().jsonPath();

		resposta = retorno.getObject("contrato", Contrato.class);

		Long expectedId = (long) 2;

		assertEquals(expectedId, resposta.getId());
		assertEquals("Flávio Barcelos", resposta.getAluno().getNome());
		assertEquals("IF Trevoso", resposta.getAluno().getCurso());
		assertEquals("flavio@email.com", resposta.getAluno().getEmail());

		assertEquals("Matemática Discreta", resposta.getOferta().getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getOferta().getAula().getNivel());

		assertEquals(5, resposta.getAvaliacao());

	}

	@Test
	public void delete() {

		retorno = given().header("Accept", "application/json").contentType("application/json").expect().statusCode(200)

				.when().delete("/contrato/1").andReturn().jsonPath();

		resposta = retorno.getObject("contrato", Contrato.class);

		assertEquals("Flávio Barcelos", resposta.getAluno().getNome());
		assertEquals("IF Trevoso", resposta.getAluno().getCurso());
		assertEquals("flavio@email.com", resposta.getAluno().getEmail());

		assertEquals("Matemática Discreta", resposta.getOferta().getAula().getMateria());
		assertEquals(Nivel.SUPERIOR, resposta.getOferta().getAula().getNivel());

		assertEquals(3, resposta.getAvaliacao());

	}

	@Test
	public void update() {

		contrato.setAvaliacao(2);;

		retorno = given().header("Accept", "application/json").contentType("application/json").body(contrato).expect()
				.statusCode(200).when().put("/contrato").andReturn().jsonPath();

		resposta = retorno.getObject("contrato", Contrato.class);

		assertEquals(2, resposta.getAvaliacao());

	}
}
