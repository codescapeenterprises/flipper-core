package com.codescape.flipper;

// Jansi
import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi;
import static org.fusesource.jansi.Ansi.*;

import java.io.PrintStream;

public class Console {
	private PrintStream out;
	
	public static String ERROR_TAG = "[@|fg_red ERROR|@]";
	public static String EXCEPTION_TAG = "[@|fg_yellow EXCEPTION|@]";
	public static String FAILED_TAG = "[@|fg_red FAILED|@]";
	public static String INFO_TAG = "[@|fg_blue INFO|@]";
	public static String OK_TAG = "[@|fg_green OK|@]";
	public static String WARNING_TAG = "[@|fg_yellow WARNING|@]";
	
	public Console() {
		AnsiConsole.systemInstall();
		this.setPrintStream(System.out);
	}
	
	public Console(PrintStream out) {
		AnsiConsole.systemInstall();
		this.setPrintStream(out);
	}
	
	public static String bold(String msg) {
		return("@|bold " + msg + "|@");
	}
	
	// Print ansi-rendered message
	public String print(String msg) {
		Ansi ansi = ansi().render(msg);
		
		out.print(ansi);
		
		return(ansi.flushAttributes().toString());
	}
	public void println(String msg) { this.print(String.format(msg + "%n")); }
	
	public void setPrintStream(PrintStream out) { this.out = out; }
}
