package co.movilidadbogota.model;

import java.io.Serializable;

public class Dependencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624369129223467594L;
	String descripcion;
	String codigoDependencia;
	String dep2;
	String ubicacion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigoDependencia() {
		return codigoDependencia;
	}

	public void setCodigoDependencia(String codigoDependencia) {
		this.codigoDependencia = codigoDependencia;
	}

	public String getDep2() {
		return dep2;
	}

	public void setDep2(String dep2) {
		this.dep2 = dep2;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
