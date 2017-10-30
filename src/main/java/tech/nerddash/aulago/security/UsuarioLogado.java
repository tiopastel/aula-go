/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tech.nerddash.aulago.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import tech.nerddash.aulago.model.Pessoa;


/**
 *
 * @author flavio
 */
@SessionScoped
@Named
public class UsuarioLogado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5200448618919795566L;

	private Pessoa usuario;

	public static enum papeis {
		ALUNO, PROFESSOR, ADMIN
	};

	private papeis papel;

	public boolean isLogado() {
		return this.usuario != null;
	}

	public void fazLogin(Pessoa usuario) {
		this.usuario = usuario;
	}

	public void desloga() {
		this.usuario = null;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public papeis getPapel() {
		return papel;
	}

	public void setPapel(papeis papel) {
		this.papel = papel;
	}

}
