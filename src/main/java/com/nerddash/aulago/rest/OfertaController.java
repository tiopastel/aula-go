package com.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import com.nerddash.aulago.dao.OfertaDao;
import com.nerddash.aulago.model.Oferta;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

@Controller
public class OfertaController {

	private final Result result;
	private final OfertaDao dao;
	private final String ERROR_TAG = "API-Error";
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected OfertaController() {
		this(null, null, null);
	}

	@Inject
	public OfertaController(Validator validator, Result result, OfertaDao dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	@Consumes("application/json")
	@Post("/oferta")
	public Oferta insere(Oferta oferta) {

		valida(oferta);

		oferta = dao.insert(oferta);
		result.use(json()).from(oferta).recursive().serialize();
		return oferta;

	}

	@Get("/oferta/{oferta.id}")
	public Oferta get(Oferta oferta) {

		try {
			oferta = dao.get(oferta.getId());
			result.use(json()).from(oferta).recursive().serialize();
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return oferta;

	}

	@Delete("/oferta/{oferta.id}")
	public boolean delete(Oferta oferta) {

		oferta = dao.get(oferta.getId());

		valida(oferta);

		try {
			result.use(json()).from(oferta).recursive().serialize();
			dao.delete(oferta);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;

	}
	
	@Consumes("application/json")
	@Put("/oferta")
	public Oferta atualiza(Oferta oferta) {
		
		valida(oferta);

		try {
			oferta = dao.update(oferta);
			result.use(json()).from(oferta).recursive().serialize();
			return oferta;
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}

		return null;

	}

	@Get("/oferta/resetTabela")
	public boolean resetTable() {
		try {
			dao.resetTable(Oferta.class);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;
	}

	private void valida(Oferta oferta) {
		validator.validate(oferta);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}
}
