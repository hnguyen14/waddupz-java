package app.API.Cache;

import com.whirlycott.cache.CacheException;

public interface ICache {
	
	public static final String ENTRY_CACHE = "app.Entry.AbstractEntry";
	
	/**
	 * MORE CACHEABLE OBJECT CAN BE DEFINED HERE. Cache name needs to be the name of the class canonical name
	 */	
	public static final String NEWS_ENTRY_CACHE = "app.Entry.NewsEntry";
	public static final String SHOPPING_DEAL_ENTRY_CACHE = "app.Entry.ShoppingDealsEntry";
	public static final String COUPONS_ENTRY_CACHE = "app.Entry.CouponEntry";
	
	public static final String EH_CACHE = "ehcache";
	public static final String WHIRLY_CACHE = "whirlycache";
	
	public Object removeCachedObject(String cacheName, Object key) throws Exception;
	public void upsertCachedObject(String cacheName, Object key, Object object) throws Exception;
	public Object getCachedObject(String cacheName, Object key)throws Exception;
	public void startup() throws Exception;
	public void shutdown() throws Exception;
}
