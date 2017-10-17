package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Table;

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
		try {
			this.em.persist(aluno);
			em.flush();
			return aluno;
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Aluno get(Long id) {
		try {
			return this.em.find(Aluno.class, id);
		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public Aluno update(Aluno aluno) {
		try {
			return this.em.merge(aluno);

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return null;
		}
	}

	public boolean delete(Aluno aluno) {
		try {
			this.em.remove(aluno);
			return true;

		} catch (Exception e) {
			System.out.println("ERRO: " + e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> listAll() {
		return em.createQuery("Select t from " + Aluno.class.getAnnotation(Table.class).name() + " t").getResultList();
	}

}
