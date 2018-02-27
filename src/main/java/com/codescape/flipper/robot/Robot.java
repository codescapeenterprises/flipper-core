package com.codescape.flipper.robot;

import java.util.LinkedList;

public class Robot extends Thread {
	private LinkedList<Trader> traders;
	
	public Robot() {
		traders = new LinkedList<Trader>();
	}
	
	public void run() {
		this.initTraders();
	}
	
	// Initializing traders
	public void initTraders() {
		
	}
	
	public void startTrader() {
		
	}
	
	public void stopTrader() {
		
	}
}
