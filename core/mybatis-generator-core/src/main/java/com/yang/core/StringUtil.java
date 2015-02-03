package com.yang.core;

public class StringUtil {

	/**
	 * ����ĸ��д
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
	 * ����ĸ��д
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
	 * �շ���д
	 * 
	 * @param str
	 *            abc_abc��AbcAbc
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
}
