package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import com.nerddash.aulago.model.Busca;

public class BuscaDao {
	private final EntityManager em;

	@Inject
	public BuscaDao(EntityManager em) {
		this.em = em;
	}

	// CDI only use
	@Deprecated
	public BuscaDao() {
		this(null);
	}

	public Busca insert(Busca busca) {

		try {
			this.em.persist(busca);
			return busca;
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Busca get(Long id) {
		try {
			return this.em.find(Busca.class, id);
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Busca update(Busca busca) {
		try {
			return this.em.merge(busca);

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public boolean delete(Busca busca) {
		try {
			this.em.remove(busca);
			return true;

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Busca> listAll() {
		return em.createQuery("Select t from " + Busca.class.getAnnotation(Table.class).name() + " t").getResultList();
	}


}
