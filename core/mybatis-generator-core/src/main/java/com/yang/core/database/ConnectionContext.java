package com.yang.core.database;

import java.sql.Connection;

public class ConnectionContext {

	private ConnectionInterface connectionInterface;

	public ConnectionContext(ConnectionInterface connectionInterface) {
		super();
		this.connectionInterface = connectionInterface;
	}
	public Connection getConnection(){
		return connectionInterface.getConnection();
	}
}
