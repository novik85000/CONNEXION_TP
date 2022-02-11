package fr.eni.connexionTP.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			
			
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
	
}
