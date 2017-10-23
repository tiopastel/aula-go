package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Contrato;

@RequestScoped
public class ContratoDao extends AbstractDaoClass<Contrato> {

	@Inject
	public ContratoDao(EntityManager em) {
		super(em);
	}

	/*
	 * CDI eyes only
	 * 
	 */
	@Deprecated
	public ContratoDao() {
		this(null);
	}

	public Contrato get(Long id) {
		return super.get(Contrato.class, id);
	}

	public List<Contrato> listAll() {
		return super.listAll(Contrato.class);
	}
}
