package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.security.CryptProducer;

@RequestScoped
public class AlunoDao extends AbstractDaoClass<Aluno> {

	private static final CryptProducer cryptProducer = new CryptProducer();

	@Inject
	public AlunoDao(EntityManager em) {
		super(em);
	}
	
	/*
	 * CDI eyes only
	 * 
	 */
	@Deprecated
	public AlunoDao() {
		this(null);
	}


	public Aluno insert(Aluno aluno) {
		aluno.setSenha(cryptProducer.encryptPassword(aluno.getSenha()));
		return super.insert(aluno);

	}
	
	public Aluno get(Long id) {
		return super.get(Aluno.class, id);
	}


	public List<Aluno> listAll() {
		return super.listAll(Aluno.class);
	}

}
