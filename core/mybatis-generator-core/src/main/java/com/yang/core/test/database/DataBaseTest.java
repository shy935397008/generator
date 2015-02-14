package com.yang.core.test.database;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.yang.core.database.ConnectionContext;
import com.yang.core.database.ConnectionInterface;
import com.yang.core.database.MySqlConnection;
import com.yang.core.database.ObjectConnection;

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
	@Test
	public void test03(){
		ObjectConnection conn=new ObjectConnection("601818");
		ConnectionContext ctx=new ConnectionContext(conn);
		Object object = ctx.getFromURL();
		if(object instanceof List){
			List<?> list=(List<?>) object;
			for (Object obj : list) {
				System.err.println(obj);
			}
		}
	}
}
