package com.yang.core.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.junit.Assert;
import org.junit.Test;

import com.yang.core.ConfigUtil;
import com.yang.core.PoolUtil;

public class DBTest {

	@Test
	public void test01() throws SQLException{
		Connection con = ConfigUtil.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from t_test");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			System.err.println(rs.getObject(1).getClass());
		}
		ps.close();
		ConfigUtil.close(rs, con);
	}
	@Test
	public void test03() throws SQLException{
		for (int i = 0; i <20; i++) {
//			System.err.println(i);
			Connection conn = ConfigUtil.getConnection();
			Assert.assertTrue(conn!=null);	
			conn.close();
		}
	}
	@Test
	public void test02() throws SQLException{
		for (int i = 0; i <20; i++) {
//			System.err.println(i);
			Connection conn = PoolUtil.getConn();
			Assert.assertTrue(conn!=null);	
			conn.close();
		}
	}
}
