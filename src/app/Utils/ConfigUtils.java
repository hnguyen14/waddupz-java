package app.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import app.Utils.Config.ServerConfig;

public class ConfigUtils {
	
	public static String getAppProperty(String propName) {
		return ServerConfig.getServerProperty(propName);
	}
	
	public static String getAppProperty(String propName, String defaultValue) {
		return ServerConfig.getServerProperty(propName, defaultValue);
	}
}