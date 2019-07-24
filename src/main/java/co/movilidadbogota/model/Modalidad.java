package co.movilidadbogota.model;

import java.io.Serializable;

public class Modalidad implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String argumento;
	String resultado;

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	


}
