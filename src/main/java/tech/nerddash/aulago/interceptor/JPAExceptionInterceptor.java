package tech.nerddash.aulago.interceptor;

import static br.com.caelum.vraptor.view.Results.json;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.jpa.JPATransactionInterceptor;


@Intercepts(before = JPATransactionInterceptor.class)
@RequestScoped
public class JPAExceptionInterceptor {
	private final Result result;
	private final String ERROR_TAG = "API-Error";

	@Deprecated
	protected JPAExceptionInterceptor() {
		this(null);
	}

	@Inject
	public JPAExceptionInterceptor(Result result) {
		this.result = result;
	}

	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {
		try {
			stack.next();
		} catch (Exception e) {			
			result.use(json()).from(e.getCause(), ERROR_TAG).serialize();
//			result.use(Results.http()).sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}