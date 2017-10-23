package com.nerddash.aulago.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.nerddash.aulago.model.Aluno;
import com.nerddash.aulago.security.CryptProducer;


public class AlunoDao extends AbstractDaoClass<Aluno> {
	
	private static final CryptProducer cryptProducer = new CryptProducer();

	@Inject
	public AlunoDao(EntityManager em) {
		super(em);
	}

	// CDI only use
	@Deprecated
	public AlunoDao() {
		this(null);
	}
	
	public Aluno insert(Aluno aluno) throws Exception {
		aluno.setSenha(cryptProducer.encryptPassword(aluno.getSenha()));
		try {
			return super.insert(aluno);
		} catch (Exception e) {
			throw e;
		}
	}

	public Aluno get(Long id) {
		return super.get(Aluno.class, id);
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
