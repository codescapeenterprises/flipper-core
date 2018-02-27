package com.codescape.flipper.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class MySQL {
	private MySQLCredentials credentials;
	private Connection connection;
	
	// The driver to use for the MySQL connection
	public static final String driver = "org.mariadb.jdbc.Driver";
	
	// Creates a new instance of this class and starts a new MySQL connection
	public MySQL(MySQLCredentials credentials) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		this.credentials = credentials;
		this.connection = DriverManager.getConnection(
			"jdbc:mariadb://" +
			credentials.getHost() + ":" + credentials.getPort() + "/" +
			credentials.getDatabase() +
			"?user=" + credentials.getUsername() +
			"&password=" + credentials.getPassword()
		);
	}
	
	public Statement query(String query) throws SQLException {
		Statement stmt = this.connection.createStatement();
		
		stmt.execute(query);
		return(stmt);
	}
	
	public void end() throws SQLException {
		this.connection.close();
	}
}
