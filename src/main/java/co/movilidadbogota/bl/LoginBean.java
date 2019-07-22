/**
 * 
 */
package co.movilidadbogota.bl;

import java.io.Serializable;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

import co.movilidadbogota.da.DaLoginBean;

import co.movilidadbogota.util.OracleConnectionUtils;
import co.movilidadbogota.util.SessionUtils;

/**
 * @author jjvargasa
 *
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

    @Size(min = 2, max = 50)
    private String username;
    @Size(min = 2, max = 50)
    private String password;
    private String database;
    
	private static DaLoginBean DALoginBean = null;
    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
    
    public String index() {
          return "/pages/main.xhtml";
      }
   
	// logout event, invalidate session
	public String logout() {

		/*
		 * EnumOperacionesServicios enumServicios =
		 * EnumOperacionesServicios.PROCESO_RUAF_SABASS; BLGeneral blGeneral = new
		 * BLGeneral(enumServicios, datasourceResource);
		 */

		HttpSession sesion = SessionUtils.getSession();
		UsuarioBean usuario = (UsuarioBean) sesion.getAttribute("UsuarioBean");

		// BitacoraVo bVo = (BitacoraVo)blbitacora.creaBitacora("RUAF",
		// Constants.BLOGINOUT, "logout");

		/*
		 * try{ blGeneral.ruafBitacoraUsu(bVo.getBiSecuencia().longValue(),
		 * usuario.getUsuarioID()); }catch(Exception e){ e.printStackTrace(); }
		 */
		usuario = new UsuarioBean();
		sesion.invalidate();

		return "login";
	}

	public String usuarioSesion(HttpServletRequest request) throws Exception {
		/*
		 * if(SessionUtils.getSession().isNew()){ UIViewRoot vistaGeneracionpmptotal =
		 * new UIViewRoot();
		 * vistaGeneracionpmptotal.setViewId("/pages/generacion_pmptotal.xhtml");
		 * FacesUtils.obtieneContexto().setViewRoot(vistaGeneracionpmptotal);
		 * FacesUtils.obtieneContexto().responseComplete();
		 * 
		 * }
		 */
		System.out.println(request.getParameter("usuario"));
		this.setUsername(request.getParameter("usuario"));
		this.setPassword(request.getParameter("password"));
		this.setDatabase(request.getParameter("database"));
		
		java.sql.Timestamp fechaInicioProceso = null;
		try {
			// fecha Inicio Proceso
			java.util.Date utilDate = new java.util.Date();
			long lnMilisegundos = utilDate.getTime();
			fechaInicioProceso = new java.sql.Timestamp(lnMilisegundos);

			System.out.println("\n\n--------------------- Inicio Conexion Database-------------\n\n");

			if (DALoginBean == null) {
				Map<String, String> map = OracleConnectionUtils.obtenerParametrosConexion(this.getUsername(), this.getPassword());
				DALoginBean = new DaLoginBean(map);
				System.out.println("Conectado");
			}
			System.out.println("\n\n----------------- Fin Conexion Database ---------------\n\n");

			return DALoginBean.verificarUsuario(username);


		} catch (Throwable e) {

			String className = this.getClass().getName();
			String fuente = "Aplicacion Web";
			String nombreFuente = "SiCO";
			String nombreProceso = "Ingreso ";
			String mensajeError = "Nombre del metodo: usuarioSesion " + "\n" + "Nombre de la clase: " + className + "\n"
					+ "Mensaje de Error: " + e.getLocalizedMessage();

			System.out.println(fuente + "\n" + nombreFuente + "\n" + nombreProceso + "\n" + mensajeError);
			e.printStackTrace();


			return "error";
		}

	}

	public String persistirNombreUsuario() {

		HttpSession sesion = SessionUtils.getSession();
		UsuarioBean usuario = (UsuarioBean) sesion.getAttribute("UsuarioBean");
		setUsername(username);
		username = usuario.getLogin();
		return username;
	}
    
}

	/*  			
	 * Connection conexion = null;
Statement st = null;
ResultSet rs = null;
boolean estaConectado = false; 
String driverClass= ResourceUtil.getResourceValue("resources", "DriverClass"); 
	Class.forName(driverClass);
	conexion = DriverManager.getConnection(ResourceUtil.getResourceValue("resources", "ConexionURL"), username, password);
	*/






















	

	
	
	
	/*
	try {
		st = conexion.createStatement();
		rs = st.executeQuery("SELECT DISTINCT USUARIO from CO_USUARIOS where usuario LIKE '"+ username + "'");
		
	while (rs.next()) {
		UsuarioBean usuario = new UsuarioBean();
		usuario.setLogin(username);
		usuario.setFechaIngreso(new Date());
		usuario.setModulo(null);
		usuario.setEstado(null);
		FacesUtils.colocaBeanEnSesion("UsuarioBean", usuario);
		estaConectado = true;
	}
	
	if(estaConectado) {
		return "/index.jsp";
		
	}

	}catch (Exception ex) {
		System.out.println("Error: No existe usuarios con ese login");
		
		return "/index.jsp";
		
	}finally{
		rs.close();
		st.close();
		conexion.close();
	}*/
	


		/*
		//if (perfil == 12 || perfil == 13){
		if (nombrePerfil.equals("Administrador") || nombrePerfil.equals("AdministradorRuaf")) {
			
			System.out.println("\nPerfil Administrador o AdministradorRuaf:" + nombrePerfil);   		
      	UsuarioBean usuario = new UsuarioBean();
        //  usuario.setIngresoEntrada(true);
          usuario.setLogin(username);
          usuario.setFechaIngreso(new Date());
          usuario.setModulo("");
          usuario.setEstado("");
          
          //usuario.setUsuarioID(usuarioID);
          
          System.out.println("\nCrea sesion");
          
          FacesUtils.colocaBeanEnSesion("UsuarioBean", usuario);
          
          System.out.println("\nFn Crea sesion");
          
          System.out.println("\nInicia Bitacora");
          //BitacoraVo bVo = (BitacoraVo)blbitacora.creaBitacora("RUAF", Constants.BLOGININ, "colocarUsuarioSesion");
          
          try{
          	blGeneral.ruafBitacoraUsu(bVo.getBiSecuencia().longValue(), usuario.getUsuarioID());
          }catch(Exception e){
          	e.printStackTrace();
          }System.out.println("\nFin Bitacora");
          
          //if (perfil == 12){
          if (nombrePerfil.equals("Administrador")){
          	return "/pages/parametros.xhtml";
          //} else if (perfil == 13) {
          } else if (nombrePerfil.equals("AdministradorRuaf")){
          	return "/pages/generacion_pmptotal.xhtml";
          } else {
          	return "/index.jsp";
          }
		} else {
			
			System.out.println("\n Perfil: Sin Perfil Administrador");
			
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							infoperfil[1], ""));
			
			System.out.println("\nInicia Bitacora Sin Perfil Administrador");
			
			//BitacoraVo bVo = (BitacoraVo) blbitacora.creaBitacora("RUAF", Constants.BLOGINERR, "colocarUsuarioSesion");
			
			try {
				blGeneral.ruafBitacoraUsu(bVo.getBiSecuencia().longValue(), usuarioID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("\nFin Bitacora Sin Perfil Administrador");
			
			return "/index.jsp";
		}
	} else {
		FacesContext.getCurrentInstance().addMessage(
				null, new FacesMessage(FacesMessage.SEVERITY_WARN, infoperfil[1], ""));
		
		System.out.println("\n Inicio Bitacora  Sin Perfil");
		//BitacoraVo bVo = (BitacoraVo)blbitacora.creaBitacora("RUAF", Constants.BLOGINERR, "colocarUsuarioSesion");
		
      try{
      	blGeneral.ruafBitacoraUsu(bVo.getBiSecuencia().longValue(), usuarioID);
      }catch(Exception e){
      	e.printStackTrace();
      }
      
      System.out.println("\nFin Bitacora  Sin Perfil");
      return "/index.jsp";
	}*/