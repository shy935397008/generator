package com.yang.xml.element.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Bean {

	private String className;
	private List<Property> list;
	@XmlAttribute
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@XmlElement
	public List<Property> getList() {
		return list;
	}
	public void setList(List<Property> list) {
		this.list = list;
	}
	
}
