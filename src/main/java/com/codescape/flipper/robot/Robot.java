package com.codescape.flipper.robot;

import com.codescape.flipper.sql.MySQL;

import java.util.LinkedList;

public class Robot extends Thread {
	private Roster roster;
	
	public Robot() {
		//this.roster(new Roster());
	}
	
	public void run() {
		this.initTraders();
	}
	
	// Initializing traders
	public void initTraders() {
		
	}
	
	public void setRoster(Roster roster) { this.roster = roster; }
}
