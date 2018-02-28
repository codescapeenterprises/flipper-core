package com.codescape.flipper.market;

public abstract class Order {
	public static final int SELL = 0;
	public static final int BUY = 1;
	
	public static final int LIMIT = 2;
	public static final int MARKET = 3;
	
	public abstract void execute();
}
