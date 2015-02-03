package com.yang.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.yang.core.AbstractProperty;
import com.yang.core.BeanUtils;
import com.yang.core.Constant;
import com.yang.core.Property;
import com.yang.core.StringUtil;

public class JavaBeanTmp {
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
		p.setProperty("date");
		p2.setClassName("java.lang.String");
		p2.setProperty("id");
		Constant con=BeanUtils.beanFactory(Constant.class);
		con.setClassName("com.yang.test.TT");
//		Set<String> set=new HashSet<String>();
//		set.add("java.util.Date");
		List<Property> prop = Arrays.asList(new Property[] { p,p2 });
		con.setList(prop);
//		con.setImports(set);
//		con.setPack("com.yang.test");
		con.format();
		byte[] b = javaBean(con, 0).getBytes();
		os.write(b);
		os.flush();
		os.close();
	}

	public  String javaBean(Constant constant,int indent) {
		String pack=constant.getPack();
		String className=constant.getClassName();
		Set<String> imps=constant.getImports();
		List<? extends AbstractProperty> prop=constant.getList();
		StringBuffer sb = new StringBuffer();
		sb.append(Const.PACKAGE).append(Const.BLANK).append(pack).append(Const.SEMICOLON)
				.append(Const.NEWLINE).append(Const.NEWLINE);
		for (String string : imps) {
			sb.append(Const.IMPORT).append(Const.BLANK).append(string).append(Const.SEMICOLON)
					.append(Const.NEWLINE);
		}
		sb.append(Const.NEWLINE);
		sb.append(Const.PUBLIC).append(Const.BLANK).append(Const.CLASS).append(Const.BLANK)
				.append(className).append("{").append(Const.NEWLINE).append(Const.NEWLINE);
		indent++;
		//field
		for (AbstractProperty pro : prop) {
			tab(sb, indent);
			sb.append(Const.PRIVATE).append(Const.BLANK).append(pro.getClassName())
					.append(Const.BLANK).append(pro.getProperty()).append(Const.SEMICOLON)
					.append(Const.NEWLINE);
		}
		sb.append(Const.NEWLINE);
		//set method
		for (AbstractProperty pro : prop) {
			tab(sb, indent);
			sb.append(Const.PUBLIC).append(Const.BLANK).append("void").append(Const.BLANK)
					.append("set")
					.append(StringUtil.firstUpper(pro.getProperty()))
					.append("(").append(pro.getClassName()).append(Const.BLANK)
					.append(pro.getProperty()).append(")").append("{")
					.append(Const.NEWLINE);
			indent++;
			tab(sb, indent);
			sb.append("this.").append(pro.getProperty()).append("=")
					.append(pro.getProperty()).append(Const.SEMICOLON)
					.append(Const.NEWLINE);
			indent--;
			tab(sb, indent);
			sb.append("}").append(Const.NEWLINE);

		}
		sb.append(Const.NEWLINE);
		//get method
		for (AbstractProperty pro : prop) {
			tab(sb, indent);
			sb.append(Const.PUBLIC).append(Const.BLANK).append(pro.getClassName()).append(Const.BLANK)
					.append("get")
					.append(StringUtil.firstUpper(pro.getProperty()))
					.append("()").append("{").append(Const.NEWLINE);
			indent++;
			tab(sb, indent);
			sb.append("return ").append(pro.getProperty()).append(Const.SEMICOLON)
					.append(Const.NEWLINE);
			indent--;
			tab(sb, indent);
			sb.append("}").append(Const.NEWLINE);

		}
		indent--;
		tab(sb, indent);
		sb.append(Const.NEWLINE).append("}");
		return sb.toString();
	}

	public static void tab(StringBuffer sb, int index) {
		for (int i = 0; i < index; i++) {
			sb.append(Const.TAB);
		}
	}
}
