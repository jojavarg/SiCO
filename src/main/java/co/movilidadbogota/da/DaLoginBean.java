package co.movilidadbogota.da;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import org.apache.log4j.Logger;

import co.movilidadbogota.util.DbCommand;
import co.movilidadbogota.util.OracleDatabase;

public class DaLoginBean {

    /**
     * Definicion del identificador de la base de datos
     */
	private OracleDatabase _database = null;
	
    /**
     * Definicion del identificador unico durante el proceso
     */
    private String UUID = "";
	
    /**
     * Definicion de logger estandar para toda la aplicacion
     */
    static Logger log = Logger.getLogger(DaLoginBean.class);
    
    /**
     * Constructor de la clase
     */
	public DaLoginBean(){
		_database = new OracleDatabase();

	}
	

    /**
	 * Método que permite establecer una conexión independiente del pool de conexiones del contenedor.<br/>
     * <br/>
     * <b>Advertencia:</b> Este método solo debe ser utilizado bajo condiciones especiales que requieran que las conexiones sean
     * administradas por los procesos del negocio
     * 
	 * @param propiedadesDatasource 
	 * <pre> 
	 * 		DRIVER_CLASS=?
	 * 		CONNECTION_URL=?
	 * 		USER_NAME=?
	 * 		PASSWORD=? 	
	 * </pre>
	 */
    public DaLoginBean(Map<String, String> propiedadesDatasource) throws Exception {
    	_database = new OracleDatabase(propiedadesDatasource);
    	
		Connection conn = _database.GetConnection(propiedadesDatasource);
		_database.setConn(conn);
		if(conn == null) {
			throw new Exception("error estableciendo conexion a base de datos");
		}
	}

    
    
    
    
    
	/**
     * Ingreso de la información del Log de monitoría
     */
/*
    public LogParametros registrarLog(LogParametros params) throws Exception {
		
	   	ResultSet rs = null;

		try {

			System.out.println("exec dbo.LP_update_log_procesos '"+params.getLogId()+"','"+params.getReferencia()+"','"+params.getFuente()+"','"+params.getNombreProceso()
					+"',"+params.getEstadoProceso()+",'"+params.getParametros()+"',' "+params.getUsuario()+"', '"+params.getObservacion()+"',' "+params.getiP()+"','"+params.getFin()+"'");
            DbCommand command = _database.GetXmlCommand("LOG_PROCESO_MONITOREO");
             _database.AddInXmlParameter(command, "@lpm_log_id", params.getLogId());
             _database.AddInXmlParameter(command, "@lpm_referencia", params.getReferencia());
             _database.AddInXmlParameter(command, "@lpm_fuente", params.getFuente());
             _database.AddInXmlParameter(command, "@lpm_nombreProceso", params.getNombreProceso());
             _database.AddInXmlParameter(command, "@lpm_estado", params.getEstadoProceso());
             _database.AddInXmlParameter(command, "@lpd_parametros", params.getParametros());
             _database.AddInXmlParameter(command, "@lpd_usuario", params.getUsuario());
             _database.AddInXmlParameter(command, "@lpd_observacion", params.getObservacion());
             _database.AddInXmlParameter(command, "@lpd_IP", params.getiP());
             _database.AddInXmlParameter(command, "@FIN", params.getFin());
            
            rs =  _database.ExecuteResultSet(command);
            
            if (rs.next()) {
            	params.setLogId(rs.getString(1));
            	params.setEstadoProceso(rs.getInt(2));
            	params.setNombreProceso(rs.getString(3));
            	params.setUsuario(rs.getString(4));
            	params.setReferencia(rs.getString(5));
            }
            
        } 
		catch (Exception e) {

            throw new Exception("Nombre de la clase: LogProcesos"
					+"\n Nombre de metodo: registrarLog"
					+"\n Error: "+e.getMessage());
            
        } finally {
        	try { rs.close(); } catch(Exception ex) { }
        	try { _database.getConn().close(); } catch(Exception ex) { }

        }
		return params;
	}
*/



