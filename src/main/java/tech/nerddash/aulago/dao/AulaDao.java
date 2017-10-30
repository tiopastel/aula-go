package tech.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import tech.nerddash.aulago.model.Aula;

@RequestScoped
public class AulaDao extends AbstractDaoClass<Aula> {

	@Inject
	public AulaDao(EntityManager em) {
		super(em);

	}
	/*
	 * CDI eyes only
	 * 
	 */
	@Deprecated
	public AulaDao() {
		this(null);
	}


	public Aula get(Long id) {
		return super.get(Aula.class, id);
	}

	public List<Aula> listAll() {
		return super.listAll(Aula.class);
	}
}
