package com.yang.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PoolUtil {

	private static Map<Object,Object> map=null;
	private static Integer maxNum=100;
//	private static Integer minNum=20;
	private static int i=0;
	static{
		map=new HashMap<Object, Object>();
	}
	private static void addFullElement() throws SQLException{
		for (int i = 0; i < 100; i++) {
			map.put(i, ConfigUtil.getConnection());
		}
	}
	public static Connection getConn() throws SQLException{
		if(i==0||i>=maxNum){
			i=0;
			map.clear();
			addFullElement();
		}
//		System.err.println(i);
		Connection con= (Connection) map.get(i);
		i++;
		return con;
	}
	
}
