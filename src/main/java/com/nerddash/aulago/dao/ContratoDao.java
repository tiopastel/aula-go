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

import com.nerddash.aulago.model.Contrato;

@RequestScoped
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

		this.em.persist(contrato);
		em.refresh(contrato);
		return contrato;
	}

	public Contrato get(Long id) {

		return this.em.find(Contrato.class, id);
	}

	public Contrato update(Contrato contrato) {

		return this.em.merge(contrato);

	}

	public boolean delete(Contrato contrato) {
		try {
			this.em.remove(contrato);
			return true;

		} catch (Exception e) {

			return false;
		}
	}

	public List<Contrato> listAll() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Contrato> query = criteriaBuilder.createQuery(Contrato.class);

		Root<Contrato> root = query.from(Contrato.class);
		Order[] orderBy = { criteriaBuilder.asc(root.get("id")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<Contrato> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}
}
