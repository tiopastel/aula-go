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

	/*
	 * CDI eyes only
	 * 
	 */
	@Deprecated
	public OfertaDao() {
		this(null);
	}


	public Oferta get(Long id) {
		return super.get(Oferta.class, id);

	}

	public List<Oferta> listAll() {
		return super.listAll(Oferta.class);
	}
}
