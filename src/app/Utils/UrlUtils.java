package app.Utils;

import java.util.Map;

import app.API.URL.StaticUrlFactory;
import app.API.URL.StaticUrl.AbstractStaticUrl;
import app.API.URL.StaticUrl.FrontPageStaticUrl;

public class UrlUtils {
	
	public static final int FRONT_PAGE = 0;
	public static final int SINGLE_POST = 1;
	
	public static String getPageUrl(int pageType, String lang) {
		if (pageType == FRONT_PAGE) {
			if (lang.equalsIgnoreCase("en")) {
				return StaticUrlFactory.root_dir + "/main/Pages/front_page.jsp?lang=en";
			} else if (lang.equalsIgnoreCase("vi")) {
				return StaticUrlFactory.root_dir + "/main/Pages/front_page.jsp?lang=vi";
			}
			
		}
		return "";
	}
	
	public static String convertUrlToDifferentLanguage(String url, String lang) {
		
		int idx = url.indexOf("/",8);
		
		url = url.substring(idx, url.length());
		
		System.out.println(url);
		
		AbstractStaticUrl handler = StaticUrlFactory.getStaticUrlHandler(url);
		
		handler.setParameter("lang", lang);
		if (handler.getType() == StaticUrlFactory.FRONT_PAGE)
			handler.setParameter("pageNum", "0");
		
		return handler.encode();	
	}
	
	public static String getFrontPageLink(String lang, String type) {
		return StaticUrlFactory.root_dir + "/" + lang + "/0/" + type + "/" + FrontPageStaticUrl.FRONT_PAGE_POSTFIX;
	}
	
}
