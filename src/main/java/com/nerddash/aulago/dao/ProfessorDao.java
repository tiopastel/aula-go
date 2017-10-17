package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;

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

		try {
			this.em.persist(professor);
			return professor;
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Professor get(Long id) {
		try {
			return this.em.find(Professor.class, id);
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Professor update(Professor professor) {
		try {
			return this.em.merge(professor);

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public boolean delete(Professor professor) {
		try {
			this.em.remove(professor);
			return true;

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Professor> listAll() {
		return em.createQuery("Select t from " + Professor.class.getAnnotation(Table.class).name() + " t").getResultList();
	}

}
