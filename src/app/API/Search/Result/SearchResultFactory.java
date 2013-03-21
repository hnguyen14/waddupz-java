package app.API.Search.Result;

import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Hits;

import app.API.Search.Searcher;
import app.Entry.AbstractEntry;

public class SearchResultFactory {

	public static List getResult(Hits hits, Map<String,String> paramMap, String objectType) throws Exception{
		if (	objectType.equalsIgnoreCase(AbstractEntry.NEWS_ENTRY) ||
				objectType.equalsIgnoreCase(AbstractEntry.COUPONS_ENTRY) ||
				objectType.equalsIgnoreCase(AbstractEntry.SHOPPING_DEAL_ENTRY))
			return FrontPageSearchResult.getResult(hits, paramMap);
		else
			return null;
	}
}
