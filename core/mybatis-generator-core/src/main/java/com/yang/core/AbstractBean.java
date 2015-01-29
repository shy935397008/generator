package com.yang.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AbstractBean {

	private String className;
	private String pack;
	private List<? extends AbstractProperty> list;
	private Set<String> imports=new HashSet<String>();

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
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

	public List<? extends AbstractProperty> getList() {
		return list;
	}

	public void setList(List<? extends AbstractProperty> list) {
		this.list = list;
	}

	public Set<String> getImports() {
		return imports;
	}

	public void setImports(Set<String> imports) {
		this.imports = imports;
	}
	
}
