package com.yang.stock;

import java.util.Timer;
import java.util.TimerTask;

public class StockTimer extends Timer {

	private boolean Active = false;
	private long repeat=5000;
	
	private TimerTask task;
	
	public StockTimer() {
		super();
	}
	
	public StockTimer(TimerTask task) {
		super();
		this.task = task;
	}


	public TimerTask getTask() {
		return task;
	}


	public void setTask(TimerTask task) {
		this.task = task;
	}


	public StockTimer(boolean isDaemon) {
		super(isDaemon);
	}

	public StockTimer(String name, boolean isDaemon) {
		super(name, isDaemon);
	}

	public StockTimer(String name) {
		super(name);
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public StockTimer stop() throws InstantiationException,
			IllegalAccessException {
		if (Active) {
			cancel();
			this.Active = false;
			StockTimer instance = this.getClass().newInstance();
			return instance;
		}
		return this;
	}

	public void start(TimerTask task) {
		if (!Active) {
			this.schedule(task, 1, repeat);
			this.Active=true;
		}else{
			
		}
	}

	public long getRepeat() {
		return repeat;
	}

	public void setRepeat(long repeat) {
		this.repeat = repeat;
	}

}
