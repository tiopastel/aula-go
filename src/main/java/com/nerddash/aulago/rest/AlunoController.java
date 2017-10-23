package com.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import com.nerddash.aulago.dao.AlunoDao;
import com.nerddash.aulago.model.Aluno;

import br.com.caelum.vraptor.Consumes;
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
	private final AlunoDao dao;
	private final String ERROR_TAG = "API-Error";
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected AlunoController() {
		this(null, null, null);
	}

	@Inject
	public AlunoController(Validator validator, Result result, AlunoDao dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	@Consumes("application/json")
	@Post("/aluno")
	public Aluno insere(Aluno aluno) {
		

		try {
			validator.validate(aluno);			
			aluno = dao.insert(aluno);
			result.use(json()).from(aluno).serialize();
			return aluno;
		} catch (Exception e) {
			validator.onErrorUse(json()).from(validator.getErrors(),  ERROR_TAG).serialize();

		}
		return null;
	}

	@Get("/aluno/{aluno.id}")
	public Aluno get(Aluno aluno) {

		try {
			aluno = dao.get(aluno.getId());
			result.use(json()).from(aluno).serialize();
			return aluno;
		} catch (Exception e) {
			System.out.println(e);
			result.use(json()).from(e, ERROR_TAG).serialize();
		}
		return null;

	}

	@Delete("/aluno/{aluno.id}")
	public boolean delete(Aluno aluno) {
		try {
			dao.delete(aluno);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e, ERROR_TAG).serialize();
		}
		return false;

	}

	@Put("/aluno")
	public Aluno atualiza(Aluno aluno) {
		try {
			aluno = dao.update(aluno);
			result.use(json()).from(aluno).serialize();
			return aluno;
		} catch (Exception e) {
			result.use(json()).from(e, ERROR_TAG).serialize();
		}

		return null;

	}

	@Get("/aluno/resetTabela")
	public boolean resetTable() {
		try {

			dao.resetTable(Aluno.class);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e, ERROR_TAG).serialize();
		}
		return false;
	}

}
