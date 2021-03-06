package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.nerddash.aulago.model.Busca;

@RequestScoped
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

		this.em.persist(busca);
		em.refresh(busca);
		return busca;

	}

	public Busca get(Long id) {

		return this.em.find(Busca.class, id);

	}

	public Busca update(Busca busca) {

		return this.em.merge(busca);

	}

	public boolean delete(Busca busca) {
		try {
			this.em.remove(busca);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public List<Busca> listAll() {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Busca> query = criteriaBuilder.createQuery(Busca.class);

		Root<Busca> root = query.from(Busca.class);
		Order[] orderBy = { criteriaBuilder.asc(root.get("id")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<Busca> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

}
