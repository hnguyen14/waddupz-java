package app.API.Cache;

import java.util.List;

import app.API.Finder;
import app.API.Object.CacheableObject;
import app.API.Search.Searcher;
import app.Entry.AbstractEntry;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCache implements ICache{

	private static ICache instance = null;

	private EHCache() {}

	public static ICache getInstance() {
		if (instance == null)
			instance = new EHCache();
		return instance;
	}

	public Object getCachedObject(String cacheName, Object key) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		
		Cache cache = cacheManager.getCache(cacheName);
		if (cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
		}
		Element element = cache.get(key);
		
		if (element != null)
			return element.getObjectValue();
		else 
			return null;
	}

	public Object removeCachedObject(String cacheName, Object key) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
		return null;
	}

	public void shutdown() throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		cacheManager.shutdown();
	}

	public void startup() throws Exception {
	}

	public void upsertCachedObject(String cacheName, Object key, Object object) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		Cache cache = cacheManager.getCache(cacheName);
		cache.remove(key);
		cache.put(new Element(key, object));
	}
	
//	public static void main(String args[]) {
//		try {
//			System.out.println("Iterate .... 1");
//			AbstractEntry e = Finder.getPostEntry("app.Entry.AbstractEntry", "12");
//			System.out.println(e.getTitle());
//			
//			System.out.println("Iterate .... 2");
//			e = Finder.getPostEntry("app.Entry.AbstractEntry", "12");
//			System.out.println(e.getTitle());
			
//			ICache cache = EHCache.getInstance();
//			System.out.println("Iteration ..... 1");
//			AbstractEntry entry = (AbstractEntry) cache.getCachedObject(ICache.ENTRY_CACHE, "app.Entry.AbstractEntry-" + 12);
//			if (entry == null) {
//				Searcher s = new Searcher(Searcher.ENTRY_SEARCH);
//				s.setParam(Searcher.PARAM_ID, "12");
//				s.setParam(Searcher.PARAM_NUMRESULT, "1");
//				List l = s.search();
//				if  (l.size() > 0) {
//					entry = (AbstractEntry) l.get(0);
//					entry.updateCache(CacheableObject.CACHE_INSERT);
//				}
//				System.out.print("CACHE MISS ..... ");
//			} else {
//				System.out.print("CACHE HIT ..... ");
//			}
//			System.out.println(entry.getTitle());
//			
//			System.out.println("Iteration ..... 1");
//			entry = (AbstractEntry) cache.getCachedObject(ICache.ENTRY_CACHE, "app.Entry.AbstractEntry-" + 12);
//			if (entry != null) {
//				System.out.print ("CACHE HIT ..... ");
//				System.out.println(entry.getTitle());
//			} else {
//				System.out.println ("CACHE MISS ..... ");
//			}
//			
//			System.out.println("Iteration ..... 2");
//			entry = (AbstractEntry) cache.getCachedObject(ICache.ENTRY_CACHE, "app.Entry.AbstractEntry-" + 12);
//			if (entry != null) {
//				System.out.print ("CACHE HIT ..... ");
//				System.out.println(entry.getTitle());
//			} else {
//				System.out.println ("CACHE MISS ..... ");
//			}
//			
//			System.out.println("Iteration ..... 3");
//			entry = (AbstractEntry) cache.getCachedObject(ICache.ENTRY_CACHE, "app.Entry.AbstractEntry-" + 12);
//			if (entry != null) {
//				System.out.print ("CACHE HIT ..... ");
//				System.out.println(entry.getTitle());
//			} else {
//				System.out.println ("CACHE MISS ..... ");
//			}
//			
//			cache.shutdown();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
