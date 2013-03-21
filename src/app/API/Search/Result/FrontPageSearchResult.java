package app.API.Search.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Hits;

import app.API.Search.Searcher;
import app.Entry.AbstractEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;
import app.Entry.NewsEntry;
import app.Entry.ShoppingDealsEntry;

public class FrontPageSearchResult {
	public static List getResult(Hits hits, Map<String,String> paramMap) throws IOException{
		int resultNum = 200;
		if (paramMap.containsKey(Searcher.PARAM_NUMRESULT))
			resultNum = Integer.parseInt(paramMap.get(Searcher.PARAM_NUMRESULT));
		List<AbstractEntry> ret_val = new ArrayList<AbstractEntry>();

		for (int i = 0; i < hits.length() && i < resultNum; i++) {
			Document doc = hits.doc(i);
			
			String category = doc.get("entryType");
			
			AbstractEntry element = EntryFactory.GetEntryInstance(category,doc);
			ret_val.add(element);
		}
		return ret_val;
	}
}
