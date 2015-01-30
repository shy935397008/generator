package com.yang.core.test;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.yang.core.BeanUtils;

public class BeanUtilTest {

	@Test
	public void test01() throws InstantiationException, IllegalAccessException{
		BeanUtils.beanFactory(Tes.class);
	}
	@Test
	public void test02() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException{
		BeanUtils.beanFactory(Tes.class,"hello");
	}
}
