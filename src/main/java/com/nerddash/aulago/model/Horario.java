package com.nerddash.aulago.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class Horario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6791695383253519765L;
	
	private LocalTime horaInicio;
	private LocalTime horaEncerra;
	private Duration duracao;

	public Horario(LocalTime horaInicio, LocalTime horaEncerra) {
		this.horaEncerra = horaEncerra;
		this.horaInicio = horaInicio;
		this.duracao = Duration.between(horaInicio, horaEncerra);
	}
	
	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraEncerra() {
		return horaEncerra;
	}

	public void setHoraEncerra(LocalTime horaEncerra) {
		this.horaEncerra = horaEncerra;
	}

	public Duration getDuracao() {
		return this.duracao;
	}

	@Deprecated
	public void setDuracao(Duration duracao) {
	}

}
