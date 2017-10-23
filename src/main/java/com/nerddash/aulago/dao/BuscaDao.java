package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Busca;

@RequestScoped
public class BuscaDao extends AbstractDaoClass<Busca> {

	@Inject
	public BuscaDao(EntityManager em) {
		super(em);
	}
	
	/*
	 * CDI eyes only
	 * 
	 */
	@Deprecated
	public BuscaDao() {
		this(null);
	}

	
	public Busca get(Long id) {
		return super.get(Busca.class, id);
	}

	public List<Busca> listAll() {

		return super.listAll(Busca.class);
	}

}
