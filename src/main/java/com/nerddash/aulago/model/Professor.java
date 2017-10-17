package com.nerddash.aulago.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "PROFESSORES")
public class Professor extends Pessoa implements Serializable {

	private static final long serialVersionUID = 2915126383016759855L;

	private String resumo;

	@NotNull
	private String formacao;

	private String lattes;

	private int leads;

	private float reputacao;

	private int aulasMinistradas;
	
	@OneToMany
	private List<Oferta> ofertas;

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getLattes() {
		return lattes;
	}

	public void setLattes(String lattes) {
		this.lattes = lattes;
	}

	public int getLeads() {
		return leads;
	}

	public void setLeads(int leads) {
		this.leads = leads;
	}

	public float getReputacao() {
		return reputacao;
	}

	public void setReputacao(float reputacao) {
		this.reputacao = reputacao;
	}

	public int getAulasMinistradas() {
		return aulasMinistradas;
	}

	public void setAulasMinistradas(int aulasMinistradas) {
		this.aulasMinistradas = aulasMinistradas;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public int addLeads(int leads) {
		this.leads += leads;
		return this.leads;
	}

	public int removeLead() {
		--this.leads;
		return this.leads;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

}
