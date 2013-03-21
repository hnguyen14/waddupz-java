package app.Tasks;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.API.EntryHandler.EntryInputEngine;
import app.API.EntryHandler.Input.IEntryInput;
import app.API.EntryHandler.Input.XMLEntryInput;
import app.API.EntryHandler.Input.Crawler.CJDellCouponCrawler;
import app.API.EntryHandler.Input.Crawler.DiggCrawler;
import app.Entry.AbstractEntry;
import app.Tasks.QuartzUtil.AbstractTask;
import app.Utils.LogUtils;

public class DataCrawler extends AbstractTask{
	
	public static String CRAWL_DIGG = "digg";
	public static String CRAWL_DELL_COUPON = "dellCoupon";
	public static String CRAWL_VNEXPRESS = "vnexpress";
	
	
	public void crawlDigg(String url, Logger log) throws Exception{
		if (log != null) {
			log.info("Start Crawling Digg.com @ " + new Date(System.currentTimeMillis()) + "@" + url);
		}
		IEntryInput method = new DiggCrawler(url,log);

		EntryInputEngine engine = new EntryInputEngine (method,log);
		engine.upsertToDB();
		
		if (log != null) {
			log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
		}
	}
	
	public void crawlDellCoupon(String url, Logger log) throws Exception {
		if (log != null) {
			log.info("Start Crawling Dell Coupon @ " + new Date(System.currentTimeMillis()) + "@" + url);
		}

		IEntryInput method = new CJDellCouponCrawler(url, log);

		EntryInputEngine engine = new EntryInputEngine (method,log);
		engine.upsertToDB();

		if (log != null) {
			log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
		}		
	}
	
	public void crawlVNExpress(String url, String itemPattern, Logger log) throws JobExecutionException {
		try{
			if (log != null) {
				log.info("Start Crawling Vnexpress.net @ " + new Date(System.currentTimeMillis()) + "@" + url);
			}
			Map ruleMap = new HashMap();
			ruleMap.put("title", "title");
			ruleMap.put("url", "link");
			ruleMap.put("desc", "description");
			
//			IEntryInput method = new XMLEntryInput
//				("http://vnexpress.net/rss/home.rss",
//				 ruleMap,"<item*>(.*?)</item>",AbstractEntry.NEWS_ENTRY,"vi","", log);
			
			IEntryInput method = new XMLEntryInput(url, ruleMap,itemPattern,AbstractEntry.NEWS_ENTRY,"vi","", log);
			
			EntryInputEngine engine = new EntryInputEngine(method,log);
			engine.upsertToDB();
			if (log != null) {
				log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
		String url = dataMap.getString("url");
		String itemPattern = dataMap.getString("itemPattern");
		String crawlType = dataMap.getString("type");
		
		if (url == null) {
			throw new JobExecutionException("No URL provided");
		}
		
		if (crawlType != null) {			
			try {
				if (crawlType.equalsIgnoreCase(CRAWL_DIGG)) {
					crawlDigg(url, logger);
				} else if (crawlType.equalsIgnoreCase(CRAWL_DELL_COUPON)) {
					crawlDellCoupon(url, logger);
				} else if (crawlType.equalsIgnoreCase(CRAWL_VNEXPRESS)) {
					crawlVNExpress(url, itemPattern, logger);
				}
			} catch (Exception e) {
				if (logger != null)
					logger.error(e);
			}
		} else {
			throw new JobExecutionException("No Crawling Type Defined");
		}
		
	}

	
	
}
