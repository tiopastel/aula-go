package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Aluno;

@RequestScoped
public class AlunoDao extends AbstractDaoClass<Aluno> {

	@Inject
	public AlunoDao(EntityManager em) {
		super(em);
	}

	// CDI only use
	@Deprecated
	public AlunoDao() {
		this(null);
	}

	public Aluno insert(Aluno aluno) {
		return super.insert(aluno);
	}

	public Aluno get(Long id) {
		return super.get(id);
	}

	public Aluno update(Aluno aluno) {
		return super.update(aluno);
	}

	public boolean delete(Aluno aluno) {		
		return super.delete(aluno);
	}

	public List<Aluno> listAll() {
		return super.listAll(Aluno.class);
	}

}
