package com.yang.core.test.tmp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.yang.core.BeanUtils;
import com.yang.core.Constant;
import com.yang.core.JavaBeanTmp;
import com.yang.core.Property;

public class JavaBeanTmpTest {

	@Test
	public void test01() throws IOException, IllegalArgumentException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {
		File dir = new File("com\\yang\\test");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		OutputStream os = new FileOutputStream("com\\yang\\test\\TT.java");
		Property p = BeanUtils.beanFactory(Property.class);
		Property p2 = BeanUtils.beanFactory(Property.class);
		p.setClassName("java.util.Date");
		p.setComment("时间");
		p.setValue("时间");
		p.setProperty("date");
		p2.setClassName("java.lang.String");
		p2.setProperty("id");
		p2.setComment("编号");
		p.setValue("编号");
		Constant con=BeanUtils.beanFactory(Constant.class);
		con.setClassName("com.yang.test.TT");
		List<Property> prop = Arrays.asList(new Property[] { p,p2 });
		con.setList(prop);
		con.setComment("tts");
		con.format();
		byte[] b = BeanUtils.beanFactory(JavaBeanTmp.class).javaBean(con, 0).getBytes();
		os.write(b);
		os.flush();
		os.close();
	}
}
