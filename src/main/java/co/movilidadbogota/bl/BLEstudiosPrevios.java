package co.movilidadbogota.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import co.movilidadbogota.da.DaEstudiosPrevios;
import co.movilidadbogota.model.Dependencia;
import co.movilidadbogota.model.EstudiosPrevios;
import co.movilidadbogota.model.Garantia;
import co.movilidadbogota.model.LineaPlan;
import co.movilidadbogota.model.Modalidad;
import co.movilidadbogota.util.OracleConnectionUtils;

@ManagedBean
@RequestScoped
public class BLEstudiosPrevios {

	List<LineaPlan> lineaPlan = new ArrayList<LineaPlan>();
	private static DaEstudiosPrevios DAEstudiosPrevios = null;
	
	
	
	
	public List<LineaPlan> obtenerLineaPlan(HttpServletRequest request) {
		
		try {
			HttpSession session = request.getSession();
			UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");
			
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(), user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}
			return DAEstudiosPrevios.obtenerLineaPlan();

		} catch (Exception ex) {
			return null;
		}
			

	}




	public EstudiosPrevios mostrarCampos(HttpServletRequest request) {
		
		String linea = request.getParameter("linea");
		try {
			HttpSession session = request.getSession();
			UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");
			
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(), user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}
			return DAEstudiosPrevios.obtenerInformacionEstudioPrevio(linea);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public List<Modalidad> obtenerModalidad(HttpServletRequest request) {

		String modalidad = request.getParameter("modalidad");
		try {
			HttpSession session = request.getSession();
			UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(),
						user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}

			return DAEstudiosPrevios.obtenerModalidad(modalidad);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public List<Dependencia> obtenerDependencia(HttpServletRequest request) {
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String obtenerVigencia(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
		if (DAEstudiosPrevios == null) {
			Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(),
					user.getPassword());
			DAEstudiosPrevios = new DaEstudiosPrevios(map);
		}

		return DAEstudiosPrevios.obtenerVigencia();
	}

/**
 * 
 * @param request
 * @return
 */
	public List<EstudiosPrevios> mostrarBusquedaCampos(HttpServletRequest request) {
		String nosisco = request.getParameter("nosisco");
		String estado = request.getParameter("estado");
		String vigencia = request.getParameter("vigencia");
		String objetoestudios = request.getParameter("objetoestudios");
		
		try {
			HttpSession session = request.getSession();
			UsuarioBean user=(UsuarioBean)session.getAttribute("usuario");
			
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(), user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}
			return DAEstudiosPrevios.obtenerInformacionBusquedaEstudioPrevio(nosisco, estado,  vigencia, objetoestudios);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public List<Garantia> obtenerAmparo(HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(),
						user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}

			return DAEstudiosPrevios.obtenerAmparo();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public List<Garantia> obtenerGarantia(HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(),
						user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}

			return DAEstudiosPrevios.obtenerGarantia();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public List<Garantia> obtenerBase(HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			UsuarioBean user = (UsuarioBean) session.getAttribute("usuario");
			if (DAEstudiosPrevios == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(user.getLogin(),
						user.getPassword());
				DAEstudiosPrevios = new DaEstudiosPrevios(map);
			}

			return DAEstudiosPrevios.obtenerBase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}	
	
	
}
