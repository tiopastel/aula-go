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

import com.nerddash.aulago.model.Aluno;

@RequestScoped
public class AlunoDao {

	private final EntityManager em;

	@Inject
	public AlunoDao(EntityManager em) {
		this.em = em;
	}

	// CDI only use
	@Deprecated
	public AlunoDao() {
		this(null);
	}

	public Aluno insert(Aluno aluno) {

		this.em.persist(aluno);
		em.refresh(aluno);
		return aluno;

	}

	public Aluno get(Long id) {
		return this.em.find(Aluno.class, id);
	}

	public Aluno update(Aluno aluno) {
		return this.em.merge(aluno);
	}

	public boolean delete(Aluno aluno) {
		try {
			this.em.remove(aluno);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public List<Aluno> listAll() {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Aluno> query = criteriaBuilder.createQuery(Aluno.class);

		Root<Aluno> root = query.from(Aluno.class);
		Order[] orderBy = { criteriaBuilder.asc(root.get("nome")) };

		query.select(root);

		query.orderBy(orderBy);

		TypedQuery<Aluno> typedQuery = em.createQuery(query);

		return typedQuery.getResultList();
	}

}
