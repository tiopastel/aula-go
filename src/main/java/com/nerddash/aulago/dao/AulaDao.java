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

import com.nerddash.aulago.model.Aula;

@RequestScoped
public class AulaDao extends AbstractDaoClass<Aula> {

	@Inject
	public AulaDao(EntityManager em) {
		super(em);

	}

	@Deprecated
	public AulaDao() {
		this(null);
	}

	public Aula insert(Aula aula) {
		em.persist(aula);
		em.refresh(aula);
		return aula;
	}

	public Aula get(Long id) {
		return em.find(Aula.class, id);
	}

	public Aula update(Aula aula) {
		return em.merge(aula);
	}

	public boolean delete(Aula aula) {
		try {
			this.em.remove(aula);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public List<Aula> listAll() {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Aula> query = criteriaBuilder.createQuery(Aula.class);

		Root<Aula> root = query.from(Aula.class);
		Order[] orderBy = { criteriaBuilder.asc(root.get("id")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<Aula> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}
}
