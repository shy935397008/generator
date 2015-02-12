package com.yang.core.pattern;

import org.junit.Test;
/**
 * 
 *²ßÂÔÄ£Ê½
 */
public class StrageTest {

	interface IStrage {
		void play();

		void sound();
	}

	public class Context {
		private IStrage strage;

		public Context(IStrage strage) {
			this.strage = strage;
		}

		public void play() {
			strage.play();
		}

		public void sound() {
			strage.sound();
		}
	}

	class Green implements IStrage {

		public void play() {

			System.err.println("green play");
		}

		public void sound() {
			System.err.println("green sound");
		}

	}

	class Gray implements IStrage {

		public void play() {
			System.err.println("gray play");
		}

		public void sound() {
			System.err.println("gray sound");
		}
	}

	@Test
	public void test02() {
		Gray gray = new Gray();
		Green green = new Green();
		Context context = new Context(gray);
		context.play();
		context.sound();
		context = new Context(green);
		context.play();
		context.sound();
	}
}
