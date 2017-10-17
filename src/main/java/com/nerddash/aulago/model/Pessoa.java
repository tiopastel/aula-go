package com.nerddash.aulago.model;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.nerddash.aulago.security.CryptProducer;

public abstract class Pessoa implements Serializable{

	
	private static final long serialVersionUID = -3987080606101062491L;
	
	private final CryptProducer cryptProducer;
	
	@Inject
	public Pessoa(CryptProducer cryptProducer) {
		this.cryptProducer = cryptProducer;
	}
	
	public Pessoa() {
		this(null);
	}

	protected String nome;

	protected int cpf;
	
	protected String endereco;

	protected Date dataNascimento;

	protected Sexo sexo;

	protected String email;

	protected String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = cryptProducer.encryptPassword(senha);
	}

	public enum Sexo {
		MASCULINO("Masculino"), FEMININO("Feminino");

		private String value;

		Sexo(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
