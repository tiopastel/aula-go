package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

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

		this.em.persist(oferta);
		em.refresh(oferta);
		return oferta;
	}

	public Oferta get(Long id) {

		return this.em.find(Oferta.class, id);

	}

	public Oferta update(Oferta oferta) {
		return this.em.merge(oferta);

	}

	public boolean delete(Oferta oferta) {
		try {
			this.em.remove(oferta);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public List<Oferta> listAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Oferta> query = criteriaBuilder.createQuery(Oferta.class);

		Root<Oferta> root = query.from(Oferta.class);
		Order[] orderBy = { criteriaBuilder.asc(root.get("id")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<Oferta> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}
}
