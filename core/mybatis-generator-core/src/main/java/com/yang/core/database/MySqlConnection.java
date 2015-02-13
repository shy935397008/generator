package com.yang.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnection implements ConnectionInterface {

	private String url;
	private Properties properties;
	
	public MySqlConnection(String url, Properties properties) {
		super();
		this.url = url;
		this.properties = properties;
	}
	

	public MySqlConnection() {
		super();
		url="jdbc:mysql://127.0.0.1:3306/test";
		properties=new Properties();
		properties.setProperty("user", "root");
		properties.setProperty("password", "000000");
		properties.setProperty("driver", "com.mysql.jdbc.Driver");
	}


	public Connection getConnection() {
		
		try {
			return DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}


	public Object getFromURL() {
		throw new UnsupportedOperationException("²»Ö§³Ö");
	}

}
