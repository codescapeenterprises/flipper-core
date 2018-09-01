package com.codescape.loki;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class Configuration extends Properties {
	private boolean loaded;
	private List<String> properties;
	
	public Configuration() {
		this.loaded = false;
		this.properties = Arrays.asList();
	}
	
	public Configuration(String file, List<String> properties) throws IOException {
		this.loaded = false;
		this.properties = properties;
		
		this.loadFromFile(file);
		this.validate();
	}
	
	public boolean validate() throws IOException {
		Iterator<String> iterator = this.properties.iterator();
		boolean valid = true;
		
		while (iterator.hasNext()) {
			String property = iterator.next();
			
			if (!this.stringPropertyNames().contains(property)) {
				valid = false;
				throw new IOException("'" + property + "' is missing in the config file");
			}
		}
		
		return valid;
	}
	
	public void loadFromFile(String file) throws IOException {
		this.load(new FileInputStream(file));
		this.loaded = true;
	}
	
	public boolean isLoaded() { return(this.loaded); }
}
