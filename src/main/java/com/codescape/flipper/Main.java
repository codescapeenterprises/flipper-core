package com.codescape.flipper;

import com.codescape.flipper.net.Server;
import com.codescape.flipper.robot.Robot;

import java.io.IOException;

/**
	Main provides CLI for the application
*/

public class Main {
	// Parse and run command
	public void cmd(String cmd[]) {
		if (cmd.length >= 1) {
			switch (cmd[0]) {
				case "start": this.start(); break;
				default: System.out.println("'" + cmd[0] + "' is an unknown command"); break;
			}
		} else { System.out.println("Usage: start"); }
	}
	
    public static void main(String[] args) {
		Main main = new Main();
		
		System.out.println("flipper 1.0.0");
		
		main.cmd(args);
    }
	
	public void start() {
		try {
			new Server(8888).start();
		} catch (IOException e) {
			System.out.println("Couldn't start server, already running?");
		}
		
		new Robot().start();
	}
}
