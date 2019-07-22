package co.movilidadbogota.util;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import co.movilidadbogota.comun.database.DatabaseConfiguration;
import co.movilidadbogota.comun.database.MetadataParameter;
import co.movilidadbogota.comun.database.MetadataQuery;
import co.movilidadbogota.comun.EnumServicios;

import co.movilidadbogota.comun.ResourceUtil;
import co.movilidadbogota.comun.Serializer;
import co.movilidadbogota.comun.StringUtil;
import co.movilidadbogota.comun.SvcLogger;

/**
 * 
 * Esta clase se encarga de maneja la conexion de la base de datos sybase como
 * una superclase.
 * 
 * 
 */

public class OracleDatabase {

	
	private String driverClass;
	private String connectionURL;
	private String user;
	private String password;
	private DatabaseConfiguration databaseConfiguration = null;
	private MetadataQuery selectedMetadataQuery = null;

	private boolean createConnectionFromDataSourceData = false;
	private boolean sharedConnection = false;

	private Connection conn = null;

	private String jndiName = null;

	private int timeOutSeconds = 0;
	
	private String UUID = "";
	/**

     * EnumServicios para los diferentes tipos de cargue
     * 
     * @param EnumServicios corresponde al tipo de cargue
     */
	public OracleDatabase(EnumServicios EnumServicios){
	    ResourceBundle resources= ResourceBundle.getBundle("datasources");
        jndiName=resources.getString(EnumServicios.toString());
	}
	/**
     * 
     * @param EnumServicios corresponde al tipo de cargue
     * @param createConnectionFromDataSourceData estado de la conexion
     */
	public OracleDatabase(EnumServicios EnumServicios, boolean createConnectionFromDataSourceData){
        ResourceBundle resources= ResourceBundle.getBundle("datasources");
        jndiName=resources.getString(EnumServicios.toString());
        this.createConnectionFromDataSourceData = createConnectionFromDataSourceData;
    }
	/**
     * 
     * @param EnumServicios corresponde al tipo de cargue
     * @param datasourceResource corresponde en que archivo se obtiene el jndi
     */
	public OracleDatabase(EnumServicios EnumServicios, Boolean datasourceResource){
		try {
			if (datasourceResource) {
				jndiName = ResourceUtil.getResourceValue("resources", EnumServicios.toString());
			} else {
				ResourceBundle resources= ResourceBundle.getBundle("datasources");
			    jndiName=resources.getString(EnumServicios.toString());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Este constructor busca el jdni en el archivo resources.
	 * Constructor obsoleto.
	 */
	
	public OracleDatabase() {
		try {
			// Obtiene el jdni		   
			jndiName = ResourceUtil.getResourceValue("resources", "jndi-name");
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	/**
	 * Este constructor recibe la conexion compartida
	 * 
	 * @param conexion
	 */

	public OracleDatabase(Connection connection) {
		this.conn = connection;
		this.sharedConnection = true;
	}

	/**
	 * Este constructor recibe el nombre jdni y crea la conexion
	 * 
	 * @param jndiName
	 */

	public OracleDatabase(String jndiName) {
		this.jndiName = jndiName;
	}

	
	public OracleDatabase(String jndiName,
			boolean createConnectionFromDataSourceData) {
		this.jndiName = jndiName;
		this.createConnectionFromDataSourceData = createConnectionFromDataSourceData;
	}

	/**
	 * Este constructor recibe el tiempo de espera de la consulta
	 * 
	 * @param timeOutSeconds
	 */
	public OracleDatabase(int timeOutSeconds, String jndiName) {
		this.timeOutSeconds = timeOutSeconds;
		try {
			// Obtiene el jdni	
			this.jndiName = jndiName;
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}

	/**
	 * Se envian los parametro para la base de datos
	 * 
	 * @param driverClass
	 * @param connectionURL
	 * @param user
	 * @param password
	 */

	public OracleDatabase(String driverClass, String connectionURL,
			String user, String password) {

		// Asigna los valores de la conexion
		super();
		this.driverClass = driverClass;
		this.connectionURL = connectionURL;
		this.user = user;
		this.password = password;
	}
	
	
	/**
	 * Se envian los parametro para la base de datos
	 * 
	 * @param driverClass
	 * @param connectionURL
	 * @param user
	 * @param password
	 */

	public OracleDatabase(Map<String, String> propiedadesDatabase) {

		// Asigna los valores de la conexion
		super();
		this.driverClass = propiedadesDatabase.get("DRIVER_CLASS");
		this.connectionURL = propiedadesDatabase.get("CONNECTION_URL");;
		this.user = propiedadesDatabase.get("USER_NAME");;
		this.password = propiedadesDatabase.get("PASSWORD");;
	}

	// GETTERS AND SETTERS
	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MetadataQuery getSelectedMetadataQuery() {
		return selectedMetadataQuery;
	}

	public void setSelectedMetadataQuery(MetadataQuery selectedMetadataQuery) {
		this.selectedMetadataQuery = selectedMetadataQuery;
	}

	public DatabaseConfiguration getDatabaseConfiguration() {
		return databaseConfiguration;
	}

	public void setDatabaseConfiguration(
			DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}


	/**
	 * Esta funcion obtiene un procedimiento almacenado leido desde el
	 * DataBaseConfiguration
	 * 
	 * @param storedProcedure
	 * @return
	 */
	public DbCommand GetStoredProcCommand(String storedProcedure) {
		return new DbCommand(CommandType.StoredProcedure, storedProcedure);
	}

	private void configConnection() {
		try {
			String xml = Serializer.leerArchivo("DatabaseConfiguration.xml");

			// Obtiene la configuracion de la base de datos
			setDatabaseConfiguration((DatabaseConfiguration) Serializer
					.deserializar(xml, DatabaseConfiguration.class));

			// Configura los valores de la conexion
			setDriverClass(getDatabaseConfiguration().getDriverClass());
			setConnectionURL(getDatabaseConfiguration().getConnectionURL());
			setUser(getDatabaseConfiguration().getUser());
			setPassword(getDatabaseConfiguration().getPassword());
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	private void configConnectionMap() {
		try {
			String xml = Serializer.leerArchivo("DatabaseConfiguration.xml");

			// Obtiene la configuracion de la base de datos
			setDatabaseConfiguration((DatabaseConfiguration) Serializer.deserializar(xml, DatabaseConfiguration.class));
			setDriverClass(this.driverClass);
			setConnectionURL(this.connectionURL);
			setUser(this.user);
			setPassword(this.password);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Lee un comando XML desde el DatabaseConfiguration
	 * 
	 * @param queryName
	 * @return
	 * @throws Exception
	 */

	public DbCommand GetXmlCommand(String queryName) throws Exception {
		configConnection();

		// Busca el query en el XML
		List<MetadataQuery> lst = getDatabaseConfiguration()
				.getMetadataQueries().getMetadataQuery();
		String commandText = "";
		CommandType commandType = CommandType.Xml;
		for (MetadataQuery metadataQuery : lst) {
			if (metadataQuery.getQueryName().equals(queryName)) {
				setSelectedMetadataQuery(metadataQuery);
				commandText = metadataQuery.getCommandText();
				if (metadataQuery.getCommandType() != null
						&& metadataQuery.getCommandType().equals(
								"StoredProcedure"))
					commandType = CommandType.StoredProcedure;
				break;
			}
		}
		// Si no encontro nada excepcion
		if (commandText.trim().length() == 0)
			throw new Exception("QueryName '" + queryName + "' does not exists");

		return new DbCommand(commandType, commandText, queryName);
	}
	
	
	/**
	 * Lee un comando XML desde el DatabaseConfiguration
	 * 
	 * @param queryName
	 * @return
	 * @throws Exception
	 */

	public DbCommand GetXmlCommandMap(String queryName) throws Exception {
		configConnectionMap();

		// Busca el query en el XML
		List<MetadataQuery> lst = getDatabaseConfiguration()
				.getMetadataQueries().getMetadataQuery();
		String commandText = "";
		CommandType commandType = CommandType.Xml;
		for (MetadataQuery metadataQuery : lst) {
			if (metadataQuery.getQueryName().equals(queryName)) {
				setSelectedMetadataQuery(metadataQuery);
				commandText = metadataQuery.getCommandText();
				if (metadataQuery.getCommandType() != null
						&& metadataQuery.getCommandType().equals(
								"StoredProcedure"))
					commandType = CommandType.StoredProcedure;
				break;
			}
		}
		// Si no encontro nada excepcion
		if (commandText.trim().length() == 0)
			throw new Exception("QueryName '" + queryName + "' does not exists");

		return new DbCommand(commandType, commandText, queryName);
	}

	/**
	 * Lee el comando tipo texto
	 * 
	 * @param textCommand
	 * @return
	 */

	public DbCommand GetTextCommand(String textCommand) {
		return new DbCommand(CommandType.Text, textCommand);
	}

	/**
	 * Adiciona un parametro al comando seleccionado
	 * 
	 * @param command
	 * @param parameterName
	 * @param parameterType
	 * @param parameterScale
	 * @param parameterValue
	 */

	public void AddInParameter(DbCommand command, String parameterName,
			DbType parameterType, int parameterScale, Object parameterValue) {
		command.addInParameter(parameterName, GetSqlType(parameterType),
				parameterScale, parameterValue);

	}

	/**
	 * Adiciona un parametro XML al comando escojido
	 * 
	 * @param command
	 * @param parameterName
	 * @param parameterValue
	 * @throws Exception
	 */

	public void AddInXmlParameter(DbCommand command, String parameterName,
			Object parameterValue) throws Exception {

		if (getSelectedMetadataQuery() == null)
			throw new Exception(
					"Cannot add a xml parameter because it is not an Xml Command Type");

		List<MetadataParameter> list = getSelectedMetadataQuery()
				.getParameters().getParameter();

		String parameterType = "";
		int parameterScale = -1;
		for (MetadataParameter metadataParameter : list) {
			if (metadataParameter.getParameterName().equals(parameterName)) {
				parameterType = metadataParameter.getParameterType();
				parameterScale = metadataParameter.getParameterScale();
				break;
			}
		}
		if (parameterType.trim().length() == 0 || parameterScale == -1)
			throw new Exception(
					StringUtil
							.Format("Parameter {0} does not exists in the xml configuration for queryName {1}",
									parameterName, getSelectedMetadataQuery()
											.getQueryName()));

		AddInParameter(command, parameterName, DbType.valueOf(parameterType),
				parameterScale, parameterValue);

	}

	/**
	 * Adiciona un parametro de salida al parametro escojido
	 * 
	 * @param command
	 * @param parameterName
	 * @param parameterType
	 * @param parameterScale
	 */

	public void AddOutParameter(DbCommand command, String parameterName,
			DbType parameterType, int parameterScale) {
		command.addOutParameter(parameterName, GetSqlType(parameterType),
				parameterScale);
	}

	/**
	 * Obitene el valor de un parametro
	 * 
	 * @param command
	 * @param parameterName
	 * @return
	 */

	public Object getParameterValue(DbCommand command, String parameterName) {
		return command.getParameterValue(parameterName);
	}

	/**
	 * Ejecuta un Query para obtener una estructura DataTable desde una consulta
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	public DataTable ExecuteDataTable(DbCommand command) throws Exception {
		DataTable table = new DataTable();
		if (command.getCommandType() == CommandType.StoredProcedure)
			table = ExecuteDataTableStoredProcedure(command);
		else if (command.getCommandType() == CommandType.Text
				|| command.getCommandType() == CommandType.Xml)
			table = ExecuteDataTableCommandText(command);
		return table;
	}

	private void printLogBD(String title, String message) {

		boolean printLogDB = false;
		try {
			printLogDB = Boolean.parseBoolean(ResourceUtil.getResourceValue(
					"resources", "printLogDB"));
			
		} catch (Exception e1) {
			printLogDB = true;
		}

		if (printLogDB == true) {
			if (title != null) {
				SvcLogger.info (title);
			}

			SvcLogger.info(message);
		}

	}
	/**
     * Metodo para la impresion de las consultas en el log para Historia Laboral
     * @param title
     * @param message
     */
    public void printLogHL(String title, String message) {

        boolean printLogHL = false;
        try {
            printLogHL = Boolean.parseBoolean(ResourceUtil.getResourceValue(
                    "resources", "printLogHL"));
            
        } catch (Exception e1) {
            printLogHL = true;
        }

        if (printLogHL == true) {
            if (title != null) {
                SvcLogger.info (title);
            }

            SvcLogger.info(message);
        }

    }
	
    /**
     * Metodo para la impresion de la validación de la existencia de Fondos Privados
     * @param title
     * @param message
     */
    public void printLogFondosPrivados(String message) {

        boolean printLogFPL = false;
        try {
            printLogFPL = Boolean.parseBoolean(ResourceUtil.getResourceValue(
                    "resources", "printLogFP"));
            
        } catch (Exception e1) {
            printLogFPL = true;
        }

        if (printLogFPL == true) {
            SvcLogger.info(message);
        }

    }    
    
	/**
	 * Obtener el SQL de un comando
	 * 
	 * @param command
	 */

	private String getCommandAsString(DbCommand command) {
		int iParametersCount = command.getParameters().size();
		String parametersCall = "(";

		for (int i = 0; i < iParametersCount; i++) {
			if (i == 0)
				parametersCall = command.getParameters().get(i)
						.getParameterName()
						+ " = "
						+ command.getParameters().get(i).getParameterValue();
			else
				parametersCall += ","
						+ command.getParameters().get(i).getParameterName()
						+ " = "
						+ command.getParameters().get(i).getParameterValue();
		}

		parametersCall = parametersCall + ")";

		String callStoredProcedure = command.getCommandText() + parametersCall;

		return callStoredProcedure;

	}

	/**
	 * Ejecuta un procedimiento rpara obtener un DatatTable
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	private DataTable ExecuteDataTableStoredProcedure(DbCommand command)
			throws Exception {

		CallableStatement stmt = null;
		ResultSet rs = null;
		DataTable table = new DataTable();

		try {
			// if (conn == null)
			if (conn == null || (conn != null && conn.isClosed()))
				conn = GetConnection();

			String callStoredProcedure = "{ call " + command.getCommandText()
					+ " }";

			
			printLogBD(null,
					"Inicio  Ejecución Query : " + command.getCommandName() + " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);

			stmt = conn.prepareCall(callStoredProcedure);

			// Adiciona los parametros al Callable Statement

			for (DbParameter parameter : command.getParameters()) {
				if (parameter.getParamDirection() == ParameterDirection.OUT)
					stmt.registerOutParameter(parameter.getParameterName(),
							parameter.getParameterType(),
							parameter.getParameterScale());
				else
					stmt.setObject(parameter.getParameterName(),
							parameter.getParameterValue(),
							parameter.getParameterType(),
							parameter.getParameterScale());

			}

			rs = stmt.executeQuery();
			// Lee el ResultSet y lo convierte a DataTable
			ResultSetMetaData metaData = rs.getMetaData();

			// Obtiene el valor de los parametros de salida

			if (metaData.getColumnCount() > 0) {
				table.setTableName(metaData.getTableName(1));
				for (int i = 1; i <= metaData.getColumnCount(); i++)
					table.addColumn(new DataColumn(metaData.getColumnLabel(i)));
			}
			while (rs.next()) {

				DataRow row = new DataRow();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					row.addColumn(new DataColumn(metaData.getColumnLabel(i), rs
							.getObject(i)));
				}

				table.addRow(row);
			}

			for (DbParameter parameter : command.getParameters()) {
				if (parameter.getParamDirection() == ParameterDirection.OUT)
					parameter.setParameterValue(stmt.getObject(parameter
							.getParameterName()));
			}

		} catch (Exception e) {
			SvcLogger.error("Error ExecuteDataTableStoredProcedure: "
					+ e.getMessage());

			closeConnectionExecution();
			
			throw e;
		} finally {

			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);

			try { rs.close(); } catch(Exception e) { }
			try { stmt.close(); } catch(Exception e) { }
			try { closeConnectionAutoCommit(); } catch(Exception e) { closeConnectionExecution(); }
		
		}

		return table;
	}

	/**
	 * Ejecuta un commando tipo texto para obtener un DatatTable
	 * 
	 * @param command
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	private DataTable ExecuteDataTableCommandText(DbCommand command)
			throws ClassNotFoundException, SQLException {
	
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DataTable table = new DataTable();

		try {
			if (conn == null || (conn != null && conn.isClosed()))
				conn = GetConnection();

			stmt = conn.prepareStatement(command.getCommandText());

			// Adiciona los parametros al PreparedStatement Statement

			for (int i = 0; i < command.getParameters().size(); i++) {
				DbParameter parameter = command.getParameters().get(i);
				stmt.setObject(i + 1, parameter.getParameterValue(),
						parameter.getParameterType(),
						parameter.getParameterScale());
			}

			
			printLogBD(null,
					"Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);

			rs = stmt.executeQuery();

			// Lee el ResultSet y lo convierte a DataTable
			ResultSetMetaData metaData = rs.getMetaData();

			if (metaData.getColumnCount() > 0) {
				table.setTableName(metaData.getTableName(1));
				for (int i = 1; i <= metaData.getColumnCount(); i++)
					table.addColumn(new DataColumn(metaData.getColumnLabel(i)));
			}
			while (rs.next()) {

				DataRow row = new DataRow();
				for (int i = 1; i <= metaData.getColumnCount(); i++) {
					row.addColumn(new DataColumn(metaData.getColumnLabel(i), rs
							.getObject(i)));
				}

				table.addRow(row);
			}

		} catch (SQLException e) {
			SvcLogger.error("Error ExecuteDataTableCommandText: " + e.getMessage());

			throw e;
		}

		finally {

			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName() + " UUID: "+ UUID);

			try { rs.close(); } catch(Exception e) { }
			try { stmt.close(); } catch(Exception e) { }
			try { closeConnectionAutoCommit(); } catch(Exception e) { closeConnectionExecution(); }
		
		}

		return table;
	}

	/**
	 * Ejecuta un comando para obtener un ResultSet
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	public ResultSet ExecuteResultSet(DbCommand command) throws Exception {

		if (command.getCommandType() == CommandType.StoredProcedure)
			return ExecuteResultSetStoredProcedure(command);
		else if (command.getCommandType() == CommandType.Text
				|| command.getCommandType() == CommandType.Xml)
			return ExecuteResultSetCommandText(command);

		return null;
	}

	/**
	 * Ejecuta un procedimiento almacenado para obtener un ResultSet
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	private ResultSet ExecuteResultSetStoredProcedure(DbCommand command)
			throws Exception {

		// Prepara para abrir la conexion
		if (conn == null || (conn != null && conn.isClosed()))			
			conn = GetConnection();

		// Prepara el CallableStatement
		String callStoredProcedure = "{ call " + command.getCommandText()
				+ " }";

		

		CallableStatement stmt = conn.prepareCall(callStoredProcedure);

		// Adiciona los parametros al Callable Statement

		for (DbParameter parameter : command.getParameters()) {
			if (parameter.getParamDirection() == ParameterDirection.OUT)
				stmt.registerOutParameter(parameter.getParameterName(),
						parameter.getParameterType(),
						parameter.getParameterScale());
			else
				stmt.setObject(parameter.getParameterName(),
						parameter.getParameterValue(),
						parameter.getParameterType(),
						parameter.getParameterScale());

		}
		
		printLogBD(null, "Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
		printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);
		ResultSet result = stmt.executeQuery();
		printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
		return result;
	}

	/**
	 * Obtiene un resultSet de un comando tipo texto
	 * 
	 * @param command
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	private ResultSet ExecuteResultSetCommandText(DbCommand command)
			throws ClassNotFoundException, SQLException {
		
		if (conn == null || (conn != null && conn.isClosed()))
			
			conn = GetConnection();

		PreparedStatement stmt = conn
				.prepareStatement(command.getCommandText());

		// Adiciona los parametros al PreparedStatement Statement

		for (int i = 0; i < command.getParameters().size(); i++) {
			DbParameter parameter = command.getParameters().get(i);
			stmt.setObject(i + 1, parameter.getParameterValue(),
					parameter.getParameterType(), parameter.getParameterScale());

		}		
		printLogBD(null, "Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
		printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);
		ResultSet result = stmt.executeQuery();
		printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);

		return result;
	}

	/**
	 * Cierra un resultset
	 * 
	 * @param rs
	 * @throws SQLException
	 */

	public void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
			rs.getStatement().close();			
		}
		closeConnectionExecution();

	}

	public void closeExternalResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}

		closeConnectionExecution();

	}

	public void beginTransaction() throws Exception {

		if (this.sharedConnection == false) {
			conn = GetConnection();
			conn.setAutoCommit(false);
		}

	}

	public void commit() throws Exception {
		if (conn != null) {

			if (this.sharedConnection == false) {
				conn.commit();
				conn.close();
			}

		}
	}

	public void rollBack() throws Exception {
		if (conn != null) {

			if (this.sharedConnection == false) {
				conn.rollback();
				conn.close();
			}

		}
	}

	/**
	 * Ejecuta un comando que no se de consulta
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	public int executeNonQuery(DbCommand command) throws Exception {

		 String dirTmp = System.getProperty("java.io.tmpdir");   System.out.println("ruta *** " + dirTmp);
		int returnValue = -1;
		if (command.getCommandType() == CommandType.StoredProcedure)
			returnValue = executeNonQueryStoredProcedure(command);
		else if (command.getCommandType() == CommandType.Text
				|| command.getCommandType() == CommandType.Xml)
			returnValue = executeNonQueryCommandText(command);
		return returnValue;

	}
	
	/**
	 * Ejecuta un procedimiento almacenado que no sea de consulta
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	public HashMap<String, Object> executeNonQueryStoredProcedure(DbCommand command, boolean ab_catchReturn)
			throws Exception {
	    SvcLogger.info("en OracleDatabase");
	    HashMap<String, Object> lhm_outputs;
	    HashMap<String, Integer> lai_outputs;
	    
	    lai_outputs = null;

	    lhm_outputs = null;
	    
		try {
			
			if (conn == null || (conn != null && conn.isClosed()))
				conn = GetConnection();
			SvcLogger.info("conn"+conn+"command"+command);
			
			CallableStatement stmt;
			
			if(!ab_catchReturn)
				stmt = conn.prepareCall("{call "
					+ command.getCommandText() + "}");
			else
				stmt = conn.prepareCall("{? = call "
						+ command.getCommandText() + "}");
			SvcLogger.info("proc:"+command.getCommandText());
			for (int i = 0; i < command.getParameters().size(); i++) {
				DbParameter parameter = command.getParameters().get(i);
				if(parameter.getParamDirection() == ParameterDirection.IN)
					stmt.setObject(i + 1, parameter.getParameterValue(),
							parameter.getParameterType(),
							parameter.getParameterScale());
				if(parameter.getParamDirection() == ParameterDirection.OUT){
					
					if(lai_outputs == null)
						lai_outputs = new HashMap<String, Integer>();
					
					stmt.registerOutParameter(i +1, parameter.getParameterType());
					lai_outputs.put(parameter.getParameterName(), new Integer(i + 1));
				}
				SvcLogger.info("par:"+parameter.getParameterValue());
			}

			
			printLogBD(null,
					"Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);

			stmt.executeUpdate();

			if(lai_outputs != null){
				Set<String> lks_ks = lai_outputs.keySet();
				lhm_outputs = new HashMap<String, Object>();
				for(String ls_s : lks_ks){
					Integer li_i = lai_outputs.get(ls_s);
					lhm_outputs.put(ls_s,stmt.getObject(li_i.intValue()));
				}
			}
			
			stmt.close();			
				
			closeConnectionAutoCommit();
		
		} catch (Exception e) {
			SvcLogger
					.error("Error executeNonQueryStoredProcedure: " + e.getMessage()+".."+e.toString());
			e.printStackTrace();
			closeConnectionExecution();
			
			throw e;
		}

		finally {

			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);

		}
		return lhm_outputs;

	}

	/**
	 * Ejecuta un procedimiento almacenado que no sea de consulta
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	private int executeNonQueryStoredProcedure(DbCommand command)
			throws Exception {
	    SvcLogger.info("en OracleDatabase");
		int result = 0;
		try {
		
			if (conn == null || (conn != null && conn.isClosed()))
				conn = GetConnection();
			SvcLogger.info("conn"+conn+"command"+command);
			PreparedStatement stmt = conn.prepareCall("{call "
					+ command.getCommandText() + "}");
			SvcLogger.info("proc:"+command.getCommandText());
			for (int i = 0; i < command.getParameters().size(); i++) {
				DbParameter parameter = command.getParameters().get(i);
				stmt.setObject(i + 1, parameter.getParameterValue(),
						parameter.getParameterType(),
						parameter.getParameterScale());
				SvcLogger.info("par:"+parameter.getParameterValue());
			}

			
			printLogBD(null,
					"Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);

			result = stmt.executeUpdate();

			//Mantis 2682 - 08/04/2015 - dlondono@asesoftware.com
			stmt.close();
			
			closeConnectionAutoCommit();
		
		} catch (Exception e) {
			SvcLogger
					.error("Error executeNonQueryStoredProcedure: " + e.getMessage()+".."+e.toString());
			e.printStackTrace();
			closeConnectionExecution();
			
			throw e;
		}

		finally {

			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);

		}
		return result;

	}

	/**
	 * Ejcuta un cimando tipo texto que no sea de consulta
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	private int executeNonQueryCommandText(DbCommand command) throws Exception {

		int result;
		try {

			if (conn == null || (conn != null && conn.isClosed()))
				
				conn = GetConnection();

			PreparedStatement stmt = conn.prepareStatement(command
					.getCommandText());

			for (int i = 0; i < command.getParameters().size(); i++) {
				DbParameter parameter = command.getParameters().get(i);
				stmt.setObject(i + 1, parameter.getParameterValue(),
						parameter.getParameterType(),
						parameter.getParameterScale());

			}
			
			printLogBD(null,
					"Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);
			result = stmt.executeUpdate();

			closeConnectionAutoCommit();
			
		} catch (Exception e) {
			SvcLogger.error("Error executeNonQueryCommandText: " + e.getMessage());

			closeConnectionExecution();
			
			throw e;
		} finally {
			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
		}
		return result;
	}

	/**
	 * Ejecuta un comando que no retorna un resultado
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	public void execute(DbCommand command) throws Exception {

		if (command.getCommandType() == CommandType.StoredProcedure)
			executeStoredProcedure(command);

	}

	/**
	 * Ejecuta un procedimiento almacenado que no devuelve respuesta
	 * 
	 * @param command
	 * @return
	 * @throws Exception
	 */

	private void executeStoredProcedure(DbCommand command) throws Exception {
		
		try {
		    if (conn == null || (conn != null && conn.isClosed()))
				conn = GetConnection();
			CallableStatement stmt = conn.prepareCall("{call "
					+ command.getCommandText() + "}");
			for (int i = 0; i < command.getParameters().size(); i++) {
				DbParameter parameter = command.getParameters().get(i);
				stmt.setObject(i + 1, parameter.getParameterValue(),
						parameter.getParameterType(),
						parameter.getParameterScale());

			}			
			printLogBD(null,
					"Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);
			
			stmt.execute();
			
			closeConnectionAutoCommit();
			
		} catch (Exception e) {
			SvcLogger.error("Error executeStoredProcedure: " + e.getMessage());
			
			closeConnectionExecution();			
			
			throw e;
		}
		
		finally {
			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);			
		}

	}

	/**
	 * @param command
	 * @return DataTable | int (filas afectadas) | null
	 */
	public Object executeQueryOfResultUndefined(DbCommand command, boolean returnDataTable) throws SQLException {
		java.util.Date fechaInicial = null;
		int resultNum = 0;
		ResultSet rs = null;
		int uc = -1;
		DataTable dataTable = null;
		Integer rowsAff = null;
		CallableStatement stmt = null;
		try {
		    if (conn == null || (conn != null && conn.isClosed())) {
		    	conn = GetConnection();
		    }
		    
		    String queryCommand = command.getCommandText();
		    if (command.getCommandType() == CommandType.StoredProcedure) {
		    	queryCommand = "{call "+ command.getCommandText() + "}";
		    } 
		    stmt = conn.prepareCall( queryCommand );

		    if (this.timeOutSeconds > 0) {
		    	stmt.setQueryTimeout(timeOutSeconds);
		    }

			for (int i = 0; i < command.getParameters().size(); i++) {
				DbParameter parameter = command.getParameters().get(i);
				stmt.setObject(i + 1, parameter.getParameterValue(),
								parameter.getParameterType(),
								parameter.getParameterScale());
			}			
			
			printLogBD(null, "Inicio  Ejecución Query : " + command.getCommandName()+ " UUID: "+ UUID);
			printLogBD("printCommand", getCommandAsString(command)+ " UUID: "+ UUID);

			boolean exec = returnDataTable;
			fechaInicial = new java.util.Date();
			try	{
				if (exec) {
					rs = stmt.executeQuery();
				} else {
					exec = stmt.execute();
				}
			} catch (SQLException e) {
				switch (e.getSQLState()) {
				case "JZ0SJ":
				case "ZZZZZ":
					break;
				default:
					throw e;
				}
			}
			while (true) {
				resultNum++;
				try {
					if (exec) {
						if (rs == null) {
							rs = stmt.getResultSet();
						}
						rowsAff = null;
					} else {
						uc = stmt.getUpdateCount(); 
						// No more results
						if (uc == -1) {
							break;
						} else {
							rowsAff = uc;
							rs = null;
						}
					}
				} catch(Exception e) {}
				
				if (rs != null) {
					try {
						ResultSetMetaData metaData = rs.getMetaData();
	
						// Obtiene el valor de los parametros de salida
						dataTable = new DataTable();
	
						if (metaData.getColumnCount() > 0) {
							dataTable.setTableName(metaData.getTableName(1));
							for (int i = 1; i <= metaData.getColumnCount(); i++)
								dataTable.addColumn(new DataColumn(metaData.getColumnLabel(i)));
						}
						while (rs.next()) {
	
							DataRow row = new DataRow();
							for (int i = 1; i <= metaData.getColumnCount(); i++) {
								row.addColumn(new DataColumn(metaData.getColumnLabel(i), rs.getObject(i)));
							}
							dataTable.addRow(row);
							
						}
	
						for (DbParameter parameter : command.getParameters()) {
							if (parameter.getParamDirection() == ParameterDirection.OUT)
								parameter.setParameterValue(stmt.getObject(parameter.getParameterName()));
						}
					} catch(Exception e) {
					} finally {
				        try { rs.close(); } catch (Throwable ignore) {}
				    }
				}
				
				try	{
					exec = stmt.getMoreResults();
				} catch (SQLException e) {
					switch (e.getSQLState()) {
					case "JZ0SJ":
					case "ZZZZZ":
						break;
					default:
						throw e;
					}
				}
			} 

			if (returnDataTable) {
				return dataTable;
			} else if (rowsAff != null) {
				return rowsAff.intValue();
			} else {
				return null;
			}

		} catch (SQLException e) {
			SvcLogger.error("Error Result " + resultNum + " executeQueryOfResultUndefined: ("+e.getSQLState()+") " + e.getMessage());
			try {
				closeConnectionExecution();			
			} catch (Exception e2) { }
			throw e;
		} finally {
			printLogBD(null, "Fin  Ejecución Query : " + command.getCommandName()+ " [("+resultNum+") respuesta(s)] UUID: "+ UUID);

			try { rs.close(); } catch (Throwable ignore) {}
			try { stmt.close(); } catch (Throwable ignore) {}
			
			try {
				closeConnectionAutoCommit();
			} catch(Exception e) {
				try {	conn.close();	} catch(Exception e1) {}
			}

			if (this.timeOutSeconds > 0 && fechaInicial != null) {
		    	timeOutSeconds -= (int) ((new java.util.Date().getTime() - fechaInicial.getTime())/1000);
		    }
			
		}

	}
	
	private Connection GetConnection() {

		if (this.sharedConnection == true)
			return conn;

		// Obtiene informacion de la conexion
		boolean useDataSource = false;
		try {
			useDataSource = Boolean.parseBoolean(ResourceUtil.getResourceValue(
					"resources", "useDataSource"));			
		} catch (Exception e1) {
			 useDataSource = true;
		}

		Connection connection = null;
		// Busca la conexion en el datasource
		try {

			if (useDataSource) {
				InitialContext cxt = new InitialContext();

				DataSource ds = (DataSource) cxt.lookup(jndiName);
				if (ds == null)
					throw new Exception("Data source not found!");
				
				if (!createConnectionFromDataSourceData) {
					connection = ds.getConnection();
					SvcLogger.info("User Database: " + connection.getMetaData().getUserName());
				}
			}

		} catch (SQLException e) {
			SvcLogger.error(e.getMessage());
		} catch (Exception e) {
			SvcLogger.error(e.getMessage());
		}
		if (connection == null) {
			try {

				if (getDriverClass() == null || getConnectionURL() == null
						|| getUser() == null || getPassword() == null)
					configConnection();
				Class.forName(getDriverClass());

				connection = DriverManager.getConnection(getConnectionURL(),
						getUser(), getPassword());
			} catch (ClassNotFoundException e) {

				SvcLogger.error(e.getMessage());
			} catch (SQLException e) {
				
				SvcLogger.error(e.getMessage());
			}
		}
		return connection;
	}

	/**
	 * Método que permite obtener una conexión independiente del pool de conexiones del contenedor.<br/>
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
	 * @return conexión a base de datos
	 */
	public Connection GetConnection(Map<String, String> propiedadesDatasource) {
        
		if (this.sharedConnection == true)
			return null;

		Connection conn = null;
        try {
	        	
	        Class.forName(propiedadesDatasource.get("DRIVER_CLASS"));
	        conn = DriverManager.getConnection(propiedadesDatasource.get("CONNECTION_URL"),
								               propiedadesDatasource.get("USER_NAME"),
								               propiedadesDatasource.get("PASSWORD"));

		} catch (SQLException e) {
			SvcLogger.error(e.getMessage());
		} catch (Exception e) {
			SvcLogger.error(e.getMessage());
		}
        return conn;
    }
	
	/**
	 * Obtiene los tipos de datos validos en SQL
	 * 
	 * @param parameterType
	 * @return
	 */
	private int GetSqlType(DbType parameterType) {
		
		switch (parameterType) {
		case BIT:
			return Types.BIT;
			// -7
		case TINYINT:
			return Types.TINYINT;
			// -6
		case SMALLINT:
			return Types.SMALLINT;
			// 5
		case INTEGER:
			return Types.INTEGER;
			// 4
		case BIGINT:
			return Types.BIGINT;
			// -5
		case FLOAT:
			return Types.FLOAT;
			// 6
		case REAL:
			return Types.REAL;
			// 7
		case DOUBLE:
			return Types.DOUBLE;
			// 8
		case NUMERIC:
			return Types.NUMERIC;
			// 2
		case DECIMAL:
			return Types.DECIMAL;
			// 3
		case CHAR:
			return Types.CHAR;
			// 1
		case VARCHAR:
			return Types.VARCHAR;
			// 12
		case LONGVARCHAR:
			return Types.LONGVARCHAR;
			// -1
		case DATE:
			return Types.DATE;
			// 91
		case TIME:
			return Types.TIME;
			// 92
		case TIMESTAMP:
			return Types.TIMESTAMP;
			// 93
		case BINARY:
			return Types.BINARY;
			// -2
		case VARBINARY:
			return Types.VARBINARY;
			// -3
		case LONGVARBINARY:
			return Types.LONGVARBINARY;
			// -4
		case NULL:
			return Types.NULL;
			// 0
		case OTHER:
			return Types.OTHER;
			// 1111
		case JAVA_OBJECT:
			return Types.JAVA_OBJECT;
			// 2000
		case DISTINCT:
			return Types.DISTINCT;
			// 2001
		case STRUCT:
			return Types.STRUCT;
			// 2002
		case ARRAY:
			return Types.ARRAY;
			// 2003
		case BLOB:
			return Types.BLOB;
			// 2004
		case CLOB:
			return Types.CLOB;
			// 2005
		case REF:
			return Types.REF;
			// 2006
		case DATALINK:
			return Types.DATALINK;
			// 70
		case BOOLEAN:
			return Types.BOOLEAN;
			// 16
		case ROWID:
			return Types.ROWID;
			// -8
		case NCHAR:
			return Types.NCHAR;
			// -15
		case NVARCHAR:
			return Types.NVARCHAR;
			// -9
		case LONGNVARCHAR:
			return Types.LONGNVARCHAR;
			// -16
		case NCLOB:
			return Types.NCLOB;
			// 2011
		case SQLXML:
			return Types.SQLXML;
			// 2009
		}
		return 0;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Connection createNewConnection() throws SQLException {
		if (conn != null && conn.isClosed())
			conn = null;
		return GetConnection();
	}

	/**
	 * Cierra la conexion si no es compartida
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void closeConnectionAutoCommit() throws SQLException {
		if (this.sharedConnection == false) {
			if (conn.getAutoCommit())
				conn.close();
		}

	}

	/**
	 * Cierra la conexion si no es compartida. Solo en caso de error
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	private void closeConnectionExecution() throws SQLException {
		if (this.sharedConnection == false) {
			if (conn != null && conn.getAutoCommit() && !conn.isClosed())
				conn.close();
		}

	}
	
	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
	public String getJndiName() {
		return jndiName;
	}
	
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

}
