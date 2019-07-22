package co.movilidadbogota.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConexion {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conexion = DriverManager.getConnection("jdbc:oracle:thin:@192.168.100.78:1521:sicapitalpru", "co", "co");
			System.out.println("Conectado");
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Error en la conexión de la base de datos");
		}

	}
}
