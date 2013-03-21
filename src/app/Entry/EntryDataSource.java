package app.Entry;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryDataSource {
	
	private Map dataMap;
	
	public EntryDataSource(Map dataMap) {
		this.dataMap = dataMap;
	}
	
	public EntryDataSource
						(	String title, String url, 
							String domain, String author, long authorId, 
							Date createdDate, String language, String summary, 
							int points, double internalBoost, List comments, 
							List tags, boolean showOnFrontPage, boolean showOnSearchPage,
							double price, double listedPrice, double rebate, 
							String monetaryUnit, String imageLink,
							String couponCode, String category, Date startDate, Date endDate) {
		dataMap = new HashMap();
		addData("title", title);
		addData("url", url);
		addData("domain", domain);
		addData("author", author);
		addData("authorId", new Long(authorId));
		addData("createdDate", createdDate);
		addData("language", language);
		addData("summary", summary);
		addData("points", new Integer(points));
		addData("internalBoost", new Double(internalBoost));
		addData("comments", comments);
		addData("tags", tags);
		addData("showOnFrontPage", new Boolean(showOnFrontPage));
		addData("showOnSearchPage", new Boolean(showOnSearchPage));
		addData("price", new Double(price));
		addData("listedPrice", new Double(listedPrice));
		addData("rebate", new Double(rebate));
		addData("monetaryUnit", monetaryUnit);
		addData("imageLink", imageLink);
		addData("couponCode", couponCode);
		addData("category", category);
		addData("startDate", startDate);
		addData("endDate", endDate);
	}
	
	public void addData(String entryKey, Object value) {
		dataMap.put(entryKey, value);
	}
	
	public Object getData(String entryKey, Object defaultValue) {
		Object ret_val = defaultValue;	
		if (dataMap.containsKey(entryKey)) {
			ret_val = dataMap.get(entryKey);
		}
		return ret_val ;
	}
	
}
