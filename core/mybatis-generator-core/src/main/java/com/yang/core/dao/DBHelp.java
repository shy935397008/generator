package com.yang.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yang.core.database.ConnectionContext;
import com.yang.core.database.ConnectionInterface;

public class DBHelp {
	private ConnectionInterface connectionInterface;
	private Connection conn;
	
	public DBHelp(ConnectionInterface connectionInterface) {
		super();
		this.connectionInterface = connectionInterface;
	}
	private Connection getConnection(){
		ConnectionContext ctx=new ConnectionContext(connectionInterface);
		return ctx.getConnection();
	}
	public int[] batchUpdate(String sql,List<Object[]> list) throws SQLException{
		conn=getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		for (Object[] objs : list) {
			for ( int i = 0; i < objs.length; i++) {
				ps.setObject(i+1, objs[i]);
			}
			ps.addBatch();
		}
		int[] j = ps.executeBatch();
		close(conn,ps,null);
		return j;
	}
	public int update(String sql,Object...objs) throws SQLException{
		conn=getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < objs.length; i++) {
			ps.setObject(i+1, objs[i]);
		}
		int i = ps.executeUpdate();
		close(conn,ps,null);
		return i;
	}
	public <T> List<T> query(String sql,MapRow<T> mr,Object...objs) throws SQLException{
		conn=getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < objs.length; i++) {
			ps.setObject(i+1, objs[i]);
		}
		ResultSet rs = ps.executeQuery();
		List<T> list=new ArrayList<T>();
		while(rs.next()){
			T t=mr.rowMap(rs);
			list.add(t);
		}
		close(conn,ps,rs);
		return list;
	}
	public <T> T queryById(String sql,MapRow<T> mr,Object...objs) throws SQLException{
		T t = null;
		conn=getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < objs.length; i++) {
			ps.setObject(i+1, objs[i]);
		}
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			t=mr.rowMap(rs);
		}
		close(conn,ps,rs);
		return t;
	}
	public int count(String sql,Object...objs) throws SQLException{
		int count=0;
		conn=getConnection();
		 PreparedStatement ps = conn.prepareStatement(sql);
		for (int i = 0; i < objs.length; i++) {
			ps.setObject(i+1, objs[i]);
		}
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			 count = rs.getInt(1);
		}
		close(conn,ps,rs);
		return count;
	}
	private void close(Connection conn2, PreparedStatement ps,ResultSet rs) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
