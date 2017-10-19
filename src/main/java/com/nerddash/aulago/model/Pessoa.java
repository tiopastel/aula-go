package com.nerddash.aulago.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.nerddash.aulago.security.CryptProducer;

@MappedSuperclass
public abstract class Pessoa implements Serializable {

	private static final long serialVersionUID = -3987080606101062491L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Transient
	private static final CryptProducer cryptProducer = new CryptProducer();

	@NotEmpty
	protected String nome;

	@NotNull
	@Email
	@Column(unique=true)
	protected String email;

	@Length(min = 6)
	protected String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		if (senha.length() > 5) {
			this.senha = cryptProducer.encryptPassword(senha);
		} else {
			this.senha = senha;
		}
	}

	public Long getId() {
		return id;
	}


}
