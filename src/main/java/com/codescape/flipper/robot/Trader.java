package com.codescape.flipper.robot;

import com.codescape.flipper.market.Order;

public class Trader extends Thread {
	private int id;
	private int accountId;
	private String name;
	
	public void run() {
		
	}
	
	public void placeOrder(Order order) {
		
	}
	
	public int getTraderId() { return(this.id); }
	public String getTraderName() { return(this.name); }
	
	public void setAccountId(int accountId) { this.accountId = accountId; }
	public void setTraderId(int id) { this.id = id; }
	public void setTraderName(String name) { this.name = name; }
}
