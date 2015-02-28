package com.yang.core;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.SQLXML;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

public class TypeConvert {

//	private Map<Object,Object> map=new HashMap<Object,Object>();
//	public TypeConvert(){
//		setMap();
//	}
//	public Object getByCode(Object obj){
//		return map.get(obj);
//	}
//	private void setMap(){
//		map.put(Types.ARRAY, Array.class.getName());
//		map.put(Types.BIGINT, BigInteger.class.getName());
//	}
	/**
	 * jdbc type convert
	 * @param big
	 * @return
	 */
	public static String getType(BigDecimal big){
		String type="void";
		if(big.intValue()==Types.BIT){
			type="Boolean";return type;
		}else if(big.intValue()==Types.TINYINT){
			type="Bute";return type;
		}else if(big.intValue()==Types.SMALLINT){
			type="Integer";return type;
		}else if(big.intValue()==Types.INTEGER){
			type="Integer";return type;
		}else if(big.intValue()==Types.BIGINT){
			type="Long";return type;
		}else if(big.intValue()==Types.FLOAT){
			type="Double";return type;
		}else if(big.intValue()==Types.REAL){
			type="Float";return type;
		}else if(big.intValue()==Types.DOUBLE){
			type="Double";return type;
		}else if(big.intValue()==Types.NUMERIC){
			type="java.math.BigDecimal";return type;
		}else if(big.intValue()==Types.DECIMAL){
			type=BigDecimal.class.getName();return type;
		}else if(big.intValue()==Types.CHAR){
			type="String";return type;
		}else if(big.intValue()==Types.VARCHAR){
			type="String";return type;
		}else if(big.intValue()==Types.LONGVARCHAR){
			type="String";return type;
		}else if(big.intValue()==Types.DATE){
			type=Date.class.getName();return type;
		}else if(big.intValue()==Types.TIME){
			type=Time.class.getName();return type;
		}else if(big.intValue()==Types.TIMESTAMP){
			type=Timestamp.class.getName();return type;
		}else if(big.intValue()==Types.BINARY){
			type="byte[]";return type;
		}else if(big.intValue()==Types.VARBINARY){
			type="byte[]";return type;
		}else if(big.intValue()==Types.LONGVARBINARY){
			type="byte[]";return type;
		}else if(big.intValue()==Types.NULL){
			return type;
		}else if(big.intValue()==Types.OTHER){
			type=Object.class.getName();return type;
		}else if(big.intValue()==Types.JAVA_OBJECT){
			type=Object.class.getName();return type;
		}else if(big.intValue()==Types.DISTINCT){
			type=Object.class.getName();return type;
		}else if(big.intValue()==Types.STRUCT){
			type=Struct.class.getName();return type;
		}else if(big.intValue()==Types.ARRAY){
			type=Array.class.getName();return type;
		}else if(big.intValue()==Types.BLOB){
			type=Blob.class.getName();return type;
		}else if(big.intValue()==Types.CLOB){
			type=Clob.class.getName();return type;
		}else if(big.intValue()==Types.REF){
			type=Ref.class.getName();return type;
		}else if(big.intValue()==Types.DATALINK){
			type=Object.class.getName();return type;
		}else if(big.intValue()==Types.NCHAR){
			type="String";return type;
		}else if(big.intValue()==Types.NVARCHAR){
			type="String";return type;
		}else if(big.intValue()==Types.LONGNVARCHAR){
			type="String";return type;
		}else if(big.intValue()==Types.NCLOB){
			type=NClob.class.getName();return type;
		}else if(big.intValue()==Types.SQLXML){
			type=SQLXML.class.getName();return type;
		}
		return type;
	}
	
}
