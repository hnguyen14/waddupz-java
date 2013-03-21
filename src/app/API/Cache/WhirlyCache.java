package app.API.Cache;

import app.API.Finder;
import app.API.Object.CacheableObject;
import app.Entry.AbstractEntry;

import com.whirlycott.cache.Cache;
import com.whirlycott.cache.CacheException;
import com.whirlycott.cache.CacheManager;

public class WhirlyCache implements ICache{
	
	private static ICache instance = null;
	
	private WhirlyCache() {}
	
	public static ICache getInstance() {
		if (instance == null)
			instance = new WhirlyCache();
		return instance;
	}
	
	public Object removeCachedObject(String cacheName, Object key)throws Exception {
		// Retrieve the Singelton CacheManager Object
		Cache cache = CacheManager.getInstance().getCache(cacheName);	
		Object object = cache.remove(key);
		return object;
	}

	public void upsertCachedObject(String cacheName, Object key, Object object) throws Exception{
		//Retrieve the Singelton CacheManager Object
		Cache cache = CacheManager.getInstance().getCache(cacheName);
		cache.remove(key);
		cache.store(key, object);
	}
	
	public Object getCachedObject(String cacheName, Object key)throws Exception {
		//Retrieve the Singelton CacheManager Object
		Cache cache = CacheManager.getInstance().getCache(cacheName);	
		Object object = cache.retrieve(key);
		return object;
	}

	public void startup() throws Exception {
		// TODO Auto-generated method stub		
	}

	public void shutdown() throws Exception{
		CacheManager.getInstance().shutdown();
	}

	public static void main(String args[]) {
//		ICache cache = getInstance();
		try {
//			cache.upsertCachedObject(ICache.ENTRY_CACHE, "1", "1");
//			Object value = CacheableObject.getCachedObject(ICache.ENTRY_CACHE, "1");
//			if (value == null) 
//				System.out.println("NULL");
//			else
//				System.out.println(value);
			AbstractEntry e = Finder.getPostEntry("app.Entry.AbstractEntry", "12");
			System.out.println(e.getTitle());
			e = Finder.getPostEntry("app.Entry.AbstractEntry", "12");
			System.out.println(e.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
