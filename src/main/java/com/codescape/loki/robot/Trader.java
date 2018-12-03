package com.codescape.loki.robot;

import com.codescape.loki.Worker;
import com.codescape.loki.market.Order;

public class Trader extends Worker implements Runnable {
	private int id;
	private int accountId;
	private String name;
	
	public Trader(int id, int accountId, String name) {
		
	}
	
	public void run() {
		
	}
	
	public void placeOrder(Order order) {
		
	}
	
	public int getAccountId() { return(this.accountId); }
	public int getTraderId() { return(this.id); }
	public String getTraderName() { return(this.name); }
	
	public void setAccountId(int accountId) { this.accountId = accountId; }
	public void setTraderId(int id) { this.id = id; }
	public void setTraderName(String name) { this.name = name; }
}
