package com.yang.xml.element.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="http://shy935397008.github.io")
public class Beans {

	private List<Bean> list;
	@XmlElement
	public List<Bean> getList() {
		return list;
	}

	public void setList(List<Bean> list) {
		this.list = list;
	}
	
}
