package com.nerddash.aulago.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "OFERTAS")
public class Oferta extends AbstractEntityClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7458430838857808802L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Professor professor;

	@NotNull
	private LocalDate dataInicial = LocalDate.now();

	@NotNull
	private LocalDate dataFinal;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Aula aula;

	@Min(value = 0)
	private BigDecimal preco;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = BigDecimal.valueOf(preco);
	}

	public void setId(Long id) {
		this.id = id;
	}

}
