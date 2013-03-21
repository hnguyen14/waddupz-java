package app.API.EntryHandler.Input.Crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import app.API.EntryHandler.Input.CrawlerEntryInput;
import app.Entry.AbstractEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;
import app.Utils.HttpUtils;

public class DiggCrawler extends CrawlerEntryInput{
	
	public static String standard_url = "http://digg.com/rss/index.xml";
	private Logger log = null; 

	public DiggCrawler (String url, Logger log) {
		super(url);
		this.log = log;
	}
	
	public DiggCrawler(String url) {
		this(url, null);
	}
	
	public DiggCrawler() {
		this (standard_url, null);
	}

	private static Pattern diggLinkPattern = Pattern.compile (
			"<h3 id=\"title\">.*?<a href=\"(.*?)\".*?>.*?</a>.*?</h3>");

	public String getLink(String url) {
		String ret_val = "";
		String html = HttpUtils.get(url).replaceAll("\n", "").replaceAll("\t"," ");
		Matcher m = diggLinkPattern.matcher(html);
		if (m.find()) {
			String text = m.group(1);
			ret_val = text;
		}

		return ret_val;
	}


	private static Pattern postPattern = Pattern.compile(
	"<item>.*?<title>(.*?)</title>.*?<link>(.*?)</link>.*?<description>(.*?)</description>.*?</item>");
	public List getInputList(){
		String html = fetch().replaceAll("\n", "").replaceAll("\t"," ");
		Matcher m=postPattern.matcher(html);
		List l = new ArrayList();
		while (m.find()) {			
			String title = m.group(1);
			String digg_url = m.group(2);
			String url = getLink(digg_url);
			String summary = m.group(3);
			String domain = url.replaceAll("http://", "").replaceAll("https://", "");
			int points = 0;
			int idx = domain.indexOf("/");
			if (idx > 0)
				domain = domain.substring(0,idx);
			String author = "sysCrawler";
			Date time = new Date(System.currentTimeMillis());

			EntryDataSource data = new EntryDataSource(title,url,domain,author,0,time,"en",summary,points,0.0,null,null,true,true,
					0.0,0.0,0.0,"USD",null,"N/A","N/A",null,null);
			l.add(EntryFactory.GetEntryInstance(AbstractEntry.NEWS_ENTRY,data));
			
			if (log != null) {
				log.info("\t" + title + " - " + url);
			}
		}
		return l;
	}
}
