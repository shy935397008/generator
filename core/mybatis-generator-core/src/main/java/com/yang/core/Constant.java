package com.yang.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Constant extends AbstractBean{

	/**
	 * 自动生成相关属性 <br>
	 * ClassName<br>
	 * Imports
	 */
	public void format(){
		Set<String> sets=new HashSet<String>();
		List<? extends AbstractProperty> prop = getList();
		for (AbstractProperty abstractProperty : prop) {
			String name = abstractProperty.getClassName();
			if(name!=null&&name.contains(".")){
				Set<String> set = getImports();
				if(set!=null){
					String[] split = name.split("\\.");
					if(split.length>0){
						if(!name.contains("java.lang")){
							sets.add(name);							
						}
						name=split[split.length-1];
						abstractProperty.setClassName(name);
					}
				}
			}
			setImports(sets);
		}
		String name = getClassName();
		if(name!=null&&name.contains(".")){
			String[] split = name.split("\\.");
			StringBuffer sb=new StringBuffer();
			if(split.length>0){
				for (int i = 0; i < split.length-1; i++) {
					sb.append(split[i]).append(".");
				}
				if(sb.length()>0){
					setPack(sb.substring(0, sb.length()-1));
				}
				name=split[split.length-1];
			}
			setClassName(name);
		}
	}
	
}
