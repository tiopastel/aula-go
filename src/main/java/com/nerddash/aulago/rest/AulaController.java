package com.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import com.nerddash.aulago.dao.AulaDao;
import com.nerddash.aulago.model.Aula;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class AulaController {

	private final Result result;
	private final AulaDao dao;
	private final String ERROR_TAG = "API-Error";
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected AulaController() {
		this(null, null, null);
	}

	@Inject
	public AulaController(Validator validator, Result result, AulaDao dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	@Consumes("application/json")
	@Post("/aula")
	public Aula insere(Aula aula) {

		valida(aula);

		aula = dao.insert(aula);
		result.use(json()).from(aula).serialize();
		return aula;

	}

	@Get("/aula/{aula.id}")
	public Aula get(Aula aula) {

		try {
			aula = dao.get(aula.getId());
			result.use(json()).from(aula).serialize();
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return aula;

	}

	@Delete("/aula/{aula.id}")
	public boolean delete(Aula aula) {

		aula = dao.get(aula.getId());

		valida(aula);

		try {
			result.use(json()).from(aula).serialize();
			dao.delete(aula);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;

	}
	
	@Consumes("application/json")
	@Put("/aula")
	public Aula atualiza(Aula aula) {
		
		valida(aula);

		try {
			aula = dao.update(aula);
			result.use(json()).from(aula).serialize();
			return aula;
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}

		return null;

	}

	@Get("/aula/resetTabela")
	public boolean resetTable() {
		try {
			dao.resetTable(Aula.class);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;
	}

	private void valida(Aula aula) {
		validator.validate(aula);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}
}
