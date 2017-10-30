package tech.nerddash.aulago.rest;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import tech.nerddash.aulago.dao.ContratoDao;
import tech.nerddash.aulago.model.Contrato;

@Controller
public class ContratoController {

	private final Result result;
	private final ContratoDao dao;
	private final String ERROR_TAG = "API-Error";
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	protected ContratoController() {
		this(null, null, null);
	}

	@Inject
	public ContratoController(Validator validator, Result result, ContratoDao dao) {
		this.validator = validator;
		this.result = result;
		this.dao = dao;
	}

	@Consumes("application/json")
	@Post("/contrato")
	public Contrato insere(Contrato contrato) {

		valida(contrato);

		contrato = dao.insert(contrato);
		result.use(json()).from(contrato).recursive().serialize();
		return contrato;

	}

	@Get("/contrato/{contrato.id}")
	public Contrato get(Contrato contrato) {

		try {
			contrato = dao.get(contrato.getId());
			result.use(json()).from(contrato).recursive().serialize();
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return contrato;

	}

	@Delete("/contrato/{contrato.id}")
	public boolean delete(Contrato contrato) {

		contrato = dao.get(contrato.getId());

		valida(contrato);

		try {
			result.use(json()).from(contrato).recursive().serialize();
			dao.delete(contrato);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;

	}
	
	@Consumes("application/json")
	@Put("/contrato")
	public Contrato atualiza(Contrato contrato) {
		
		valida(contrato);

		try {
			contrato = dao.update(contrato);
			result.use(json()).from(contrato).recursive().serialize();
			return contrato;
		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}

		return null;

	}

	@Get("/contrato/resetTabela")
	public boolean resetTable() {
		try {
			dao.resetTable(Contrato.class);
			return true;

		} catch (Exception e) {
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
		}
		return false;
	}

	private void valida(Contrato contrato) {
		validator.validate(contrato);
		validator.onErrorUse(json()).from(validator.getErrors(), ERROR_TAG).serialize();
	}
}
