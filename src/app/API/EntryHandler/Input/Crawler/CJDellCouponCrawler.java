package app.API.EntryHandler.Input.Crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import app.API.EntryHandler.Input.CrawlerEntryInput;
import app.API.EntryHandler.Input.IEntryInput;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.Entry.AbstractEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;

public class CJDellCouponCrawler extends CrawlerEntryInput{

	private static String standard_url = "http://cjbeta.com/dell/wp-rss2.php?ec3_after=today&pid=2902194&submit=Go";
	private static final Pattern postPattern = Pattern.compile(
			"<item>.*?<title>(.*?)</title>.*?<link>(.*?)</link>.*?<description>(.*?)</description>.*?</item>");
	private static final Pattern itemSectionPattern = Pattern.compile(
			"<b><font.*?>(.*?)</font></b>(.*?)<br.*?>");
	
	private static final String OFFER_TYPE = "Offer Type:";
	private static final String OFFER = "Offer:";
	private static final String INFO = "Info and Restrictions:";
	private static final String COUPON_CODE = "Coupon Code:";
	private Logger log = null;

	public CJDellCouponCrawler(String url , Logger log) {
		super(url);
		this.log = log;
	}
	
	public CJDellCouponCrawler(String url) {
		this (url, null);
	}
	
	public CJDellCouponCrawler() {
		this(standard_url, null);
	}
	

	public String getDescription(String desc) {
		return null;
	}
	
	public Map<String, String> getDataMap(String desc) {
		Map<String, String> ret_val = new HashMap<String, String>();
		Matcher m = itemSectionPattern.matcher(desc);
		while (m.find()) {
			String sectionName = m.group(1);
			String sectionValue = m.group(2);
			ret_val.put(sectionName, sectionValue);
		}
		return ret_val;
	}
	
	public EntryDataSource getEntryDataSource(String url, String title, String desc) {
		String domain = url.replaceAll("http://", "").replaceAll("https://", "");
		int points = 0;
		int idx = domain.indexOf("/");
		if (idx > 0)
			domain = domain.substring(0,idx);
		String author = "sysCrawler";
		Date time = new Date(System.currentTimeMillis());
		
		Map<String, String> dataMap = getDataMap(desc);
		
		String offer = "";
		String couponCode = "";
		String info = "";
		
		for  (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext(); ) {
			String section = iter.next();
			String sectionValue = dataMap.get(section);
			
			if (section.equalsIgnoreCase(OFFER)) {
				offer = sectionValue;
			} else if (section.equalsIgnoreCase(COUPON_CODE)) {
				couponCode = sectionValue;
			} else if (section.equalsIgnoreCase(INFO)) {
				info = sectionValue;
			}		
		}
		
		String dataSourceTitle = title;
		String summary = "";
		
		if (offer.length() > 0) {
			dataSourceTitle += " - " + offer;
			summary += offer;
		}
		
		if (info.length() > 0) {
			summary += ". " + info;
		}
		
		return new EntryDataSource(dataSourceTitle,url,domain,author,0,time,"en",summary,points,0.0,null,null,true,true,
				0.0,0.0,0.0,"USD",null,couponCode,FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS,null,null);
	}
	
	public List getInputList() throws Exception {
		String html = fetch().replaceAll("\n", "").replaceAll("\t"," ");
		Matcher m=postPattern.matcher(html);
		
		List l = new ArrayList();
		while (m.find()) {			
			String title = m.group(1);
			String url = m.group(2);
			String summary = m.group(3).replace("<![CDATA[","").replace("]]>", "").trim() + "<br>";
			
			EntryDataSource data = getEntryDataSource(url, title, summary);
			l.add(EntryFactory.GetEntryInstance(AbstractEntry.COUPONS_ENTRY,data));
			if (log != null) {
				log.info("\t" + title);
			}
		}
		return l;
	}
	
	public static void main (String args[]) {
		try {
			IEntryInput method = new CJDellCouponCrawler();
			List l = method.getInputList();
			for (int i = 0 ; i < l.size(); i++) {
				AbstractEntry ae = (AbstractEntry) l.get(i);
				System.out.println (ae.getTitle());
				System.out.println ("\t" + ae.getSummary());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
