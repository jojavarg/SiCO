package co.movilidadbogota.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import co.movilidadbogota.comun.ResourceUtil;

public class OracleConnectionUtils {

	
	Connection conexion = null;
	
	private Connection OracleConnectionUtils(String username, String password) throws Exception{

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conexion = DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.78:1521:sicapitalpru", username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("No se pudo conectar a la base de datos ");
		}
		
		return conexion;
	}
	
	
	public static Map<String,String> obtenerParametrosConexion(String usuario, String password) throws Exception{

			Map<String, String> map = new HashMap();
			map.put("DRIVER_CLASS", ResourceUtil.getResourceValue("resources", "DriverClass"));
			map.put("CONNECTION_URL", ResourceUtil.getResourceValue("resources", "ConexionURL"));
			map.put("USER_NAME", usuario);
			map.put("PASSWORD", password);
		
			return map;

	}
	
}
