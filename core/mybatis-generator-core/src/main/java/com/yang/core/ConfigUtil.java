package com.yang.core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.yang.generator.GenTest;

import freemarker.template.TemplateException;

public class ConfigUtil {

	private static String pack="com.yang.test";
	
	
	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		ConfigUtil.pack = pack;
	}
	private static Properties p=new Properties();
	static{
		p.setProperty("remarksReporting", "true");
		p.setProperty("user", "pahctest02");
		p.setProperty("password", "pahctest02");
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}		
	}
	public static Connection getConnection() throws SQLException{
		return  DriverManager.getConnection("jdbc:oracle:thin:@172.30.150.100:1521:orcl",p);
	}
	
	public static List<DBColumn> getListColumn(String schema,String tableName) throws SQLException{
		Connection conn = getConnection();
		DatabaseMetaData metaData = conn.getMetaData();
		//---------------------------------column------------------------
		ResultSet columns = metaData.getColumns(conn.getCatalog(),schema,tableName,null);
		Map<String,Object> map=new HashMap<String, Object>();
		List<DBColumn> list=new ArrayList<DBColumn>();
		while(columns.next()){
			for (int i = 0; i < ENUM.COLUMN.values().length; i++) {
				Object object;
				try {
					object = columns.getObject(ENUM.COLUMN.values()[i]
							+ "");
//					System.err.println(GenTest.camel(ENUM.COLUMN.values()[i]+"")+"<>"+object);
					if(object!=null){
						map.put(GenTest.firstlower(GenTest.camel(ENUM.COLUMN.values()[i]+"")), object);						
					}
				} catch (Exception e) {
					continue;
				}
			}
			DBColumn c=new DBColumn();
			BeanUtils.map2Bean(c, map);
			map.clear();
			list.add(c);
			
		}
		close(columns,conn);
		return list;
	}
	private static void close(ResultSet rs,Connection con) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(con!=null){
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static List<DBTable> getListTable(String schema) throws SQLException{
		Connection conn = getConnection();
		DatabaseMetaData metaData = conn.getMetaData();
		List<DBTable> list=new ArrayList<DBTable>();
		ResultSet tables = metaData.getTables(conn.getCatalog(),schema, null, new String[]{"TABLE"});
		Map<String,Object> map=new HashMap<String, Object>();
		while(tables.next()){
			DBTable tab=new DBTable();
			for (int i = 0; i < ENUM.TABLE.values().length; i++) {
				Object object;
				try {
					object = tables.getObject(ENUM.TABLE.values()[i]
							+ "");
					map.put(GenTest.firstlower(GenTest.camel(ENUM.TABLE.values()[i]+"")), object);	
				} catch (Exception e) {
					continue;
				}
				
			}
			Object object = map.get(GenTest.firstlower(GenTest.camel(ENUM.TABLE.TABLE_NAME+"")));
			List<DBColumn> column = getListColumn(schema,object+"");
			map.put("list", column);
			BeanUtils.map2Bean(tab, map);
			list.add(tab);
		}
		close(tables,conn);
		return list;
	}
	public static void main(String[] args) throws SQLException, IOException, TemplateException {
		List<DBTable> list = getListTable("PAHCTEST02");
		for (DBTable dbTable : list) {
//			GenTest.camel(dbTable.getTableName()); class
//			dbTable.getRemarks(); comment
//			System.err.println(dbTable.getTableName()+"\t");
//			System.err.println(dbTable.getRemarks()+"\t");
			List<DBColumn> lis = dbTable.getList();
			AbstractBean bean=new AbstractBean();
			bean.setClassName(GenTest.camel(dbTable.getTableName()));
			bean.setPack(pack);
			List<AbstractProperty> listabs=new ArrayList<AbstractProperty>();
			//TODO 常量待續
			for (DBColumn dbColumn : lis) {
				AbstractProperty ab=new AbstractProperty();
				ab.setClassName(dbColumn.getTypeName());
				ab.setProperty(GenTest.firstlower(GenTest.camel(dbColumn.getColumnName())));
				ab.setComment(dbColumn.getRemarks()!=null?dbColumn.getRemarks():"");
				//TODO 常量待續
//				GenTest.firstlower(GenTest.camel(dbColumn.getColumnName())); field
//				System.err.println(dbColumn.getColumnName());
//				dbColumn.getTypeName(); class
//				System.err.println(dbColumn.getTypeName());
//				dbColumn.getRemarks() comment
				//System.err.println(dbColumn.getRemarks());
				listabs.add(ab);
			}
			bean.setList(listabs);
			GenTest.getByBean(bean);
		}
	}
}
