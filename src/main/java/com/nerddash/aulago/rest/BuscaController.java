package com.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import com.nerddash.aulago.dao.BuscaDao;
import com.nerddash.aulago.model.Busca;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class BuscaController {

	private final Result result;
	private final BuscaDao dao;
	private final String ERROR_TAG = "API-Error";
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected BuscaController() {
		this(null, null, null);
	}

	@Inject
	public BuscaController(Validator validator, Result result, BuscaDao dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	@Consumes("application/json")
	@Post("/busca")
	public Busca insere(Busca busca) {

		valida(busca);

		busca = dao.insert(busca);
		result.use(json()).from(busca).recursive().serialize();
		return busca;

	}

	@Get("/busca/{busca.id}")
	public Busca get(Busca busca) {

		try {
			busca = dao.get(busca.getId());
			result.use(json()).from(busca).recursive().serialize();
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return busca;

	}

	@Delete("/busca/{busca.id}")
	public boolean delete(Busca busca) {

		busca = dao.get(busca.getId());

		valida(busca);

		try {
			result.use(json()).from(busca).recursive().serialize();
			dao.delete(busca);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;

	}
	
	@Consumes("application/json")
	@Put("/busca")
	public Busca atualiza(Busca busca) {
		
		valida(busca);

		try {
			busca = dao.update(busca);
			result.use(json()).from(busca).recursive().serialize();
			return busca;
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}

		return null;

	}

	@Get("/busca/resetTabela")
	public boolean resetTable() {
		try {
			dao.resetTable(Busca.class);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;
	}

	private void valida(Busca busca) {
		validator.validate(busca);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}
}
