package co.movilidadbogota.model;

import java.util.List;

public class Objeto {

	String alcance;
	String espeficicacion;
	String otrosAspectos;
	String constancia;
	List<BienesServicios> bienesServicios;

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getEspeficicacion() {
		return espeficicacion;
	}

	public void setEspeficicacion(String espeficicacion) {
		this.espeficicacion = espeficicacion;
	}

	public String getOtrosAspectos() {
		return otrosAspectos;
	}

	public void setOtrosAspectos(String otrosAspectos) {
		this.otrosAspectos = otrosAspectos;
	}

	public String getConstancia() {
		return constancia;
	}

	public void setConstancia(String constancia) {
		this.constancia = constancia;
	}

	public List<BienesServicios> getBienesServicios() {
		return bienesServicios;
	}

	public void setBienesServicios(List<BienesServicios> bienesServicios) {
		this.bienesServicios = bienesServicios;
	}
	
	
}
