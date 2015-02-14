package com.yang.stock;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ul")
@XmlAccessorType
public class UL {

	private List<LI> list=new ArrayList<LI>();

	@XmlElement(name="li")
	public List<LI> getList() {
		return list;
	}

	public void setList(List<LI> list) {
		this.list = list;
	}
	
}
