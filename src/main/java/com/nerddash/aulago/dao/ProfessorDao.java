package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Professor;
import com.nerddash.aulago.security.CryptProducer;

public class ProfessorDao extends AbstractDaoClass<Professor> {

	private static final CryptProducer cryptProducer = new CryptProducer();

	@Inject
	public ProfessorDao(EntityManager em) {
		super(em);
	}

	/*
	 * CDI eyes only
	 * 
	 */
	@Deprecated
	public ProfessorDao() {
		this(null);
	}


	public Professor insert(Professor professor) {
		professor.setSenha(cryptProducer.encryptPassword(professor.getSenha()));
		return super.insert(professor);
	}

	public Professor get(Long id) {
		return super.get(Professor.class, id);
	}

	public List<Professor> listAll() {
		return super.listAll(Professor.class);
	}

}
