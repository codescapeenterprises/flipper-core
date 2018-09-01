package com.codescape.loki.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MariaDB {
	private MariaDBCredentials credentials;
	private Connection connection;
	
	// The driver to use for the sql connection
	public static final String driver = "org.mariadb.jdbc.Driver";
	
	// Creates a new instance of this class and starts a new sql connection
	public MariaDB(MariaDBCredentials credentials) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		this.setCredentials(credentials);
		this.connect(
			credentials.getHost(),
			credentials.getUsername(),
			credentials.getPassword(),
			credentials.getDatabase(),
			credentials.getPort()
		);
	}
	
	public void connect(String host, String uname, String pword, String db, int port) throws SQLException {
		this.setConnection(DriverManager.getConnection(
			"jdbc:mariadb://" +
			host + ":" + String.valueOf(port) + "/" +
			db +
			"?user=" + uname +
			"&password=" + pword
		));
	}
	
	public MariaDBCredentials getCredentials() { return(this.credentials); }
	
	public ResultSet query(String query) throws SQLException {
		return(this.connection.createStatement().executeQuery(query));
	}
	
	public void end() throws SQLException {
		this.connection.close();
	}
	
	public void setConnection(Connection connection) { this.connection = connection; }
	public void setCredentials(MariaDBCredentials credentials) { this.credentials = credentials; }
}
