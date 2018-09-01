package com.codescape.loki;

public abstract class Worker {
	public boolean active = true;
	
	public abstract void retire();
	
	public void active(boolean active) { this.active = active; }
}
