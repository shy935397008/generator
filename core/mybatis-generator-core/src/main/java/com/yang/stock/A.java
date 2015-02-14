package com.yang.stock;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class A {

	private String href;
	private String title;
	private String value;
	public String getHref() {
		return href;
	}
	@XmlAttribute
	public void setHref(String href) {
		this.href = href;
	}
	@XmlAttribute
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@XmlValue
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
