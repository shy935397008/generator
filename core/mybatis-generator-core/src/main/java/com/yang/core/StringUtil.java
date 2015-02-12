package com.yang.core;

public class StringUtil {

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstUpper(String str) {
		char[] cs = str.toCharArray();
		if (Character.isLowerCase(cs[0])) {
			cs[0] -= 32;
		}
		return new String(cs);
	}

	/**
	 * 首字母大写
	 * 
	 * @param str
	 * @return
	 */
	public static String firstlower(String str) {
		char[] cs = str.toCharArray();
		if (Character.isUpperCase(cs[0])) {
			cs[0] += 32;
		}
		return new String(cs);
	}
	/**
	 * 驼峰书写
	 * 
	 * @param str
	 *            abc_abc→AbcAbc
	 * @return
	 */
	public static String camel(String str) {
		str = str.toLowerCase();
		if (str.contains("_")) {
			String[] split = str.split("_");
			StringBuilder sb = new StringBuilder();
			for (String string : split) {
				sb.append(firstUpper(string));
			}
			return sb.toString();
		}
		return firstUpper(str);
	}
	/**
	 * 文件内容缩进
	 * @param sb
	 * @param indent
	 */
	public static void tab(StringBuffer sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append(Const.TAB);
		}
	}
}
