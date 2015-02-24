package com.yang.xml.element.beans;

import javax.xml.bind.annotation.XmlAttribute;

public class Property {

	private String className;
	private String property;

	@XmlAttribute
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@XmlAttribute
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

}
