package com.yang.generator;

import java.util.ArrayList;
import java.util.List;

public class AbstractBean {

	private String className;
	private String pack;
	private List<? extends AbstractPropertity> list = new ArrayList< AbstractPropertity>();

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

	public List<? extends AbstractPropertity> getList() {
		return list;
	}

	public void setList(List<? extends AbstractPropertity> list) {
		this.list = list;
	}
}
