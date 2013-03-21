package app.API.Search.Query;

import java.util.Map;

import net.sf.hibernate.type.AbstractType;

import org.apache.lucene.search.Query;

import app.API.Search.Searcher;
import app.Entry.AbstractEntry;

public class SearchQueryFactory {
	
	public static Query getQuery(String objectType, Map<String, String>paramMap) throws Exception{
		if (	objectType.equalsIgnoreCase(AbstractEntry.NEWS_ENTRY) ||
				objectType.equalsIgnoreCase(AbstractEntry.COUPONS_ENTRY) ||
				objectType.equalsIgnoreCase(AbstractEntry.SHOPPING_DEAL_ENTRY))
			return FrontPageSearchQuery.getQuery(paramMap);
		else 
			return null;
	}
	

}
