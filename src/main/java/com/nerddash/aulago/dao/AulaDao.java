package com.nerddash.aulago.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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
		return super.insert(aula);
	}

	public Aula get(Long id) {
		return super.get(Aula.class, id);
	}

	public Aula update(Aula aula) {
		return super.update(aula);
	}

	public boolean delete(Aula aula) {
		return super.delete(aula);
	}

	public List<Aula> listAll() {
		return super.listAll(Aula.class);
	}
}
