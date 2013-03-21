package app.Servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import net.sf.ehcache.CacheManager;

/**
 * 
 * @author Hoang Nguyen
 * This Listener is implement to properly shutdown EHCache CacheManager
 * so it will flush data to Disk store and make it persistent.
 *
 */
public class EHCacheServletListener implements ServletContextListener{

	private Logger logger = Logger.getLogger("ServerLog");
	
	public void contextDestroyed(ServletContextEvent arg0) {
		logger.info("Flushing EHCache Data to Disk Store .... " );
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.shutdown();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("Initializing EHCache.....");
	}

	public static void main(String args[]) {
		
	}
	
}
