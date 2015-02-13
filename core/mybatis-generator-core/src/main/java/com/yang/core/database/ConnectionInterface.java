package com.yang.core.database;

import java.sql.Connection;

public interface ConnectionInterface {

	public Connection getConnection();
	
	public Object getFromURL();
}
