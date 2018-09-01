package com.codescape.loki;

import com.codescape.loki.net.Server;
import com.codescape.loki.robot.Robot;
import com.codescape.loki.sql.MariaDB;
import com.codescape.loki.sql.MariaDBCredentials;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.*;

/**
 * Main class assembles the application, it's the executable class of loki.
 */

public class Main {
	private Configuration config;
	private String configFile;
	private Console console;
	private boolean run;
	private boolean cli;
	private boolean stop;
	
	private LinkedList<Thread> threads;
	private Server server;
	private MariaDB sql;
	private Robot robot;
	
	public static final String commandPrefix = "-";
	
	// Resource files
	public static final String banner = "BANNER";
	public static final String help = "HELP";
	
	// Default parameters
	public static final String defaultConfigFile = "conf" + File.separator	+ "default.conf";
	public static final String defaultHost = "localhost";
	
	public Main() {
		this.setConsole(new Console());
		this.setConfig(new Configuration());
		this.setConfigFile(Main.defaultConfigFile);
		
		this.run(false);
		this.cli(false);
	}
	
	/**
	 * Parses options and looks for commands to execute on an instance of this class.
	 * The {@link #main(String[] args)} function uses this to pass arguments from example a terminal.
	 *
	 * @param   options  {@link String} array to parse
	 * @return           {@link Integer} representing the number of commands found
	 */
	public int parseOptions(String[] options) throws ArgumentException {
		int commandsFound = 0;
		
		// Loop through arguments
		for (int i = 0; i < options.length; i++) {
			// Check if argument is a command
			if (options[i].startsWith(Main.commandPrefix)) {
				String command = options[i].substring(options[i].indexOf(Main.commandPrefix) + 1);
				
				switch (command) {
					case "start": this.run(true); break;
					case "cli": this.cli(true); break;
					case "stop": /*this.stop(true);*/ break;
					case "conf":
						if (i != (options.length - 1)) this.setConfigFile(options[i + 1]);
						else throw new ArgumentException("'conf' command needs a parameter"); break;
					default: throw new ArgumentException("'" + command + "' is an unknown command, run without arguments to see help");
				}
				
				commandsFound++;
			}
		}
		
		return(commandsFound);
	}
	
    public static void main(String[] args) {
		Main main = new Main();
		
		// Prints ASCII art
		try {
			main.printResourceFile(Main.banner);
		} catch (Exception e) {
			main.console.println(Console.ERROR_TAG + " Unable to read ASCII art from " + Main.banner);
		}
		
		// Parses arguments, displays help-message if no command was found
		try {
			if (main.parseOptions(args) < 1) main.printResourceFile(Main.help);
		} catch (ArgumentException e) {
			main.console.println(Console.ERROR_TAG + ' ' + e.getMessage());
		}
		
		// Run application
		try {
			if (main.run) {
				main.loadConfig();
				main.connectToDB();
				main.startServer();
			}
			
			if (main.stop) {
				
			}
		} catch (Exception e) {
			System.exit(1);
		}
    }
	
	/**
	 * Returns {@link Console}
	 *
	 * @return        {@link Console}
	 */
	public Console getConsole() { return(this.console); }
	
	/**
	 * This will try to connect the application to a database with credentials from a configuration file.
	 * Outputs information to the shell.
	 *
	 * @return        {@link Boolean} wheter connection was successfull
	 */
	public boolean connectToDB() throws Exception {
		boolean success = false;
		
		try {
			this.console.print(Console.INFO_TAG + " Connecting to MariaDB: ");
			
			MariaDBCredentials credentials = new MariaDBCredentials(
				this.config.getProperty("mdb_host"),
				this.config.getProperty("mdb_uname"),
				this.config.getProperty("mdb_pword"),
				this.config.getProperty("mdb_db"),
				Integer.parseInt(this.config.getProperty("mdb_port"))
			);
			
			this.console.print(Console.bold(credentials.toString()) + ' ');
			
			this.sql = new MariaDB(credentials);
			success = true;
			
			this.console.println(Console.OK_TAG);
		} catch (Exception e) {
			this.console.println(Console.FAILED_TAG);
			this.console.println(Console.EXCEPTION_TAG + ' ' + e.getMessage());
			throw e;
		}
		
		return(success);
	}
	
	public boolean loadConfig() throws Exception {
		boolean success = false;
		
		this.console.print(Console.INFO_TAG + " Loading config file: " + Console.bold(this.configFile) + ' ');
		
		try {
			this.setConfig(new Configuration(this.configFile, Arrays.asList(
				"port",
				"mdb_host",
				"mdb_port",
				"mdb_uname",
				"mdb_pword",
				"mdb_db",
				"username",
				"password"
			)));
			
			success = true;
			
			this.console.println(Console.OK_TAG);
		} catch (Exception e) {
			this.console.println(Console.FAILED_TAG);
			this.console.println(Console.EXCEPTION_TAG + ' ' + e.getMessage());
			throw e;
		}
		
		return(success);
	}
	
	public boolean startServer() throws Exception {
		boolean success = false;
		
		try {
			this.console.print(Console.INFO_TAG + " Handling communication on port: ");
			this.console.print(Console.bold(this.config.getProperty("port")) + ' ');
			
			// Starting Server in a new Thread
			new Thread(new Server(Integer.parseInt(config.getProperty("port")))).start();
			success = true;
			
			this.console.println(Console.OK_TAG);
		} catch (Exception e) {
			this.console.println(Console.FAILED_TAG);
			this.console.println(Console.EXCEPTION_TAG + ' ' + e.getMessage());
			throw e;
		}
		
		return(success);
	}
	
	public void printResourceFile(String file) {
		Iterator<String> iterator = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(file))).lines().iterator();
		
		while (iterator.hasNext()) this.console.println(iterator.next());
	}
	
	public void setConfig(Configuration config) { this.config = config; }
	public void setConfigFile(String configFile) { this.configFile = configFile; }
	public void run(boolean run) { this.run = run; }
	public void cli(boolean cli) { this.cli = cli; }
	public void setConsole(Console console) { this.console = console; };
}
