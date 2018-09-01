package com.codescape.loki.net;

import  com.codescape.loki.Main;
import  com.codescape.loki.Worker;
import  com.codescape.loki.robot.Robot;

import java.io.IOException;
import java.net.ServerSocket;

import javax.net.ssl.*;

/**
	Server listens for incoming TCP connections on a specific port and starts new Session(s)
*/
public class Server extends Worker implements Runnable {
	private int port;
	private ServerSocket socket;
	
	public Server(int port) throws IOException {
		this.setPort(port);
		this.setSocket(SSLServerSocketFactory.getDefault().createServerSocket(port));
	}
	
	public int getPort() { return(this.port); }
	
	public void run() {
		try {
			while (this.active) {
				new Thread(new Session(socket.accept())).start();
				
				//main.console().infoMessageln("New connection");
			}
		} catch (IOException e) {
			//System.out.println("An error occurred on new connection");
		}
	}
	
	public void retire() {
		this.active(false);
		
		try {
			this.socket.close();
		} catch (IOException e) {
			
		}
	}
	
	//public void setListen(boolean listen) { this.listen = listen; }
	public void setPort(int port) { this.port = port; }
	public void setSocket(ServerSocket socket) { this.socket = socket; }
}
