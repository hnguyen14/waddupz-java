package app.API.URL.StaticUrl;

import java.util.Iterator;
import java.util.Map;

import app.API.URL.StaticUrlFactory;

public class SearchPageStaticUrl extends AbstractStaticUrl{
	public static final String SEARCH_PAGE_POSTFIX = "search.html";
	public static final String SEARCH_PAGE_JSP_PATH = "/main/Pages/search_page.jsp";
	
	/*
	 * format : 
	 * http://localhost:8080/server_path/en/0/shopping-deals/automotive_gps-search.html
	 * 
	 */
	
	public SearchPageStaticUrl(String url) {
		super(url);
	}
	
	public SearchPageStaticUrl(Map params) {
		super(params);
	}
	
	protected void parseUrl() {
		String[] tokens = this.url.split("/");
		paramMap.put("lang", tokens[0]);
		paramMap.put("pageNum", tokens[1]);
		paramMap.put("outputMode", tokens[2]);
		
		String[] toks = tokens[3].split("-");
		paramMap.put("q", toks[0].replaceAll("_"," "));
		
	}
	
	@Override
	public String decode() {		
		if (paramMap.size() == 4) {	
			String ret_val = SEARCH_PAGE_JSP_PATH + "?";
			
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
		if (paramMap.size() == 4) {
			String ret_val = "";
			
			String lang = (String) paramMap.get("lang");
			String searchTerm = (String) paramMap.get("q");
			String type = (String) paramMap.get("outputMode");
			String pageNum = (String) paramMap.get("pageNum");
			
			ret_val = StaticUrlFactory.root_dir + "/" + lang + "/" + pageNum + "/" + type + "/" + searchTerm.replaceAll(" ","_") + "-" + SEARCH_PAGE_POSTFIX;
			
			return ret_val;
		} else 
			return null;
	}
	
	public int getType() {
		return StaticUrlFactory.FRONT_PAGE;
	}

	public static void main(String args[]) {
		SearchPageStaticUrl u = new SearchPageStaticUrl("/waddupz/vi/0/news/hillary_clinton-search.html");
		System.out.println(u.decode());
	}

}
