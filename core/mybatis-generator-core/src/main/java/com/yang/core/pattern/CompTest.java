package com.yang.core.pattern;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

/**
 * 组合模式
 * 
 */
public class CompTest {

	interface Com {
		void coppy();
	}

	class Root implements Com {

		List<Com> ls = new ArrayList<Com>();

		public Root() {
		}

		public void remove(Com com) {
			ls.remove(com);
		}

		public void add(Com com) {
			ls.add(com);
		}

		public void coppy() {
			for (Com it : ls) {
				it.coppy();
			}
		}

	}

	class Sub implements Com {

		private String name;

		public Sub(String name) {
			super();
			this.name = name;
		}

		public void coppy() {
			System.err.println("coppy ok?" + name);
		}

	}

	@Test
	public void test01() {
//		CompTest com = new CompTest();
		Sub sub = new Sub("tom");
		Sub sub1 = new Sub("tom1");
		Sub sub2 = new Sub("tom2");
		Root root =new Root();
		Calendar ca=Calendar.getInstance();
		System.err.println(ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.err.println(ca.getActualMinimum(Calendar.DAY_OF_MONTH));
//		int maximum = ca.getMaximum();
//		System.err.println(ca.get(Calendar.MONTH)+2);
		root.add(sub);
		root.add(sub1);
		root.add(sub2);
		root.coppy();
	}
}
