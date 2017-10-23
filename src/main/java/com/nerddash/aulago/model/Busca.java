package com.nerddash.aulago.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "BUSCAS")
public class Busca extends AbstractEntityClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6858953411847427654L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Aluno aluno;

	private LocalDate dataInicial = LocalDate.now();

	@NotNull
	private LocalDate dataFinal;

	@NotNull
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Aula aula;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) throws Exception {
		if (dataFinal.isAfter(LocalDate.now())) {
			this.dataFinal = dataFinal;
		} else {
			throw new Exception("A data final deve ser uma data futura.");
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

	public void setId(Long id) {
		this.id = id;
	}

}
