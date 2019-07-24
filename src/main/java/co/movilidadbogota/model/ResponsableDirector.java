package co.movilidadbogota.model;

import java.io.Serializable;

public class ResponsableDirector implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		String idCompania;
		String descripcionCompania;

		String idDependencia;
		String descripcionDependencia;

		String funcionario;
		String cargo;

		public String getIdCompania() {
			return idCompania;
		}

		public void setIdCompania(String idCompania) {
			this.idCompania = idCompania;
		}

		public String getDescripcionCompania() {
			return descripcionCompania;
		}

		public void setDescripcionCompania(String descripcionCompania) {
			this.descripcionCompania = descripcionCompania;
		}

		public String getIdDependencia() {
			return idDependencia;
		}

		public void setIdDependencia(String idDependencia) {
			this.idDependencia = idDependencia;
		}

		public String getDescripcionDependencia() {
			return descripcionDependencia;
		}

		public void setDescripcionDependencia(String descripcionDependencia) {
			this.descripcionDependencia = descripcionDependencia;
		}

		public String getFuncionario() {
			return funcionario;
		}

		public void setFuncionario(String funcionario) {
			this.funcionario = funcionario;
		}

		public String getCargo() {
			return cargo;
		}

		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
	
	
}
