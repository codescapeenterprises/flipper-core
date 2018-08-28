package com.codescape.flipper;

public interface Worker {
	private boolean active;
	
	public void retire();
	
	default public void active(boolean active) { this.active = active; }
}
