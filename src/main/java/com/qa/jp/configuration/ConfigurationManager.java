package com.qa.jp.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() {
		prop = new Properties();

		// maven: cmd line argument:
		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		try {
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return prop;

	}

}
