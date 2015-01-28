package com.yang.generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbstractBean {

	private String className;
	private String pack;
	private List<? extends Property> list = new ArrayList<Property>();
	private Set<String> imports=new HashSet<String>();

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public List<? extends Property> getList() {
		return list;
	}

	public void setList(List<? extends Property> list) {
		this.list = list;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}
	
}
