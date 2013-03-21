package app.API.EntryHandler.Input;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

import app.API.EntryHandler.EntryInputEngine;
import app.Entry.AbstractEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;
import app.Utils.HttpUtils;
import app.Utils.LogUtils;

public class XMLEntryInput implements IEntryInput{
	private String url;
	private String language;
	private String feedEntryType;
	private Map ruleMap;
	private Pattern itemPattern;
	private String couponCat;
	private Logger log = null;
	
	public XMLEntryInput(String url, Map ruleMap, String itemPattern, String feedEntryType, String language, String couponCat, Logger log) {
		this.url = url;
		this.ruleMap = ruleMap;
		this.feedEntryType = feedEntryType;
		this.itemPattern = Pattern.compile(itemPattern);
		this.language = language;
		this.couponCat = couponCat;
		this.log = log;
	}
	
	public XMLEntryInput(String url, Map ruleMap, String itemPattern, String feedEntryType, String language, String couponCat) {
		this(url, ruleMap, itemPattern, feedEntryType, language, couponCat,null);
	}
		
	private String fetch() {
		String ret_val = HttpUtils.get(url);
		return ret_val.replaceAll("\n", " ").replaceAll("\t"," "); 
	}
	
	private String getItemChildText(String itemChildName, String itemText) {
		String ret_val = null;
		Pattern pattern = Pattern.compile("<" + itemChildName + ".*?>(.*?)</" + itemChildName + ">");
		Matcher m = pattern.matcher(itemText);
		
		if (m.find())
			ret_val = m.group(1);
		return ret_val;
	}
	
	private AbstractEntry getEntry(String itemText) throws Exception{
		AbstractEntry ret_val = null;
		
		// INITIALIZE VALUE MAP
		Map values = new HashMap();
		for (Iterator iter = ruleMap.keySet().iterator(); iter.hasNext(); ) {
			String param = (String) iter.next();
			values.put(param, getItemChildText((String) ruleMap.get(param), itemText));
		}
		
		// GETTING PART THAT EVERY ENTRY MUST HAVE
		String url = (String) values.get("url");
		String title = (String) values.get("title");
		String summary = (String) values.get("desc");
		
		String domain = url.replaceAll("http://", "").replaceAll("https://", "");
		int points = 0;
		int idx = domain.indexOf("/");
		if (idx > 0)
			domain = domain.substring(0,idx);
		String author = "sysCrawler";
		Date time = new Date(System.currentTimeMillis());
		// END OF GETTING ENTRY CORE DATA
		
		// THESE VALUE ARE NEEDED FOR SHOPPING DEAL ENTRY
		double price = 0.0;
		double listed_price = 0.0;
		double rebate = 0.0;
		String imageLink = "";
		String mu = "USD";		
		if (feedEntryType == AbstractEntry.SHOPPING_DEAL_ENTRY) {
			price = Double.parseDouble(((String) values.get("price")).replaceAll("$", "").replaceAll(",",""));
			listed_price = Double.parseDouble(((String) values.get("listed_price")).replaceAll("$", "").replaceAll(",",""));
			rebate = Double.parseDouble(((String) values.get("rebate")).replaceAll("$", "").replaceAll(",",""));
			imageLink = (String) values.get("image");
			mu = "USD";
		}
		// END OF GETTING SHOPPING DEAL ADDITIONAL DATA
		
		// THESE VALUE ARE NEEDED FOR COUPON ENTRY
		String couponCode = "N/A";
		String category = "N/A";
		Date startDate = null;
		Date endDate = null;
		if (feedEntryType == AbstractEntry.COUPONS_ENTRY) {
			couponCode = (String) values.get("couponCode");
			category = this.couponCat;
			String startDateStr = (String) values.get("startDate");
			String endDateStr = (String) values.get("endDate");
			startDate = new Date(startDateStr == null ? 0 : Long.parseLong(startDateStr));
			endDate = new Date(endDateStr == null ? 0 : Long.parseLong(endDateStr));
		}
		// END OF GETTING COUPON DATA
		
		// CREATE ENTRY DATA SOURCE AND FACTORIZE AND ENTRY
		EntryDataSource data = new EntryDataSource(title,url,domain,author,0,time,language,summary,points,0.0,null,null,true,true,
				price,listed_price,rebate,mu,imageLink, couponCode, category, startDate, endDate);
		ret_val = EntryFactory.GetEntryInstance(feedEntryType,data);
		return ret_val;
	}

	public List getInputList()  throws Exception{
		List ret_val = new ArrayList();
		String html = fetch();
		Matcher m = itemPattern.matcher(html);
		while (m.find()) {
			String itemText = m.group(1);
			AbstractEntry ae = getEntry(itemText);
			String title = ae.getTitle();
			ret_val.add(ae);
			if (log != null) {
				log.info("\t" + title);
			}
		}
		return ret_val;
	}
	
	public static void main(String args[]) {
		try{ 
//			Map ruleMap = new HashMap();
//			ruleMap.put("title", "title");
//			ruleMap.put("url", "link");
//			ruleMap.put("desc", "description");
//			
//			IEntryInput method = new XMLEntryInput
//				("http://www.bbc.co.uk/vietnamese/index.xml",
//				 ruleMap,"<item.*?>(.*?)</item>",AbstractEntry.NEWS_ENTRY,"vi","");
//			
//			EntryInputEngine engine = new EntryInputEngine(method);
//			engine.upsertToDB();
//			Map ruleMap = new HashMap();
//			ruleMap.put("title", "title");
//			ruleMap.put("url", "link");
//			ruleMap.put("desc", "description");
//			
//			IEntryInput method = new XMLEntryInput
//				("http://www.tuoitre.com.vn/tianyon/RssView.aspx?ChannelID=10",
//				 ruleMap,"<item*>(.*?)</item>",AbstractEntry.NEWS_ENTRY,"vi","");
//			
//			EntryInputEngine engine = new EntryInputEngine(method);
//			engine.upsertToDB();
			Map ruleMap = new HashMap();
			ruleMap.put("title", "title");
			ruleMap.put("url", "link");
			ruleMap.put("desc", "description");
			
			IEntryInput method = new XMLEntryInput
				("http://vnexpress.net/rss/home.rss",
				 ruleMap,"<item*>(.*?)</item>",AbstractEntry.NEWS_ENTRY,"vi","");
			
			EntryInputEngine engine = new EntryInputEngine(method);
			engine.upsertToDB();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
