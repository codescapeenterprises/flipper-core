package com.codescape.flipper.net;

import  com.codescape.flipper.Worker;

import java.net.Socket;

/**
	Handles the data communication from a TCP connection
*/

public class Session implements Runnable, Worker {
	private boolean active;
	private Socket socket;
	
	public Session(Socket socket) {
		this.setSocket(socket);
	}
	
	public void auth(User user) {
		
	}
	
	public void run() {
		while (this.active) {
			
		}
	}
	
	public void retire() {
		this.active(false);
		this.socket.close();
	}
	
	public void setSocket(Socket socket) { this.socket = socket; }
}
