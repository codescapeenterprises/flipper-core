package com.codescape.loki.net;

import  com.codescape.loki.Worker;

import java.io.IOException;
import java.net.Socket;

/**
	Handles the data communication from a TCP connection
*/

public class Session extends Worker implements Runnable  {
	private Socket socket;
	
	public Session(Socket socket) {
		this.setSocket(socket);
	}
	
	public void auth(/*User user*/) {
		
	}
	
	public void run() {
		while (this.active) {
			
		}
	}
	
	public void retire() {
		this.active(false);
		
		try {
			this.socket.close();
		} catch (IOException e) {
			
		}
	}
	
	public void setSocket(Socket socket) { this.socket = socket; }
}
