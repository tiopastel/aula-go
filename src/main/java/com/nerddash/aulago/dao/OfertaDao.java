package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import com.nerddash.aulago.model.Oferta;

public class OfertaDao {
	private final EntityManager em;

	@Inject
	public OfertaDao(EntityManager em) {
		this.em = em;
	}

	// CDI only use
	@Deprecated
	public OfertaDao() {
		this(null);
	}

	public Oferta insert(Oferta oferta) {

		try {
			this.em.persist(oferta);
			return oferta;
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Oferta get(Long id) {
		try {
			return this.em.find(Oferta.class, id);
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Oferta update(Oferta oferta) {
		try {
			return this.em.merge(oferta);

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public boolean delete(Oferta oferta) {
		try {
			this.em.remove(oferta);
			return true;

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Oferta> listAll() {
		return em.createQuery("Select t from " + Oferta.class.getAnnotation(Table.class).name() + " t").getResultList();
	}
}