     /**
      * 
      * @param usuario
      * @return
      * @throws Exception
      */
	public String verificarUsuario(String usuario) throws Exception{
		ResultSet rs = null;
		try {
			DbCommand command = _database.GetXmlCommandMap("VERIFICAR_USUARIO");
             _database.AddInXmlParameter(command, "@usuario", usuario);
             rs =  _database.ExecuteResultSet(command);
            
            if (rs.next()) {
            	return "main";

            }
            return "error";
        } 
		catch (Exception e) {

            throw new Exception("Nombre de la clase: DaLoginBean"
					+"\n Nombre de metodo: verificarUsuario"
					+"\n Error: "+e.getMessage());
     
        } finally {
        	try { rs.close(); } catch(Exception ex) { }
        	try { _database.getConn().close(); } catch(Exception ex) { }

        }
	}
	
	
	/**
	 * Método que ejecuta la consulta para evaluar el estado del Proceso e indica que si algún proceso está en ejecución  para la misma Dirección  IP
	 * @param referencia: Nombre del Proceso - Número de identificación
	 * @param direccionIP: Dirección Ip del cliente
	 * @return boolean verificacíon de ejecución del proceso
	 * @throws Exception En caso de error del método
	 */
	public boolean enEjecucionDAO(String referencia, String direccionIP) throws Exception{
		ResultSet rs = null;
		try {
			DbCommand command = _database.GetXmlCommand("VERIFICAR_ESTADO_MONITOREO_IP");
             _database.AddInXmlParameter(command, "@referencia", referencia);
             _database.AddInXmlParameter(command, "@ip", direccionIP);
             rs = _database.ExecuteResultSet(command);
            
            if (rs.next()) {
            	return (rs.getInt(1) == 1);
            }
            return false;
        } 
		catch (Exception e) {

            throw new Exception("Nombre de la clase: LogProcesos"
					+"\n Nombre de metodo: enEjecucionDAO 2"
					+"\n Error: "+e.getMessage());
     
        } finally {
        	try { rs.close(); } catch(Exception ex) { }
        	try { _database.getConn().close(); } catch(Exception ex) { }

        }
	}

	/**
	 * Método que ejecuta la consulta para revisar el estado de un proceso en monitoria 
	 * @param id: Documento de identificación
	 * @param referencia:Nombre del proceso
	 * @return int estado del proceso
	 * @throws Exception En caso de error del método
	 */
	public  int consultarEstadoProcesoLog(String id, String referencia) throws Exception{

	   	ResultSet rs = null;
	   	int ultimoEstado=-1;
 
		try {
			DbCommand command = _database.GetXmlCommand("CONSULTAR_ESTADO_MONITOREO");
             _database.AddInXmlParameter(command, "@id", referencia+'-'+id);
            rs =  _database.ExecuteResultSet(command);
            
            if (rs.next()) {
            	ultimoEstado = rs.getInt(1);
            }

        } 
		catch (Exception e) {

            throw new Exception("Nombre de la clase: LogProcesos"
					+"\n Nombre de metodo: consultarEstadoProcesoLog"
					+"\n Error: "+e.getMessage());
     
        } finally {
        	try { rs.close(); } catch(Exception ex) { }
        	try { _database.getConn().close(); } catch(Exception ex) { }

        }
		return ultimoEstado;
	}

	public boolean esUltimoProcesoEjecucion(String id, String nombreProceso) throws Exception {
		ResultSet rs = null;
		try {
			DbCommand command = _database.GetXmlCommand("VERIFICAR_ULTIMO_PROCESO_MONITOREO");
            _database.AddInXmlParameter(command, "@nombre_proceso", nombreProceso);
            _database.AddInXmlParameter(command, "@log_id", id);
            rs = _database.ExecuteResultSet(command);
            
            if (rs.next()) {
            	return (rs.getInt(1) == 1);
            }
            return false;
        } 
		catch (Exception e) {

            throw new Exception("Nombre de la clase: LogProcesos"
					+"\n Nombre de metodo: enEjecucion"
					+"\n Error: "+e.getMessage());
     
        } finally {
        	try { rs.close(); } catch(Exception ex) { }
        	try { _database.getConn().close(); } catch(Exception ex) { }

        }
	}

}
