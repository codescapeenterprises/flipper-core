package com.codescape.flipper.net;

import  com.codescape.flipper.Main;
import  com.codescape.flipper.Worker;
import  com.codescape.flipper.robot.Robot;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.*;

/**
	Server listens for incoming TCP connections on a specific port and starts new Session(s)
*/
public class Server implements Runnable, Worker {
	private boolean active;
	private int port;
	private ServerSocket socket;
	
	public Server(int port) throws IOException {
		this.setPort(port);
		this.setSocket(SSLServerSocketFactory.getDefault().createServerSocket(port));
		this.active(true);
	}
	
	public int getPort() { return(this.port); }
	
	public void run() {
		try {
			while (this.active) {
				new Thread(new Session(socket.accept())).start();
				
				//main.console().infoMessageln("New connection");
			}
		} catch (IOException e) {
			//main.console().errorMessageln("An error occurred on new connection");
		}
	}
	
	public void retire() {
		this.active(false);
		this.socket.close();
	}
	
	public void setListen(boolean listen) { this.listen = listen; }
	public void setPort(int port) { this.port = port; }
	public void setSocket(ServerSocket socket) { this.socket = socket; }
}
