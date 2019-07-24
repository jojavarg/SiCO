/**
 * 
 */
package co.movilidadbogota.model;

import java.util.Date;

/**
 * @author jjvargasa
 *
 */
public class EstudiosPrevios {

	LineaPlan lineaplan;
	String argumento;
	String resultado;
	String numeroSisco;
	int vigencia;
	String estado;
	boolean viabilidad;
	int noViabilidad;

	String objeto;	
	
	
	General general;
	Conveniencia conveniencia;
	Objeto objetos;
	Condiciones condiciones;
	Obligaciones obligaciones;
	Productos productos;
	Garantia garantia;
	Respuesta respuesta;

	
	ResponsableDirector responsableDirector;
	ResponsableOrdenador responsableOrdenador;
	


	public ResponsableDirector getResponsableDirector() {
		return responsableDirector;
	}

	public void setResponsableDirector(ResponsableDirector responsableDirector) {
		this.responsableDirector = responsableDirector;
	}

	public ResponsableOrdenador getResponsableOrdenador() {
		return responsableOrdenador;
	}

	public void setResponsableOrdenador(ResponsableOrdenador responsableOrdenador) {
		this.responsableOrdenador = responsableOrdenador;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

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

	public int getNoViabilidad() {
		return noViabilidad;
	}

	public void setNoViabilidad(int noViabilidad) {
		this.noViabilidad = noViabilidad;
	}

	public boolean isViabilidad() {
		return viabilidad;
	}

	public void setViabilidad(boolean viabilidad) {
		this.viabilidad = viabilidad;
	}

	public LineaPlan getLineaplan() {
		return lineaplan;
	}

	public void setLineaplan(LineaPlan lineaplan) {
		this.lineaplan = lineaplan;
	}

	public String getNumeroSisco() {
		return numeroSisco;
	}

	public void setNumeroSisco(String numeroSisco) {
		this.numeroSisco = numeroSisco;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public General getGeneral() {
		return general;
	}

	public void setGeneral(General general) {
		this.general = general;
	}

	public Conveniencia getConveniencia() {
		return conveniencia;
	}

	public void setConveniencia(Conveniencia conveniencia) {
		this.conveniencia = conveniencia;
	}

	public Objeto getObjetos() {
		return objetos;
	}

	public void setObjetos(Objeto objetos) {
		this.objetos = objetos;
	}

	public Condiciones getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(Condiciones condiciones) {
		this.condiciones = condiciones;
	}

	public Obligaciones getObligaciones() {
		return obligaciones;
	}

	public void setObligaciones(Obligaciones obligaciones) {
		this.obligaciones = obligaciones;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public Garantia getGarantia() {
		return garantia;
	}

	public void setGarantia(Garantia garantia) {
		this.garantia = garantia;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	
	
	
	
	
	
	
	
	
	

}
