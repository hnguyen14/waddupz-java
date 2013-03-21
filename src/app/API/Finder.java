package app.API;

import java.util.List;

import app.API.Cache.ICache;
import app.API.Object.CacheableObject;
import app.API.Search.Searcher;
import app.Entry.AbstractEntry;

public class Finder {
	
	public static AbstractEntry getPostEntry(String objectType , String id) {
		AbstractEntry entry = null;
		String cacheKey = id;
		try {
			entry = (AbstractEntry) CacheableObject.getCachedObject(objectType, cacheKey);
			if (entry == null) {
				Searcher s = new Searcher(objectType);
				s.setParam(Searcher.PARAM_ID, String.valueOf(id));
				s.setParam(Searcher.PARAM_NUMRESULT, "1");
				List l = s.search();
				if  (l.size() > 0) {
					entry = (AbstractEntry) l.get(0);
					entry.updateCache(CacheableObject.CACHE_INSERT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entry;
	}
	public static void main (String args[]) {
		
	}
}
