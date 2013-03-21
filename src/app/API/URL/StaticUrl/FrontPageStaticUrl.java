package app.API.URL.StaticUrl;

import java.util.Iterator;
import java.util.Map;

import app.API.URL.StaticUrlFactory;
import app.Utils.UrlUtils;

public class FrontPageStaticUrl extends AbstractStaticUrl {

	public static final String FRONT_PAGE_POSTFIX = "front.html";
	public static final String FRONT_PAGE_JSP_PATH = "/main/Pages/front_page.jsp";
	
	
	/** 
	 *  TO ADD IN NEW TABS:
	 *     define the text associated with the new tab here.
	 *     Write code to handle the entry for the tab (extends AbstractEntry.java . 
	 *     Hibernate binding file. Hibernate config file
	 *     
	 *     ConntentBuilderBean.java:    - set category for the search
	 *     								- add in the display for each entry in each tab
	 *     Body.jsp - add in the tab in JSP
	 */
	public static final String PAGE_TYPE_NEWS = "news";
	public static final String PAGE_TYPE_SHOP_DEALS = "shopping-deals";
	public static final String PAGE_TYPE_COUPON = "coupon";
	
	/*
	 * format : 
	 * http://localhost:8080/server_path/vi/0/news/front.html
	 * 
	 */
	
	public FrontPageStaticUrl(String url) {
		super(url);
	}
	
	public FrontPageStaticUrl(Map params) {
		super(params);
	}
	
	protected void parseUrl() {
		String[] tokens = this.url.split("/");
		paramMap.put("lang", tokens[0]);
		paramMap.put("pageNum", tokens[1]);
		paramMap.put("outputMode", tokens[2]);
	}
	
	@Override
	public String decode() {		
		if (paramMap.size() == 3) {	
			String ret_val = FRONT_PAGE_JSP_PATH + "?";
			
			for (Iterator iter = paramMap.keySet().iterator(); iter.hasNext(); ) {
				String key = (String) iter.next();
				ret_val += key + "=" + paramMap.get(key) + "&";
			}
			return ret_val.substring(0, ret_val.length() - 1);
		} else
			return null;
		
	}

	@Override
	public String encode() {
		if (paramMap.size() == 3) {
			String ret_val = "";
			
			String lang = (String) paramMap.get("lang");
			String pageNum = (String) paramMap.get("pageNum");
			String type = (String) paramMap.get("outputMode");
			
			ret_val = StaticUrlFactory.root_dir + "/" + lang + "/" + pageNum + "/" + type + "/" + FRONT_PAGE_POSTFIX;
			
			return ret_val;
		} else 
			return null;
	}
	
	public int getType() {
		return StaticUrlFactory.FRONT_PAGE;
	}

	public static void main(String args[]) {
		FrontPageStaticUrl u = new FrontPageStaticUrl("/waddupz/vi/0/news.html");
		System.out.println(u.decode());
	}
	
}
