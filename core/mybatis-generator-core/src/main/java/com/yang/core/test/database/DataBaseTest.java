package com.yang.core.test.database;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.yang.core.database.ConnectionContext;
import com.yang.core.database.ConnectionInterface;
import com.yang.core.database.MySqlConnection;
import com.yang.core.database.OracleConnection;

public class DataBaseTest {

	@Test
	public void test01(){
		ConnectionInterface connectionInterface=new MySqlConnection();
		ConnectionContext ctx=new ConnectionContext(connectionInterface);
		Connection connection = ctx.getConnection();
		assertNotNull("null",connection);
		if(connection!=null){
			System.err.println("link ok");
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
