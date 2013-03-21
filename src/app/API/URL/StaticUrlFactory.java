package app.API.URL;

import app.API.URL.StaticUrl.AbstractStaticUrl;
import app.API.URL.StaticUrl.EntryPageStaticUrl;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.API.URL.StaticUrl.SearchPageStaticUrl;
import app.Utils.ConfigUtils;

public class StaticUrlFactory {
	public static final String root_dir = ConfigUtils.getAppProperty("server.path","");
	
	public static final int FRONT_PAGE = 0;
	public static final int SINGLE_POST = 1;
	public static final int SEARCH_PAGE = 2;
	
	public static AbstractStaticUrl getStaticUrlHandler(String requestUrl){
		AbstractStaticUrl ret_val = null;
		
		// Strip out root dir
		String url = requestUrl;		
		if (url.endsWith(FrontPageStaticUrl.FRONT_PAGE_POSTFIX)) {
			ret_val = new FrontPageStaticUrl(url);
		} else if (url.endsWith(EntryPageStaticUrl.ENTRY_PAGE_POSTFIX)) {
			ret_val = new EntryPageStaticUrl(url);
		} else if (url.endsWith(SearchPageStaticUrl.SEARCH_PAGE_POSTFIX)) {
			ret_val = new SearchPageStaticUrl(url);
		}
		
		return ret_val;
	}

	public static void main(String args[]) {
		
	}
}
