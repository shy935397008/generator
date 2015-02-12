package com.yang.core.pattern;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * 观察者模式
 *
 */
public class ObserverTest {

	public interface IWatcher {
		void update(Object obj);
	}

	public interface ISubject {
		void addWatcher(IWatcher w);

		void removeWatcher(IWatcher w);

		void notifyWatcher();
	}

	class Watcher implements IWatcher {

		private String id;

		public Watcher(String id) {
			this.id = id;
		}

		public void update(Object obj) {
			System.err.println(id);
		}

	}

	class Subject implements ISubject {
		List<IWatcher> list = new ArrayList<IWatcher>();

		public void addWatcher(IWatcher w) {
			list.add(w);
		}

		public void removeWatcher(IWatcher w) {
			list.remove(w);
		}

		public void notifyWatcher() {
			for (IWatcher l : list) {
				l.update(this);
			}
		}

	}

	@Test
	public void test01() {
		IWatcher w = new Watcher("w");
		IWatcher w1 = new Watcher("1");
		IWatcher w2 = new Watcher("2");
		IWatcher w3 = new Watcher("3");
		ISubject s = new Subject();
		s.addWatcher(w);
		s.addWatcher(w1);
		s.addWatcher(w2);
		s.addWatcher(w3);
		s.notifyWatcher();
		s.removeWatcher(w3);
		s.notifyWatcher();
	}
}
