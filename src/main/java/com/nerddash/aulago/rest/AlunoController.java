package com.nerddash.aulago.rest;

import javax.inject.Inject;

import com.nerddash.aulago.dao.AlunoDao;
import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.model.Nivel;
import com.nerddash.aulago.model.Pessoa.Sexo;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
public class AlunoController {
	
	
	private final Result result;
	private Aluno aluno;
	private AlunoDao alunoDao;

	/**
	 * @deprecated CDI eyes only
	 */
	protected AlunoController() {
		this(null, null);
	}
	
	@Inject
	public AlunoController(Result result, AlunoDao alunoDao) {
		this.result = result;
		this.alunoDao = alunoDao;
	}

	@Path("/")
	public void index() {
		
		aluno = new Aluno();
		aluno.setNome("Flávio Arantes");
		aluno.setNivel(Nivel.SUPERIOR);
		aluno.setCpf(153);
		aluno.setSenha("100xxx");
		aluno.setSexo(Sexo.MASCULINO);
		aluno.setEmail("flavio@email.com");
		alunoDao.insert(aluno);
		
		result.include("variable", "VRaptor!");
	}

}
