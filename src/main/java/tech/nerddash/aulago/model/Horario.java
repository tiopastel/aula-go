package tech.nerddash.aulago.model;

import java.io.Serializable;
import java.time.LocalTime;


public class Horario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6791695383253519765L;
	
	private LocalTime horaInicio;
	private LocalTime horaEncerra;
	

	public Horario(LocalTime horaInicio, LocalTime horaEncerra) {
		this.horaEncerra = horaEncerra;
		this.horaInicio = horaInicio;
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


}
