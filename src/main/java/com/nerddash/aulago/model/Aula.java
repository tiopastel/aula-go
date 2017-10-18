package com.nerddash.aulago.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "AULAS")
public class Aula implements Serializable {

	private static final long serialVersionUID = 1135012763643617812L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Nivel nivel;

	@NotEmpty
	private String materia;

	private Horario horario;

	@Min(value = 0)
	private BigDecimal preco;

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = BigDecimal.valueOf(preco);
	}

	public Long getId() {
		return id;
	}

}
