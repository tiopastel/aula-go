package com.nerddash.aulago.rest;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
public class AlunoController {
	
	
	private final Result result;

	/**
	 * @deprecated CDI eyes only
	 */
	protected AlunoController() {
		this(null);
	}
	
	@Inject
	public AlunoController(Result result) {
		this.result = result;
	}

	@Path("/")
	public void index() {
		result.include("variable", "VRaptor!");
	}

}
