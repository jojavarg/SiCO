package co.movilidadbogota.model;

import java.util.List;

public class Obligaciones {

	List<ObligacionesEspeciales> obligacionesEspeciales;
	List<ObligacionesSupervisor> obligacionesSupervisor;
	List<ObligacionesInterventor> obligacionesInterventor;

	public List<ObligacionesEspeciales> getObligacionesEspeciales() {
		return obligacionesEspeciales;
	}

	public void setObligacionesEspeciales(List<ObligacionesEspeciales> obligacionesEspeciales) {
		this.obligacionesEspeciales = obligacionesEspeciales;
	}

	public List<ObligacionesSupervisor> getObligacionesSupervisor() {
		return obligacionesSupervisor;
	}

	public void setObligacionesSupervisor(List<ObligacionesSupervisor> obligacionesSupervisor) {
		this.obligacionesSupervisor = obligacionesSupervisor;
	}

	public List<ObligacionesInterventor> getObligacionesInterventor() {
		return obligacionesInterventor;
	}

	public void setObligacionesInterventor(List<ObligacionesInterventor> obligacionesInterventor) {
		this.obligacionesInterventor = obligacionesInterventor;
	}

}
