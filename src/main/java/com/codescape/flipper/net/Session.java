package com.codescape.flipper.net;

import java.net.Socket;

/**
	Handles the data communication from a TCP connection
*/

public class Session extends Thread {
	private Socket socket;
	
	public Session(Socket socket) {
		this.setSocket(socket);
	}
	
	public void run() {
		
	}
	
	public void setSocket(Socket socket) { this.socket = socket; }
}
