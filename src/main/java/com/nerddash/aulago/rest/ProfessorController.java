package com.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import com.nerddash.aulago.dao.ProfessorDao;
import com.nerddash.aulago.model.Professor;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class ProfessorController {

	private final Result result;
	private final ProfessorDao dao;
	private final String ERROR_TAG = "API-Error";
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected ProfessorController() {
		this(null, null, null);
	}

	@Inject
	public ProfessorController(Validator validator, Result result, ProfessorDao dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	@Consumes("application/json")
	@Post("/professor")
	public Professor insere(Professor professor) {

		valida(professor);

		professor = dao.insert(professor);
		result.use(json()).from(professor).serialize();
		return professor;

	}

	@Get("/professor/{professor.id}")
	public Professor get(Professor professor) {

		try {
			professor = dao.get(professor.getId());
			result.use(json()).from(professor).serialize();
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return professor;

	}

	@Delete("/professor/{professor.id}")
	public boolean delete(Professor professor) {

		professor = dao.get(professor.getId());

		valida(professor);

		try {
			result.use(json()).from(professor).serialize();
			dao.delete(professor);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;

	}
	
	@Consumes("application/json")
	@Put("/professor")
	public Professor atualiza(Professor professor) {
		
		valida(professor);

		try {
			professor = dao.update(professor);
			result.use(json()).from(professor).serialize();
			return professor;
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}

		return null;

	}

	@Get("/professor/resetTabela")
	public boolean resetTable() {
		try {
			dao.resetTable(Professor.class);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;
	}

	private void valida(Professor professor) {
		validator.validate(professor);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}
}
