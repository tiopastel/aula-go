package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import com.nerddash.aulago.model.Contrato;

public class ContratoDao {

	private final EntityManager em;

	@Inject
	public ContratoDao(EntityManager em) {
		this.em = em;
	}

	// CDI only use
	@Deprecated
	public ContratoDao() {
		this(null);
	}

	public Contrato insert(Contrato contrato) {

		try {
			this.em.persist(contrato);
			return contrato;
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Contrato get(Long id) {
		try {
			return this.em.find(Contrato.class, id);
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Contrato update(Contrato contrato) {
		try {
			return this.em.merge(contrato);

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public boolean delete(Contrato contrato) {
		try {
			this.em.remove(contrato);
			return true;

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> listAll() {
		return em.createQuery("Select t from " + Contrato.class.getAnnotation(Table.class).name() + " t").getResultList();
	}
}
