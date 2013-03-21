package app.API.Object;

import java.io.Serializable;

import app.API.Cache.EHCache;
import app.API.Cache.ICache;
import app.API.Cache.WhirlyCache;
import app.Utils.ConfigUtils;

import com.whirlycott.cache.Cache;
import com.whirlycott.cache.CacheException;
import com.whirlycott.cache.CacheManager;

public abstract class CacheableObject implements Serializable{
	
	public static final int CACHE_UPDATE = 1;
	public static final int CACHE_REMOVE = 2;
	public static final int CACHE_INSERT = 3;
	
	private static ICache cache = null;
	
	// Get the ID of the cache Object
	protected abstract String getCachedObjectId();
	// get the class Name - Object Type
	protected abstract String getCacheName();
	
	private static void initCache() {
		String cacheType = ConfigUtils.getAppProperty("server.cache.type", ICache.WHIRLY_CACHE);
		if (cacheType.equalsIgnoreCase(ICache.WHIRLY_CACHE))
			cache = WhirlyCache.getInstance();
		else if (cacheType.equalsIgnoreCase(ICache.EH_CACHE))
			cache = EHCache.getInstance();
		else 
			cache = WhirlyCache.getInstance();
	}
	
	public void updateCache(int actionMode) throws Exception{
		if (cache == null)
			initCache();
		String cacheKey = getCachedObjectId();
		switch (actionMode) {
		case CACHE_INSERT:
			cache.upsertCachedObject(getCacheName(), cacheKey, this);
			break;
		case CACHE_UPDATE:
			cache.upsertCachedObject(getCacheName(), cacheKey, this);
			break;
		case CACHE_REMOVE:
			cache.removeCachedObject(getCacheName(), cacheKey);
			break;
		default:
			break;
		}
	}
	
	public static Object getCachedObject(String cacheName, String id) throws Exception{
		if (cache == null)
			initCache();
		return cache.getCachedObject(cacheName, id);
	}
	
	public static void main(String args[]){
		
	}
}
