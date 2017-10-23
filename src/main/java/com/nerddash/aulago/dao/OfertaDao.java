package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Oferta;

public class OfertaDao extends AbstractDaoClass<Oferta>{


	@Inject
	public OfertaDao(EntityManager em) {
		super(em);
	}

	// CDI only use
	@Deprecated
	public OfertaDao() {
		this(null);
	}

	public Oferta insert(Oferta oferta) {

		return super.insert(oferta);
	}

	public Oferta get(Long id) {

		return super.get(Oferta.class, id);

	}

	public Oferta update(Oferta oferta) {
		return super.update(oferta);

	}

	public boolean delete(Oferta oferta) {
		return super.delete(oferta);
	}

	public List<Oferta> listAll() {
		return super.listAll(Oferta.class);
	}
}
