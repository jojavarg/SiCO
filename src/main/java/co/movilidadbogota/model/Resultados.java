package co.movilidadbogota.model;

import java.io.Serializable;

public class Resultados implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String idResultado;
	String resultadoEsperado;
	String volumen;

	public String getIdResultado() {
		return idResultado;
	}

	public void setIdResultado(String idResultado) {
		this.idResultado = idResultado;
	}

	public String getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

	public String getVolumen() {
		return volumen;
	}

	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	
}
