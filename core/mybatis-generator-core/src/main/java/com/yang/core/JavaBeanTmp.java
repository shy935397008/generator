package com.yang.core;

import java.util.List;
import java.util.Set;


import com.yang.core.AbstractProperty;
import com.yang.core.StringUtil;

public class JavaBeanTmp {

	/**
	 * JavaBean Ä£°å
	 * @param constant JavaBean
	 * @param indent Ëõ½øÖµ
	 * @return
	 */
	public  String javaBean(AbstractBean constant,int indent) {
		String pack=constant.getPack();
		String className=constant.getClassName();
		Set<String> imps=constant.getImports();
		List<? extends AbstractProperty> prop=constant.getList();
		StringBuffer sb = new StringBuffer();
		//package
		sb.append(Const.PACKAGE).append(Const.BLANK).append(pack).append(Const.SEMICOLON)
				.append(Const.NEWLINE).append(Const.NEWLINE);
		//import
		for (String string : imps) {
			sb.append(Const.IMPORT).append(Const.BLANK).append(string).append(Const.SEMICOLON)
					.append(Const.NEWLINE);
		}
		sb.append(Const.NEWLINE);
		//class
		if(constant.getComment()!=null){
			sb.append("/**").append(Const.NEWLINE);
			sb.append("** ").append(constant.getClassName()).append(" ").append(constant.getComment()).append(Const.NEWLINE);
			sb.append("**/").append(Const.NEWLINE);
		}
		sb.append(Const.PUBLIC).append(Const.BLANK).append(Const.CLASS).append(Const.BLANK)
				.append(className).append("{").append(Const.NEWLINE).append(Const.NEWLINE);
		indent++;
		//field
		for (AbstractProperty pro : prop) {
			System.err.println(pro.getClass().getName());
			if(pro.getComment()!=null){
				tab(sb, indent);
				sb.append("/**").append(Const.NEWLINE);
				tab(sb, indent);
				sb.append("**").append(pro.getClassName()).append(" ").append(pro.getComment()).append(Const.NEWLINE);
				tab(sb, indent);
				sb.append("**/").append(Const.NEWLINE);
			}
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

	/**@deprecated
	 * {@link StringUtil#tab(StringBuffer, int)}
	 * @param sb
	 * @param indent
	 */
	public static void tab(StringBuffer sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append(Const.TAB);
		}
	}
}
