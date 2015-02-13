package com.yang.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection implements ConnectionInterface {

	private String url;
	private Properties properties;
	
	public OracleConnection() {
		super();
		properties=new Properties();
		url="jdbc:oracle:thin:@172.30.150.100:1521:orcl";
		properties.setProperty("user", "pahctest02");
		properties.setProperty("password", "pahctest02");
		properties.setProperty("driver", "oracle.jdbc.OracleDriver");
	}

	public OracleConnection(String url, Properties properties) {
		super();
		this.url = url;
		this.properties = properties;
	}

	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
