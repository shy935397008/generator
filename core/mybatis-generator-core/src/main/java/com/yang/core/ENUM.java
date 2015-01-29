package com.yang.core;

public class ENUM {

	public enum COLUMN{
		TABLE_CAT
		,TABLE_SCHEM
		,TABLE_NAME
		,COLUMN_NAME
		,DATA_TYPE
		,TYPE_NAME
		,COLUMN_SIZE
		,BUFFER_LENGTH
		,DECIMAL_DIGITS
		,NUM_PREC_RADIX
		,NULLABLE
		,REMARKS
		,COLUMN_DEF
		,SQL_DATA_TYPE
		,SQL_DATETIME_SUB
		,CHAR_OCTET_LENGTH
		,ORDINAL_POSITION
		,IS_NULLABLE
		,SCOPE_CATLOG
		,SCOPE_SCHEMA
		,SCOPE_TABLE
		,SOURCE_DATA_TYPE
		,IS_AUTOINCREMENT		
	}
	public enum TABLE{
		TABLE_CAT
		,TABLE_SCHEM
		,TABLE_NAME
		,TABLE_TYPE
		,REMARKS
		,TYPE_CAT
		,TYPE_SCHEM
		,TYPE_NAME
		,SELF_REFERENCING_COL_NAME
		,REF_GENERATION
	}
	
	public static COLUMN getColumn(String column) {
		return COLUMN.valueOf(column);
	}
	public static TABLE getTable(String table) {
		return TABLE.valueOf(table);
	}
	
}
