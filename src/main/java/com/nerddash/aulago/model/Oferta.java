package com.nerddash.aulago.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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
	private Long id;

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
	
	private LocalTime horaInicio;
	
	private LocalTime horaTermino;

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
		if(dataFinal.isAfter(this.dataInicial)) {
			this.dataFinal = dataFinal;
		}
		
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

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(LocalTime horaTermino) {
		this.horaTermino = horaTermino;
	}

	@Deprecated
	public void setDataInicial(LocalDate dataInicial) {
		
	}

	@Override
	public String toString() {
		return "Oferta [id=" + id + ", professor=" + professor + ", dataInicial=" + dataInicial + ", dataFinal="
				+ dataFinal + ", aula=" + aula + ", preco=" + preco + ", horaInicio=" + horaInicio + ", horaTermino="
				+ horaTermino + "]";
	}

}
