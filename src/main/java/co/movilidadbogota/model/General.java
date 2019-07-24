package co.movilidadbogota.model;

import java.util.Date;
import java.util.List;

public class General {

	String direccion;
	String directorOficina;
	Date fechaElaboracion;
	String idEntidad;
	String idUnidad;
	List<LineaPlan> listaLineaplan;
	String nombreEntidad;
	String nombreUnidad;
	
	
	String contacto;
	String nombreContacto;

	String areaOrigen;
	String idAreaOrigen;
	
	

	public String getContacto() {
		return contacto;
	}

	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getAreaOrigen() {
		return areaOrigen;
	}

	public void setAreaOrigen(String areaOrigen) {
		this.areaOrigen = areaOrigen;
	}

	public String getIdAreaOrigen() {
		return idAreaOrigen;
	}

	public void setIdAreaOrigen(String idAreaOrigen) {
		this.idAreaOrigen = idAreaOrigen;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getDirectorOficina() {
		return directorOficina;
	}

	public Date getFechaElaboracion() {
		return fechaElaboracion;
	}

	public String getIdEntidad() {
		return idEntidad;
	}

	public String getIdUnidad() {
		return idUnidad;
	}

	public List<LineaPlan> getListaLineaplan() {
		return listaLineaplan;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setDirectorOficina(String directorOficina) {
		this.directorOficina = directorOficina;
	}

	public void setFechaElaboracion(Date fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}

	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}

	public void setListaLineaplan(List<LineaPlan> listaLineaplan) {
		this.listaLineaplan = listaLineaplan;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}
	
	
	
	
}
