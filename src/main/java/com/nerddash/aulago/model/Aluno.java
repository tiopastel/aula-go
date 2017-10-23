package com.nerddash.aulago.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "ALUNOS")
public class Aluno extends Pessoa {

	private static final long serialVersionUID = -6213319960236426293L;

	@NotNull
	private Nivel nivel;

	@NotNull @NotEmpty
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

	@Override
	public String toString() {
		return "Aluno [nivel=" + nivel + ", curso=" + curso + ", buscas=" + buscas + ", aulasContradas="
				+ aulasContradas + ", id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}

}
