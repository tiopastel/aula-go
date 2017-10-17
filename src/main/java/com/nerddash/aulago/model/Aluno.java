package com.nerddash.aulago.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ALUNOS")
public class Aluno extends Pessoa implements Serializable {

	private static final long serialVersionUID = -6213319960236426293L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Nivel nivel;

	@NotNull
	private String curso;

	@OneToMany
	private List<Busca> buscas;

	private int aulasContradas = 0;

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<Busca> getBuscas() {
		return buscas;
	}

	public void setBuscas(List<Busca> buscas) {
		this.buscas = buscas;
	}

	public int getAulasContradas() {
		return aulasContradas;
	}

	public void setAulasContradas(int aulasContradas) {
		this.aulasContradas = aulasContradas;
	}

}
