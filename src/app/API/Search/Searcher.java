package app.API.Search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

import app.API.Search.Query.SearchQueryFactory;
import app.API.Search.Result.SearchResultFactory;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.Entry.AbstractEntry;
import app.Entry.NewsEntry;
import app.Utils.ConfigUtils;

public class Searcher {

	/**													**	
	 *  MORE SEARCH TYPE WILL NEED TO BE DECLARED HERE	 *
	 *    the value of each search type needs to be      *
	 *    consistent with the ordering of the Index_list *
	 *													 */		
	public static final String PARAM_LANG = "lang";
	public static final String PARAM_KEYWORD = "keyword";
	public static final String PARAM_NUMRESULT = "numResult";
	public static final String PARAM_ID = "id";
	public static final String PARAM_COUPON_CAT = "couponCat";
	public static final String PARAM_SHOWONFRONTPAGE = "frontPage";
	public static final String PARAM_SHOWONSEARCHPAGE = "searchPage";
	
	public static final String Index_dir = ConfigUtils.getAppProperty("server.data.directory") + "index/";
	
	//******************** END OF SEARCH TYPE DECLARATION  *******************************

	private String objectType;
	private Map<String, String> paramMap;	

	public Searcher(String objectType) {
		this(objectType,new HashMap<String, String>());
	}
		
	public Searcher(String objectType, Map<String, String> param) {
		this.objectType = objectType;
		this.paramMap = param;
	}
	
	public void setParam(String paramName, String paramValue) {
		this.paramMap.put(paramName, paramValue);
	}
	
	public void printParam(){
		System.out.println(paramMap);
	}
	
	public List search() throws Exception{
		String indexDir = Index_dir + objectType;
		IndexSearcher searcher = new IndexSearcher(indexDir);
		Query lucene_query = SearchQueryFactory.getQuery(objectType, paramMap);

		Hits hits = searcher.search(lucene_query);
		return SearchResultFactory.getResult(hits, paramMap, objectType);
	}
	
	public static void main(String args[]) {
		try {
			Searcher s = new Searcher(AbstractEntry.NEWS_ENTRY);
			s.setParam(Searcher.PARAM_LANG, "en");
//			s.setParam(Searcher.PARAM_COUPON_CAT, FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
			s.setParam(Searcher.PARAM_NUMRESULT, "200");
			List l = s.search();
			for (int i = 0 ; i < l.size(); i++) {
				AbstractEntry user = (AbstractEntry) l.get(i);
				System.out.println (user.getId() + " ------------" + user.getTitle()	+ "\t" + user.getCreatedDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
