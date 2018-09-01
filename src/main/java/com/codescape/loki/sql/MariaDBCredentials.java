package com.codescape.loki.sql;

public class MariaDBCredentials {
	private String host;
	private String uname;
	private String pword;
	private String db;
	private int port;
	
	public MariaDBCredentials(String host, String uname, String pword, String db) {
		this(host, uname, pword, db, 3306);
	}
	
	public MariaDBCredentials(String host, String uname, String pword, String db, int port) {
		this.setHost(host);
		this.setUsername(uname);
		this.setPassword(pword);
		this.setDatabase(db);
		this.setPort(port);
	}
	
	// Getters
	public String getHost() { return(this.host); }
	public String getUsername() { return(this.uname); }
	public String getPassword() { return(this.pword); }
	public String getDatabase() { return(this.db); }
	public int getPort() { return(this.port); }
	
	
	// Setters
	public void setHost(String host) { this.host = host; }
	public void setUsername(String uname) { this.uname = uname; }
	public void setPassword(String pword) { this.pword = pword; }
	public void setDatabase(String db) { this.db = db; }
	public void setPort(int port) { this.port = port; }
	
	public String toString() {
		return(this.uname + '@' + this.host + ':' + this.port + '/' + this.db);
	}
}
