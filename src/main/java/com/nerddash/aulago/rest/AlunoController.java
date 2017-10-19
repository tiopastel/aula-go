package com.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import com.nerddash.aulago.dao.AlunoDao;
import com.nerddash.aulago.model.Aluno;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class AlunoController {

	private final Result result;
	private AlunoDao dao;
	private Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected AlunoController() {
		this(null, null, null);
	}

	@Inject
	public AlunoController(Result result, AlunoDao dao, Validator validator) {
		this.result = result;
		this.dao = dao;
		this.validator = validator;
	}

	@Post("/aluno/")
	public Aluno insere(Aluno aluno) {
		aluno = dao.insert(aluno);
		validator.onErrorRedirectTo(ErrorController.class).error();
		result.use(json()).from(aluno).serialize();
		return aluno;
	}

	@Get("/aluno/{aluno.id}")
	public Aluno get(Aluno aluno) {

		aluno = dao.get(aluno.getId());
		validator.onErrorRedirectTo(ErrorController.class).error();
		result.use(json()).from(aluno).serialize();
		return aluno;

	}
	
	@Delete("/aluno/{aluno.id}")
	public boolean delete(Aluno aluno) {
		validator.onErrorRedirectTo(ErrorController.class).error();
		return dao.delete(aluno);
		
	}
	
	@Put("/aluno/")
	public Aluno atualiza(Aluno aluno) {
		validator.onErrorRedirectTo(ErrorController.class).error();
		result.use(json()).from(aluno).serialize();
		return dao.update(aluno);
		
	}

}
