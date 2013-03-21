package app.Utils.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import app.DB.Pools;

public class ServerConfig {
	public static Map<String, Property> PROPERTIES = null;
	public static Configuration appConfig = null;
	
	public static class Property {
		private String value;
		private boolean inDB;
		public Property(String value, boolean inDB) {
			super();
			this.value = value;
			this.inDB = inDB;
		}
		public boolean isInDB() {
			return inDB;
		}
		public void setInDB(boolean inDB) {
			this.inDB = inDB;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}

	public static void initialize(){
		try {
			PROPERTIES = new HashMap<String, Property>();
			appConfig = new PropertiesConfiguration("app.properties");

			// Read from app properties file first
			for (Iterator iter = appConfig.getKeys(); iter.hasNext(); ) {
				String key = (String) iter.next();
				String value = (String) appConfig.getString(key);
				PROPERTIES.put(key, new Property(value, false));
			}

			// getting override properties from DB
			Connection conn = Pools.getConnection();
			PreparedStatement stmt = conn.prepareStatement("select * from server_property");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String key = rs.getString("NAME");
				String value = rs.getString("VALUE");

				if (PROPERTIES.containsKey(key)) {
					PROPERTIES.remove(key);
				}
				PROPERTIES.put(key, new Property(value, true));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void modifyProperty(String key, String value) {
		if (PROPERTIES == null || appConfig == null)
			initialize();
		if (PROPERTIES.containsKey(key)) {
			Property prop = PROPERTIES.remove(key);	
			if (prop.isInDB()) {
				updatePropertyToDB(key, value);
			} else {
				insertPropertyToDB(key, value);
			}
			
			PROPERTIES.put(key, new Property(value, true));
		}
	}
	
	public static void resetProperty(String key) {
		if (PROPERTIES == null || appConfig == null)
			initialize();
		if (PROPERTIES.containsKey(key)) {
			PROPERTIES.remove(key);
			deletePropertyToDB(key);
			PROPERTIES.put(key, new Property(appConfig.getString(key), false));
		}
	}
	
	public static String getServerProperty(String key) {
		return getServerProperty(key,null);
	}
	
	public static String getServerProperty(String key, String defaultValue) {
		if (PROPERTIES == null || appConfig == null)
			initialize();
		if (PROPERTIES.containsKey(key)) 
			return PROPERTIES.get(key).getValue();
		else
			return defaultValue;
	}
	
	private static void insertPropertyToDB(String key, String value) {
		try{
			Connection conn = Pools.getConnection();
			PreparedStatement stmt = conn.prepareStatement("insert into server_property(name,value) values(?,?)");
			stmt.setString(1, key);
			stmt.setString(2, value);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void updatePropertyToDB(String key, String value) {
		try{
			Connection conn = Pools.getConnection();
			PreparedStatement stmt = conn.prepareStatement("update server_property set value=? where name=?");
			stmt.setString(1, value);
			stmt.setString(2, key);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void deletePropertyToDB(String key) {
		try{
			Connection conn = Pools.getConnection();
			PreparedStatement stmt = conn.prepareStatement("delete from server_property where name=?");
			stmt.setString(1, key);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
		initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
