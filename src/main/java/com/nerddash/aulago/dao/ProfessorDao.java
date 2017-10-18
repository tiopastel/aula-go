package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.nerddash.aulago.model.Professor;

public class ProfessorDao {
	private final EntityManager em;

	@Inject
	public ProfessorDao(EntityManager em) {
		this.em = em;
	}

	// CDI only use
	@Deprecated
	public ProfessorDao() {
		this(null);
	}

	public Professor insert(Professor professor) {

		this.em.persist(professor);
		em.refresh(professor);
		return professor;

	}

	public Professor get(Long id) {

		return this.em.find(Professor.class, id);
	}

	public Professor update(Professor professor) {
		
			return this.em.merge(professor);
	}

	public boolean delete(Professor professor) {
		try {
			this.em.remove(professor);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public List<Professor> listAll() {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Professor> query = criteriaBuilder.createQuery(Professor.class);

		Root<Professor> root = query.from(Professor.class);
		Order[] orderBy = { criteriaBuilder.asc(root.get("nome")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<Professor> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

}
