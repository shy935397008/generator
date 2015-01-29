package com.yang.core;

import java.util.ArrayList;

/**
 *数据库用户表 
 *
 */
public class DBTable {

	private String tableCat;
	private String tableSchem;
	private String tableName;
	private String tableType;
	private String remarks;
	private String typeCat;
	private String typeSchem;
	private String typeName;
	private String selfReferencingColName;
	private String refGeneration;

	private ArrayList<DBColumn> list=new ArrayList<DBColumn>();
	public String getTableCat() {
		return tableCat;
	}

	public void setTableCat(String tableCat) {
		this.tableCat = tableCat;
	}

	public String getTableSchem() {
		return tableSchem;
	}

	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTypeCat() {
		return typeCat;
	}

	public void setTypeCat(String typeCat) {
		this.typeCat = typeCat;
	}

	public String getTypeSchem() {
		return typeSchem;
	}

	public void setTypeSchem(String typeSchem) {
		this.typeSchem = typeSchem;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSelfReferencingColName() {
		return selfReferencingColName;
	}

	public void setSelfReferencingColName(String selfReferencingColName) {
		this.selfReferencingColName = selfReferencingColName;
	}

	public String getRefGeneration() {
		return refGeneration;
	}

	public void setRefGeneration(String refGeneration) {
		this.refGeneration = refGeneration;
	}

	public ArrayList<DBColumn> getList() {
		return list;
	}

	public void setList(ArrayList<DBColumn> list) {
		this.list = list;
	}
	
}
