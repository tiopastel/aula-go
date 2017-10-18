package com.nerddash.aulago.model;

import static org.junit.Assert.assertThat;

import org.junit.Before;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class PessoaTest {

	private Aluno alunoInvalido;
	
	
	@Before
	public void setUp() {
		alunoInvalido = new Aluno();

		alunoInvalido.setNome("Flávio Arantes");
		alunoInvalido.setNivel(Nivel.SUPERIOR);
	}

	@Test
	public void testSetSenhaCom6Digitos() {		

		alunoInvalido.setSenha("100xxx");

		assertThat(alunoInvalido.getSenha(), not(equalTo("100xxx")));
		
	}
	
	@Test
	public void testSetSenhaNaoDeveCriptografarMenorQue6() {
		
		alunoInvalido.setSenha("100xx");

		assertThat(alunoInvalido.getSenha(), equalTo("100xx"));
		
	}

}
