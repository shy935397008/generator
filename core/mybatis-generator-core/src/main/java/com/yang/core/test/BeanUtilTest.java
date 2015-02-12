package com.yang.core.test;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.yang.core.BeanUtils;

public class BeanUtilTest {

	private int num=9000;
	@Test
	public void test01() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for (int i = 0; i < num; i++) {
			BeanUtils.beanFactory(Tes.class);
		}
	}
//	@Test
//	public void test02() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
//		BeanUtils.beanFactory(Tes.class,"hello");
//	}
	@Test
	public void test03(){
		for (int i = 0; i < num; i++) {
			new Tes();
		}
	}
}
