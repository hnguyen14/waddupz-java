package app.API.Search.Query;

import java.util.Map;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import app.API.Search.Searcher;

public class FrontPageSearchQuery {
	public static Query getQuery(Map<String, String>paramMap) throws Exception {
		BooleanQuery mainQuery = new BooleanQuery();

		String lang = paramMap.get(Searcher.PARAM_LANG);
		String keyword = paramMap.get(Searcher.PARAM_KEYWORD);
		String showFrontPage = paramMap.get(Searcher.PARAM_SHOWONFRONTPAGE);
		String showSearchPage = paramMap.get(Searcher.PARAM_SHOWONSEARCHPAGE);
		String couponCat = paramMap.get(Searcher.PARAM_COUPON_CAT);
		String id = paramMap.get(Searcher.PARAM_ID);
		
		if (id != null) {
			QueryParser p = new QueryParser("id", new StandardAnalyzer());
			Query idQuery = p.parse(id);
			mainQuery.add(idQuery, Occur.MUST);
		}

		if (couponCat != null) {
			QueryParser p = new QueryParser("category", new StandardAnalyzer());
			Query couponCatQuery = p.parse(couponCat);
			mainQuery.add(couponCatQuery, Occur.MUST);
		}
		
		if (lang != null) {
			QueryParser p = new QueryParser("language", new StandardAnalyzer());
			Query langQuery = p.parse(lang.toLowerCase());
			mainQuery.add(langQuery, Occur.MUST);
		}

		if (showFrontPage != null) {
			QueryParser p = new QueryParser("showOnFrontPage", new StandardAnalyzer());
			Query showQuery = p.parse(showFrontPage.toLowerCase());
			mainQuery.add(showQuery, Occur.MUST);
		}

		if (showSearchPage != null) {
			QueryParser p = new QueryParser("showOnSearchPage", new StandardAnalyzer());
			Query showQuery = p.parse(showSearchPage.toLowerCase());
			mainQuery.add(showQuery, Occur.MUST);
		}

		if (keyword != null) {
			BooleanQuery keywordSearch = new BooleanQuery();

			QueryParser p0 = new QueryParser("title", new StandardAnalyzer());
			Query title = p0.parse(keyword.toLowerCase());
			title.setBoost(100f);

			QueryParser p1 = new QueryParser("sum", new StandardAnalyzer());
			Query summary = p0.parse(keyword.toLowerCase());

			keywordSearch.add(title,Occur.SHOULD);
			keywordSearch.add(summary, Occur.SHOULD);

			mainQuery.add(keywordSearch, Occur.MUST);
		}

		return mainQuery;
	}
}
