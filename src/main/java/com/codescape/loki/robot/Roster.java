package com.codescape.loki.robot;

import java.util.LinkedList;
import java.util.Iterator;

/**
	Keeps track of Trader(s)
*/

public class Roster extends LinkedList<Trader> {
	/**
		Returns Trader by id or name
	*/
	public Trader get(String idOrName) throws TraderException {
		Iterator<Trader> iterator = super.iterator();
		boolean exist = false;
		Trader trader = null;
		
		// Iterate through Roster
		while (iterator.hasNext() && !exist) {
			trader = iterator.next();
			
			if (String.valueOf(trader.getTraderId()).equals(idOrName) ||
				trader.getTraderName().equals(idOrName)) {
				exist = true;
			}
		}
		
		// If no Trader exist, throw exception
		if (!exist) throw new TraderException("'" + idOrName + "' trader does not exist");
		
		return(trader);
	}
}
