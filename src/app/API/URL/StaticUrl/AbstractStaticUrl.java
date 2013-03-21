package app.API.URL.StaticUrl;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStaticUrl {
	
	protected Map paramMap = new HashMap();
	protected String url;
	
	public AbstractStaticUrl(String url) {
		this.url = url.substring(1,url.length());
		parseUrl();
	}
	
	public AbstractStaticUrl(Map params) {
		this.paramMap = params;
	}
	
	public void setParameter(String name, String value) {
		paramMap.put(name, value);
	}

	public abstract String decode();
	public abstract String encode();
	public abstract int getType();
	protected abstract void parseUrl();
}
