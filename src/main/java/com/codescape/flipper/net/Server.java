package com.codescape.flipper.net;

import java.io.IOException;
import java.net.ServerSocket;

/**
	Server listens for incoming TCP connections on a specific port and starts new Session(s)
*/

public class Server extends Thread {
	private boolean listen;
	private int port;
	private ServerSocket socket;
	
	public Server(int port) throws IOException {
		this.setSocket(new ServerSocket(port));
		this.setPort(port);
		this.setListen(true);
	}
	
	public int getPort() { return(this.port); }
	
	public void run() {
		try {
			while (this.listen) {
				new Session(socket.accept()).start();
				
				System.out.println("New connection");
			}
		} catch (IOException e) {
			System.out.println("An error occurred on new connection");
		}
	}
	
	public void setListen(boolean listen) { this.listen = listen; }
	public void setPort(int port) { this.port = port; }
	public void setSocket(ServerSocket socket) { this.socket = socket; }
}
