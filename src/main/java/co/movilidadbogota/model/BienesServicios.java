package co.movilidadbogota.model;

import java.io.Serializable;

public class BienesServicios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String tipoSolicitudadquisicion;

	String idBienesServicios;
	String descripcionElemento;
	String unidadMedida;
	String cantidad;
	boolean mvu;
	boolean aprobacion;
	String cantidadAprobada;
	String valorUnitario;

	public String getTipoSolicitudadquisicion() {
		return tipoSolicitudadquisicion;
	}

	public void setTipoSolicitudadquisicion(String tipoSolicitudadquisicion) {
		this.tipoSolicitudadquisicion = tipoSolicitudadquisicion;
	}

	public String getIdBienesServicios() {
		return idBienesServicios;
	}

	public void setIdBienesServicios(String idBienesServicios) {
		this.idBienesServicios = idBienesServicios;
	}

	public String getDescripcionElemento() {
		return descripcionElemento;
	}

	public void setDescripcionElemento(String descripcionElemento) {
		this.descripcionElemento = descripcionElemento;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isMvu() {
		return mvu;
	}

	public void setMvu(boolean mvu) {
		this.mvu = mvu;
	}

	public boolean isAprobacion() {
		return aprobacion;
	}

	public void setAprobacion(boolean aprobacion) {
		this.aprobacion = aprobacion;
	}

	public String getCantidadAprobada() {
		return cantidadAprobada;
	}

	public void setCantidadAprobada(String cantidadAprobada) {
		this.cantidadAprobada = cantidadAprobada;
	}

	public String getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

}
