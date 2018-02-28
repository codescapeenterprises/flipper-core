package com.codescape.flipper.robot;

import com.codescape.flipper.sql;

import java.sql.Statement;
import java.util.LinkedList;

public class Roster extends LinkedList<Trader> {
	private MySQL mysql;
	
	public Roster(MySQL mysql) {
		this.setMySQL(mysql);
	}
	
	public Trader get(String idOrName) throws NumberFormatException, NoTraderException {
		Trader trader = null;
		
		for (int i = 0; i < size(); i++) {
			trader = get(i);
			
			if (trader.getTraderId() == Integer.parseInt(idOrName) || trader.getTraderName().equals(idOrName)) {
				i = size();
			} else trader = null;
		}
		
		if (trader == null) throw new NoTraderException("'" + idOrName + "' trader does not exist");
		
		return(trader);
	}
	
	public loadTrader(String idOrName) {
		Statement stmt = this.mysql.query("SELECT * FROM `traders` WHERE `id` = '" + idOrName + "' OR `name` = '" + idOrName + "';");
	}
	
	public void setMySQL(MySQL mysql) { this.mysql = mysql; }
}
