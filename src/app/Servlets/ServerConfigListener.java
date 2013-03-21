package app.Servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import app.Utils.Config.ServerConfig;

public class ServerConfigListener implements ServletContextListener{
	private Logger logger = Logger.getLogger("ServerLog");
	
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Initializing Server Configuration ....." );
		ServerConfig.initialize();
	}

}
