package com.yang.stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yang.core.database.ObjectConnection;
import com.yang.stock.bean.Stocks;

public class StockService {

	private StockDao dao=new StockDao();
	private ObjectConnection connection=new ObjectConnection();
	
	public void insert() throws SQLException{
		Object object = connection.getFromURL();
		if(object instanceof List){
			@SuppressWarnings("unchecked")
			List<Stocks> list=(List<Stocks>) object;
			List<Stocks> lis=new ArrayList<Stocks>();
			for (Stocks stocks : list) {
				int i = dao.count(stocks);
				if(i==0){
					lis.add(stocks);
				}
			}
			dao.batchInsert(lis);
		}
	}
	public static void main(String[] args) {
		try {
			new StockService().insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
