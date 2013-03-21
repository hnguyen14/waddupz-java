package app.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Pools {

    
	private static Map pools = new HashMap();
	public static Configuration db_config = null;
	
	public static Connection getConnection() throws Exception {
		return getConnection(null);
	}
	
	public static Connection getConnection(String name) throws Exception{
		DBPool pool = null;
		if (name == null)
			pool = (DBPool) pools.get("default");
		else if (pools.containsKey(name))
			pool = (DBPool) pools.get(name);
		
		if (pool != null)
			return pool.getConnection();
		else {

			if (db_config == null)
				db_config = new PropertiesConfiguration("db.properties");
			
			String driver_prop = "db.driver";
			String user_prop = "db.userName";
			String pw_prop = "db.password";
			String dbName_prop = "db.dbName";
			String host_prop = "db.hostName";

			if (name != null) {
				driver_prop += "." + name;
				user_prop += "." + name;
				pw_prop += "." + name;
				dbName_prop += "." + name;
				host_prop += "." + name;
			}

			pool = new DBPool (	db_config.getString(driver_prop),db_config.getString(user_prop),
					db_config.getString(pw_prop),db_config.getString(dbName_prop),
					db_config.getString(host_prop));
			if (pool != null) {
				if (name != null)
					pools.put(name, pool);
				else 
					pools.put("default",pool);
				return pool.getConnection();
			} else
				return null;
		}
	}
	
	
}

