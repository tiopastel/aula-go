package tech.nerddash.aulago.model;

import java.io.Serializable;

public enum Nivel implements Serializable {
	

	BASICO("Básico"), MEDIO("Médio"), SUPERIOR("Superior");
	
	private String nivel;
	
	Nivel(String nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return nivel;
	}
	

}
