package com.yang.core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface MapRow<T> {

	T rowMap(ResultSet rs) throws SQLException;
}
