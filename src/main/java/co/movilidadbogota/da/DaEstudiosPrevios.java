package co.movilidadbogota.da;

import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import co.movilidadbogota.model.Condiciones;
import co.movilidadbogota.model.EstudiosPrevios;
import co.movilidadbogota.model.Garantia;
import co.movilidadbogota.model.General;
import co.movilidadbogota.model.LineaPlan;
import co.movilidadbogota.model.Modalidad;
import co.movilidadbogota.util.DbCommand;
import co.movilidadbogota.util.OracleDatabase;

public class DaEstudiosPrevios {

	/**
	 * Definicion del identificador de la base de datos
	 */
	private OracleDatabase _database = null;

	/**
	 * Definicion del identificador unico durante el proceso
	 */
	private String UUID = "";

	/**
	 * 
	 * @param propiedadesDatasource
	 * @throws Exception
	 */
	public DaEstudiosPrevios(Map<String, String> propiedadesDatasource) throws Exception {
		_database = new OracleDatabase(propiedadesDatasource);

		Connection conn = _database.GetConnection(propiedadesDatasource);
		_database.setConn(conn);
		if (conn == null) {
			throw new Exception("error estableciendo conexion a base de datos");
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<LineaPlan> obtenerLineaPlan() throws Exception {
		ResultSet rs = null;
		List<LineaPlan> listaLineaPlan = new ArrayList<LineaPlan>();
		try {
			DbCommand command = _database.GetXmlCommandMap("OBTENER_LINEAS");
			rs = _database.ExecuteResultSet(command);
			
					
			while (rs.next()) {
				LineaPlan lineaPlan = new LineaPlan();
				lineaPlan.setNumeroOrden(rs.getString(1));
				lineaPlan.setObjeto(rs.getString(2));
				
				listaLineaPlan.add(lineaPlan);
				
			}
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios" + "\n Nombre de metodo: obtenerLineaPlan"
					+ "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}
		System.out.println("Cantidad de registros " + listaLineaPlan.size());
		return listaLineaPlan;
	}

	/**
	 * 
	 * @param linea
	 * @return
	 * @throws Exception
	 */
	public EstudiosPrevios obtenerInformacionEstudioPrevio(String linea) throws Exception {
		ResultSet rs = null;
		List<LineaPlan> listaLineaPlan = new ArrayList<LineaPlan>();
		try {
			DbCommand command = _database.GetXmlCommandMap("CARGAR_ESTUDIO_PREVIO");
			_database.AddInXmlParameter(command, "@linea", linea);

			rs = _database.ExecuteResultSet(command);
			EstudiosPrevios estudiosPrevios = new EstudiosPrevios();

			while (rs.next()) {
				estudiosPrevios.setNoViabilidad(rs.getInt(1));
				
				List listaLineaplan = new ArrayList<LineaPlan>();
				
				LineaPlan lp = new LineaPlan();
				lp.setNumeroOrden(linea);
				lp.setObjeto(rs.getString(11));
				listaLineaplan.add(lp);
				
				//Carga Inicio
				
				estudiosPrevios.setArgumento(rs.getString(9));
				estudiosPrevios.setResultado(rs.getString(10));
				estudiosPrevios.setObjeto(rs.getString(11));
				
				//Carga General
				
				General general = new General();
				general.setIdEntidad(rs.getString(3));
				general.setIdEntidad(rs.getString(3));
				general.setNombreEntidad(rs.getString(4));
				general.setIdUnidad(rs.getString(5));
				general.setNombreUnidad(rs.getString(6));
				general.setContacto(rs.getString(13));
				general.setNombreContacto(rs.getString(14));
				general.setIdAreaOrigen(rs.getString(7));
				general.setAreaOrigen(rs.getString(8));
				general.setListaLineaplan(listaLineaplan);
				
				estudiosPrevios.setGeneral(general);
				
				
				
				//Carga Condiciones
				
				Condiciones condiciones = new Condiciones();
				condiciones.setPlazoEjecucion(rs.getString(15));
				condiciones.setTipoPlazoEjecucion(rs.getString(16));
				condiciones.setValordisp(rs.getString(12));	
				
				estudiosPrevios.setCondiciones(condiciones);
				
			

			}
			return estudiosPrevios;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios"
					+ "\n Nombre de metodo: obtenerInformacionEstudioPrevio" + "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}

	}
	/**
	 * 
	 * @param modalidad2
	 * @return
	 * @throws Exception
	 */
	public List<Modalidad> obtenerModalidad(String modalidad2) throws Exception {
		
		ResultSet rs = null;
		List<Modalidad> listaModalidad = new ArrayList<Modalidad>();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");

		String fechaActual = format.format( new Date()   );

		try {
			DbCommand command = _database.GetXmlCommandMap("OBTENER_MODALIDADES");
			_database.AddInXmlParameter(command, "@fechaInicial", fechaActual);
			_database.AddInXmlParameter(command, "@fechaFinal", fechaActual);

			rs = _database.ExecuteResultSet(command);
			

			while (rs.next()) {
				Modalidad modalidad = new Modalidad();
				modalidad.setArgumento(rs.getString(1));
				modalidad.setResultado(rs.getString(2));
				
				listaModalidad.add(modalidad);
				
			}
			
			
			return listaModalidad;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios"
					+ "\n Nombre de metodo: obtenerModalidad" + "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String obtenerVigencia() throws Exception {
		ResultSet rs = null;

		try {
			DbCommand command = _database.GetXmlCommandMap("OBTENER_VIGENCIA");
			rs = _database.ExecuteResultSet(command);
			
					
			while (rs.next()) {
				return rs.getString(1);			
			}
			return null;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios" + "\n Nombre de metodo: obtenerLineaPlan"
					+ "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}

	}



	public List<EstudiosPrevios> obtenerInformacionBusquedaEstudioPrevio(String nosisco, String estado, String vigencia,
			String objetoestudios) throws Exception {

		ResultSet rs = null;
		List<EstudiosPrevios> listaEstudiosPrevios = new ArrayList<EstudiosPrevios>();
		try {
			DbCommand command = _database.GetXmlCommandMap("BUSCAR_ESTUDIOS_PREVIOS");
			_database.AddInXmlParameter(command, "@nosisco", nosisco);
			_database.AddInXmlParameter(command, "@estado", estado);
			_database.AddInXmlParameter(command, "@vigencia", vigencia);
			_database.AddInXmlParameter(command, "@objetoestudios", objetoestudios);
			

			rs = _database.ExecuteResultSet(command);


			
			while (rs.next()) {
				EstudiosPrevios estudiosPrevios = new EstudiosPrevios();
				estudiosPrevios.setNoViabilidad(rs.getInt(1));
				// estudiosPrevios.getLineaplan().setNumeroOrden(linea);
				
				
				
				List listaLineaplan = new ArrayList<LineaPlan>();
				
				LineaPlan lp = new LineaPlan();
				lp.setNumeroOrden("");
				lp.setObjeto(rs.getString(11));
				listaLineaplan.add(lp);
				
				
				
				
				//Carga Inicio
				
				estudiosPrevios.setArgumento(rs.getString(9));
				estudiosPrevios.setResultado(rs.getString(10));
				estudiosPrevios.setObjeto(rs.getString(11));
				
				//Carga General
				
				General general = new General();
				general.setIdEntidad(rs.getString(3));
				general.setNombreEntidad(rs.getString(4));
				general.setIdUnidad(rs.getString(5));
				general.setNombreUnidad(rs.getString(6));
				general.setIdAreaOrigen(rs.getString(7));
				general.setAreaOrigen(rs.getString(8));
				general.setContacto(rs.getString(13));
				general.setNombreContacto(rs.getString(14));
				general.setListaLineaplan(listaLineaplan);
				
				estudiosPrevios.setGeneral(general);
				
				//Carga Condiciones
				
				Condiciones condiciones = new Condiciones();
				condiciones.setValordisp(rs.getString(12));	
				condiciones.setPlazoEjecucion(rs.getString(15));
				condiciones.setTipoPlazoEjecucion(rs.getString(16));

				
				estudiosPrevios.setCondiciones(condiciones);

				listaEstudiosPrevios.add(estudiosPrevios);

			}
			return listaEstudiosPrevios;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios"
					+ "\n Nombre de metodo: obtenerInformacionBusquedaEstudioPrevio" + "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}

	}
	
	
/**
 * 	
 * @return
 * @throws Exception
 */
public List<Garantia> obtenerGarantia() throws Exception {
		
		ResultSet rs = null;
		List<Garantia> listaGarantia = new ArrayList<Garantia>();

		try {
			DbCommand command = _database.GetXmlCommandMap("OBTENER_GARANTIA");


			rs = _database.ExecuteResultSet(command);
			

			while (rs.next()) {
				Garantia garantia = new Garantia();
				garantia.setIdGarantia(rs.getString(1));
				garantia.setDescripcionGarantia(rs.getString(2));
				
				listaGarantia.add(garantia);
				
			}
			
			
			return listaGarantia;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios"
					+ "\n Nombre de metodo: obtenerGarantia" + "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}
	}

/**
 * 	
 * @return
 * @throws Exception
 */
public List<Garantia> obtenerAmparo() throws Exception {
		
		ResultSet rs = null;
		List<Garantia> listaGarantia = new ArrayList<Garantia>();

		try {
			DbCommand command = _database.GetXmlCommandMap("OBTENER_AMPARO");


			rs = _database.ExecuteResultSet(command);
			

			while (rs.next()) {
				Garantia garantia = new Garantia();
				garantia.setIdAmparo(rs.getString(1));
				garantia.setDescripcionAmparo(rs.getString(2));
				
				listaGarantia.add(garantia);
				
			}
			
			
			return listaGarantia;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios"
					+ "\n Nombre de metodo: obtenerAmparo" + "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}
	}
/**
 * 	
 * @return
 * @throws Exception
 */
public List<Garantia> obtenerBase() throws Exception {
		
		ResultSet rs = null;
		List<Garantia> listaGarantia = new ArrayList<Garantia>();

		try {
			DbCommand command = _database.GetXmlCommandMap("OBTENER_BASE");


			rs = _database.ExecuteResultSet(command);
			

			while (rs.next()) {
				Garantia garantia = new Garantia();
				garantia.setIdBase(rs.getString(1));
				garantia.setDescripcionBase(rs.getString(2));
				
				listaGarantia.add(garantia);
				
			}
			
			
			return listaGarantia;
		} catch (Exception e) {

			throw new Exception("Nombre de la clase: DaEstudiosPrevios"
					+ "\n Nombre de metodo: obtenerAmparo" + "\n Error: " + e.getMessage());

		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
			}
			try {
				_database.getConn().close();
			} catch (Exception ex) {
			}

		}
	}
	

}
