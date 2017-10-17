package com.nerddash.aulago.rest;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.dao.AlunoDao;
import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
public class AlunoController {
	
	
	private final Result result;
	private Aluno alunoInvalido;
	private EntityManager em;
	private AlunoDao dao;
	/**
	 * @deprecated CDI eyes only
	 */
	protected AlunoController() {
		this(null, null);
	}
	
	@Inject
	public AlunoController(Result result, AlunoDao dao) {
		this.result = result;
		this.dao = dao;
	}

	@Path("/")
	public void index() {
			
		result.include("variable", "VRaptor!");
	}
	@Path("/error")
	public void error() {
		
		alunoInvalido = new Aluno();

		alunoInvalido.setNome("Flávio Arantes");
		alunoInvalido.setNivel(Nivel.SUPERIOR);
		alunoInvalido.setCpf(153);
		alunoInvalido.setSenha("100xxx");
		dao.insert(alunoInvalido);
	}

}
