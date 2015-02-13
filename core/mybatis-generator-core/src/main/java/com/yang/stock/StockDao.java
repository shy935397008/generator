package com.yang.stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.yang.core.dao.DBHelp;
import com.yang.core.database.ConnectionInterface;
import com.yang.core.database.MySqlConnection;
import com.yang.stock.bean.Stocks;

public class StockDao {

	private ConnectionInterface connectionInterface = new MySqlConnection();
	DBHelp dh = new DBHelp(connectionInterface);

	public int insert(Stocks stock) throws SQLException {
		String sql = "insert int t_stock(id,name,code,operation,date)values(?,?,?,?,?)";
		return dh.update(sql, stock.getId(), stock.getName(), stock.getCode(),
				stock.getOperation(), stock.getDate());
	}
	public int count(Stocks stock) throws SQLException{
		String sql="select count(*) from t_stock  where `name`=? and `code`=? and `operation`=? and `date`=?";
		return dh.count(sql, stock.getName(), stock.getCode(),
				stock.getOperation(), stock.getDate());
	}

	public void batchInsert(List<Stocks> list) throws SQLException {
		if(list==null||list.isEmpty()){
			return;
		}
		String sql = "insert into t_stock(`id`,`name`,`code`,`operation`,`date`)values(?,?,?,?,?)";
		List<Object[]> lis = new ArrayList<Object[]>();
		for (Stocks stocks : list) {
			UUID id=UUID.randomUUID();
			Object[] obj = new Object[5];
			obj[0] = id.toString();
			obj[1] = stocks.getName();
			obj[2] = stocks.getCode();
			obj[3] = stocks.getOperation();
			obj[4] = stocks.getDate();
			lis.add(obj);
		}
		dh.batchUpdate(sql, lis);
	}
}
