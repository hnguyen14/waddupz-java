package app.Tasks;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import app.API.EntryHandler.EntryInputEngine;
import app.API.EntryHandler.Input.IEntryInput;
import app.API.EntryHandler.Input.XMLEntryInput;
import app.API.EntryHandler.Input.Crawler.CJDellCouponCrawler;
import app.API.EntryHandler.Input.Crawler.DiggCrawler;
import app.API.Search.Keyword.Phrase;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.Entry.AbstractEntry;
import app.Entry.NewsEntry;
import app.Entry.ShoppingDealsEntry;
import app.Tasks.QuartzUtil.AbstractTask;
import app.Utils.DBUtils;
import app.Utils.HibernateUtil;
import app.Utils.LogUtils;
import app.Utils.TextUtils;

public class CrawlTask {
	
	public static class CrawlBuy extends AbstractTask {

		protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
			try{ 
//				Logger log  = LogUtils.getTaskLogger("Buy_Crawler");
//				log.info("Start Crawling Buy.com @ " + new Date(System.currentTimeMillis()));
				
				Map ruleMap = new HashMap();
				
				ruleMap.put("title", "title");
				ruleMap.put("url", "link");
				ruleMap.put("price", "p:price");
				ruleMap.put("listed_price", "p:listprice");
				ruleMap.put("rebate", "p:rebate");
				ruleMap.put("desc", "p:description");
				ruleMap.put("image", "p:imagelink");
				
				IEntryInput method = new XMLEntryInput
					("http://www.buy.com/rss/feed.asp?loc=14982&grp=2&pid=2902194",
					 ruleMap,"<item*>(.*?)</item>",AbstractEntry.SHOPPING_DEAL_ENTRY,"en","");
				
				EntryInputEngine engine = new EntryInputEngine(method);
				engine.upsertToDB();
//				log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static class CrawlDigg extends AbstractTask {

		protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
			try{ 
//				Logger log  = LogUtils.getTaskLogger("Digg_Crawler");
//				log.info("Start Crawling Digg.com @ " + new Date(System.currentTimeMillis()));
				
				IEntryInput method = new DiggCrawler("http://digg.com/rss/index.xml");
				
				EntryInputEngine engine = new EntryInputEngine (method);
				engine.upsertToDB();
				
//				log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static class CrawlCJDellCoupon extends AbstractTask {

		protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
			try{ 
//				Logger log  = LogUtils.getTaskLogger("Digg_Crawler");
//				log.info("Start Crawling Digg.com @ " + new Date(System.currentTimeMillis()));
				
				IEntryInput method = new CJDellCouponCrawler();
				
				EntryInputEngine engine = new EntryInputEngine (method);
				engine.upsertToDB();
				
//				log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static class CrawlVnExpress extends AbstractTask {

		protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
			try{ 
//				Logger log  = LogUtils.getTaskLogger("VNExpress_Crawler");
//				log.info("Start Crawling Vnexpress.net @ " + new Date(System.currentTimeMillis()));
				Map ruleMap = new HashMap();
				ruleMap.put("title", "title");
				ruleMap.put("url", "link");
				ruleMap.put("desc", "description");
				
				IEntryInput method = new XMLEntryInput
					("http://vnexpress.net/rss/home.rss",
					 ruleMap,"<item*>(.*?)</item>",AbstractEntry.NEWS_ENTRY,"vi","");
				
				EntryInputEngine engine = new EntryInputEngine(method);
				engine.upsertToDB();
//				log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static class CrawlVnBBC extends AbstractTask {

		protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
			try{ 
//				Logger log  = LogUtils.getTaskLogger("VNBBC_Crawler");
//				log.info("Start Crawling Vnexpress.net @ " + new Date(System.currentTimeMillis()));
				Map ruleMap = new HashMap();
				ruleMap.put("title", "title");
				ruleMap.put("url", "link");
				ruleMap.put("desc", "description");
				
				IEntryInput method = new XMLEntryInput
					("http://www.bbc.co.uk/vietnamese/index.xml",
					 ruleMap,"<item.*?>(.*?)</item>",AbstractEntry.NEWS_ENTRY,"vi","");
				
				EntryInputEngine engine = new EntryInputEngine(method);
				engine.upsertToDB();
//				log.info("Done Crawling Task @ " + new Date(System.currentTimeMillis()));
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	public static class CrawlCJShoppingCoupon extends AbstractTask {

		protected void doTask(JobDataMap dataMap, Logger logger) throws JobExecutionException {
			try {
				Map ruleMap = new HashMap();
				ruleMap.put("title", "title");
				ruleMap.put("url", "link");
				
				IEntryInput method = new XMLEntryInput("http://cjbeta.com/offerportal/?feed=rss2&s=Dell&key=Advertiser&pid=2902194&submit.x=29&submit.y=2",
						ruleMap,"<item*>(.*?)</item>",AbstractEntry.COUPONS_ENTRY,"en",FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
				EntryInputEngine e = new EntryInputEngine(method);
				e.upsertToDB();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public static void main(String args[]) {
		
	}
}
