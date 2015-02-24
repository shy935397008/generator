package com.yang.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.yang.xml.element.beans.Beans;

public class TestXML {

	@Test
	public void test01() throws JAXBException{
		JAXBContext ctx=JAXBContext.newInstance(Beans.class);
		Unmarshaller unmarshaller = ctx.createUnmarshaller();
		Object unmarshal = unmarshaller.unmarshal(TestXML.class.getClassLoader().getResourceAsStream("bean.xml"));
		System.err.println(unmarshal.getClass().getName());
	}
}
