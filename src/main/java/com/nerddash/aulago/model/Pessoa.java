package com.nerddash.aulago.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.nerddash.aulago.security.CryptProducer;

@MappedSuperclass
public abstract class Pessoa implements Serializable{

	
	private static final long serialVersionUID = -3987080606101062491L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	private static final CryptProducer cryptProducer = new CryptProducer();	

	@NotNull
	protected String nome;

	@NotNull
	protected int cpf;
	
	@NotNull
	protected String endereco;

	@NotNull
	protected Date dataNascimento;

	@NotNull
	protected Sexo sexo;

	@NotNull
	protected String email;

	@NotNull
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
