package app.API.URL.StaticUrl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import app.API.URL.StaticUrlFactory;

public class EntryPageStaticUrl extends AbstractStaticUrl{

	/*
	 * format : 
	 * http://localhost:8080/server_root/vi/news/entryID-entry.html
	 * 
	 */
	public static final String ENTRY_PAGE_POSTFIX = "entry.html";
	public static final String ENTRY_PAGE_JSP_PATH = "/main/Pages/singleEntry.jsp";
	
	public EntryPageStaticUrl(String url) {
		super(url);
	}
	
	public EntryPageStaticUrl(Map params) {
		super(params);
	}
	
	@Override
	public String decode() {
		if (paramMap.size() == 3) {	
			String ret_val = ENTRY_PAGE_JSP_PATH + "?";
			
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
		if (paramMap.size() >= 3) {
			String lang = (String) paramMap.get("lang");
			String id = (String) paramMap.get("id");
			String type = (String) paramMap.get("outputMode");
			return StaticUrlFactory.root_dir + "/" + lang + "/" + type + "/" + id + "-" + ENTRY_PAGE_POSTFIX;
		} else 
			return null;
	}

	@Override
	public int getType() {
		return StaticUrlFactory.SINGLE_POST;
	}

	@Override
	protected void parseUrl() {
		String toks[] = url.split("/");
		paramMap.put("lang",toks[0]);
		paramMap.put("outputMode",toks[1]);
		String entry_id = toks[2];
		String toksEntry[] = entry_id.split("-");
		paramMap.put("id", toksEntry[0]);
	}
	
	
	public static void main(String args[]) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("lang","vi");
		param.put("outputMode", FrontPageStaticUrl.PAGE_TYPE_NEWS);
		param.put("id", "123");
		EntryPageStaticUrl url = new EntryPageStaticUrl(param);
		System.out.println(url.encode());
	}

}
