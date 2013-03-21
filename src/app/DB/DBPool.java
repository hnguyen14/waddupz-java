package app.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationRuntimeException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class DBPool {
	private GenericObjectPool connectionPool; 
	private DataSource dataSource;
	private ConnectionFactory connectionFactory;
	private PoolableConnectionFactory poolableConnectionFactory;
	
	
	public DBPool (String driver, String username, String password, String dbName, String hostName) {
		
		try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		connectionPool = new GenericObjectPool(null);
		connectionPool.setMaxIdle(10);
		connectionPool.setMinIdle(2);
		connectionPool.setMaxActive(40);
	
		String connectionURI = "jdbc:mysql://"+hostName+"/"+dbName;
		
		Properties prop = new Properties();
		prop.setProperty("user", username);
		prop.setProperty("password", password);
		setupDataSource(connectionURI, prop);
	}
	
	
	private void setupDataSource(String connectionURI,Properties prop){
		connectionFactory = new DriverManagerConnectionFactory(connectionURI,prop);
		poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory,connectionPool,null,null,false,true);
		dataSource = new PoolingDataSource(connectionPool);
	}
	
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	
	public static void main (String args[]) {
		
		try {
			Connection con = Pools.getConnection("testDB");
			PreparedStatement stmt = con.prepareStatement("select count(*) cnt from nextag_keywords");
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt("cnt"));
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
