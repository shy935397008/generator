package com.yang.core.pattern;

import org.junit.Test;

/**
 *装饰者模式 
 *
 */
public class DecoratorTest {

	interface IDecorator{
		void print();
	}
	class Decorat implements IDecorator{

		public void print() {
			System.err.println("hhhhh");
		}
	}
	class Decorator implements IDecorator{
		private IDecorator decorator;
		
		public Decorator(IDecorator decorator) {
			super();
			this.decorator = decorator;
		}

		public void print() {
			System.err.println("before...........");
			decorator.print();
			System.err.println("after............");
		}
	}
	@Test
	public void test01(){
		IDecorator d=new Decorat();
		Decorator dt=new Decorator(d);
		dt.print();
	}
}
