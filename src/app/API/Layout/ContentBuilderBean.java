package app.API.Layout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.API.Finder;
import app.API.Search.Searcher;
import app.API.Search.Keyword.Phrase;
import app.API.Search.Query.FrontPageSearchQuery;
import app.API.URL.StaticUrlFactory;
import app.API.URL.StaticUrl.AbstractStaticUrl;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.API.URL.StaticUrl.SearchPageStaticUrl;
import app.Entry.AbstractEntry;
import app.Entry.EntryComment;
import app.Entry.NewsEntry;
import app.Entry.ShoppingDealsEntry;
import app.Utils.ConfigUtils;
import app.Utils.HibernateUtil;

public class ContentBuilderBean implements Serializable{
	
	private static final long serialVersionUID = -8195141248646999364L;
	/**
	 * Language 	: vi, en
	 * Category 	: News, Shopping
	 * Search Term 	: empty (front page), term to search (search page), or entry title (single entry page)
	 * pageType		: front page, search page, entry page
	 * pageNum		: using to display listing.
	 */
	private long id;
	private String language;
	private String category;
	private String searchTerm;
	private int pageType;
	private int pageNum;
	private AbstractEntry entry;
	private List entryList;
	private Session session;
	private ResourceBundle rb;
	
	public void initialize(PageContext pageContext) {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		
		String id_str = request.getParameter("id");
		this.id = -1;
		if (id_str != null && id_str.length() > 0)
			this.id = Long.parseLong(id_str);
		
		this.language = request.getParameter("lang");
		this.category = request.getParameter("outputMode");
		
		if (language.equalsIgnoreCase("en"))
			rb = ResourceBundle.getBundle("i18n.english");
		else if (language.equalsIgnoreCase("vi"))
			rb = ResourceBundle.getBundle("i18n.vietnamese");
		
		String pageNumStr = request.getParameter("pageNum");
		if (pageNumStr != null)
			this.pageNum = Integer.parseInt(pageNumStr);
		else 
			this.pageNum = 0;
		
		this.searchTerm = request.getParameter("q");
		if (this.searchTerm == null) 
			this.searchTerm = "";
		try {
			dbInitialization(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
	}
	public void dbInitialization() throws Exception{
		session = HibernateUtil.getSessionFactory().openSession();
		if (pageType == StaticUrlFactory.FRONT_PAGE || pageType == StaticUrlFactory.SEARCH_PAGE) {
			this.entryList = getEntryListFromDB();
		} else if (pageType == StaticUrlFactory.SINGLE_POST) {
			this.entry = getEntryFromDB();
		}
		
		session.close();
	}
	
	public String getTitle() {
		if (pageType == StaticUrlFactory.FRONT_PAGE)
			return "<title>WADDUPZ - Telling you whats up on the net</title>";
		else if (pageType == StaticUrlFactory.SEARCH_PAGE)
			return "<title>WADDUPZ - " + searchTerm + "</title>";
		else if (pageType == StaticUrlFactory.SINGLE_POST)
			return "<title>WADDUPZ - " + entry.getTitle() + "</title>";
		else
			return "<title>WADDUPZ - Telling you whats up on the net </title>";
	}
	
	public String getPageCss() {
		return "<link rel=\"stylesheet\" href=\"" + StaticUrlFactory.root_dir + "/main/Layout/css/layout_style.css\" type=\"text/css\" media=\"screen\" />";
	}
	
	public String getMetaDescription() {
		if ((pageType == StaticUrlFactory.FRONT_PAGE || pageType == StaticUrlFactory.SEARCH_PAGE) && entryList != null) {
			String ret_val = entryList.size() + " - " + category.replaceAll("-"," ") + " posts - ";
			int idx = pageNum * 10;
			for (int i = idx; i < entryList.size() && i < idx + 10 && ret_val.length() < 240; i++) {
				ret_val += ((AbstractEntry) entryList.get(i)).getTitle() + ",";
			}
			ret_val = StringEscapeUtils.escapeHtml(ret_val);
			return "<meta name=\"description\" content=\"" + ret_val + " ... \">";
		} 
		else if (pageType == StaticUrlFactory.SINGLE_POST && entry != null) { 
			String sum = entry.getTitle() + " - " + entry.getSummary();
			String ret_val = (sum.length() > 240 ?sum.substring(0, 240) : sum);
			if (ret_val.trim().length() < 0)
				ret_val = "Web pages collected from the Internet";
			ret_val = StringEscapeUtils.escapeHtml(ret_val);
			return "<meta name=\"description\" content=\"" + ret_val + " ... \">";
		} else 
			return "<meta name=\"description\" content=\"Web pages collected from the Internet\">";
		
	}
	
	private List getEntryListFromDB() throws Exception {
		String objectType = AbstractEntry.NEWS_ENTRY;
		if (category.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_NEWS))
			objectType = AbstractEntry.NEWS_ENTRY;
		else if (category.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS))
			objectType = AbstractEntry.SHOPPING_DEAL_ENTRY;
		
		Searcher searcher = new Searcher(objectType);
		
		if (pageType == StaticUrlFactory.FRONT_PAGE)
			searcher.setParam(Searcher.PARAM_SHOWONFRONTPAGE, String.valueOf(true));
		
		if (language != null && language.length() > 0)
			searcher.setParam(Searcher.PARAM_LANG, language);
		if (searchTerm != null && searchTerm.length() > 0)
			searcher.setParam(Searcher.PARAM_KEYWORD, searchTerm);		
		
		return searcher.search();
	}
	
	private AbstractEntry getEntryFromDB() throws Exception{		
		String objectType = AbstractEntry.NEWS_ENTRY;
		if (category.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_NEWS))
			objectType = AbstractEntry.NEWS_ENTRY;
		else if (category.equalsIgnoreCase(FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS))
			objectType = AbstractEntry.SHOPPING_DEAL_ENTRY;
		
		return Finder.getPostEntry(objectType , String.valueOf(this.id));
	}	
	
	public String getSubTabContent() {
		String ret_val = "&nbsp;";
		List tags = null;
		
		String showRelatedKwProperty = "layout.showRelatedKeyword." + this.category + "." + this.language;
		String showRelatedKw = ConfigUtils.getAppProperty(showRelatedKwProperty, "true");
		
		if ("true".equalsIgnoreCase(showRelatedKw)) {
			ret_val = "";
			if (pageType == StaticUrlFactory.SINGLE_POST) {
				tags = entry.getTags();
			} else {
				if (entryList != null && entryList.size() > 0) {
					tags = new ArrayList();
					int start_idx = pageNum * 10;
					if (start_idx >= this.entryList.size())
						start_idx = this.entryList.size();
					int end_idx = start_idx + 10;
					if (end_idx > this.entryList.size())
						end_idx = this.entryList.size();

					for (int i = start_idx ; i < end_idx; i++) {
						AbstractEntry entry = (AbstractEntry) entryList.get(i);
						List entryTags = entry.getTags();
						for (int j  = 0 ; j < entryTags.size(); j++) {
							Phrase p = (Phrase) entryTags.get(j);
							if (!tags.contains(p)){
								tags.add(p);
							}
						}
					}
				}
			}
			if (tags != null && tags.size() > 0) {
				ret_val += "<div align=\"right\">\nRelated Topics : ";
				for (int i = 0 ; i < tags.size() && i < 8; i++) {
					Phrase p = (Phrase) tags.get(i);
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("lang", getLanguage());
					paramMap.put("outputMode", getCategory());
					paramMap.put("q", p.getPhrase().replaceAll(" ","_"));
					paramMap.put("pageNum", "0");
					SearchPageStaticUrl search_url = new SearchPageStaticUrl(paramMap);
					ret_val += "<a id=\"tag_title\" href=\"" + search_url.encode() + "\"><b>" + p.getPhrase() + "</b></a>";
					if (i < tags.size() - 1)
						ret_val += " | ";
					else 
						ret_val += "\n";
				}
				ret_val += "</div>\n";
			}
		}
		return ret_val ;
	}
	
	public String getResourceBundle() {
		if (language.equalsIgnoreCase("vi"))
			return "i18n.vietnamese";
		else 
			return "i18n.english";
	}
	
	public String getFrontPageContent() {
		String ret_val = "";
		
		if (this.entryList == null || this.entryList.size() <= 0) {
			ret_val = "No Result Found";
			return ret_val;
		}
		
		
		ret_val += "<table cellspacing=\"0\" cellpadding=\"5px\" width=\"100%\">\n";
		
		int totalNumberOfPage = this.entryList.size() / 10 ;
		
		if (this.entryList.size() % 10 > 0)
			totalNumberOfPage++;
		
		int start_idx = pageNum * 10;
		if (start_idx >= this.entryList.size())
			start_idx = this.entryList.size();
		int end_idx = start_idx + 10;
		if (end_idx > this.entryList.size())
			end_idx = this.entryList.size();
		
		for (int i = start_idx ; i < end_idx; i++) {
			AbstractEntry entry = (AbstractEntry) this.entryList.get(i);
			ret_val += entry.getDisplayHTML(rb);
			ret_val += "<tr><td colspan=\"2\"><br/></td></tr>\n";
		}

		if (totalNumberOfPage > 1) { 
			ret_val += "<tr><td colspan=\"2\"><br/></td></tr>\n";
			ret_val += "<tr><td colspan=\"2\" align=\"center\">\n";

			for (int i = 0 ; i < totalNumberOfPage; i++) {
				Map paramMap = new HashMap();

				paramMap.put("lang",language);
				paramMap.put("pageNum",String.valueOf(i));
				paramMap.put("outputMode",category);

				AbstractStaticUrl staticUrl = null;
				if (pageType == StaticUrlFactory.FRONT_PAGE)  
					staticUrl = new FrontPageStaticUrl(paramMap);
				else if (pageType == StaticUrlFactory.SEARCH_PAGE) {
					paramMap.put("q", searchTerm.replaceAll(" ","_"));
					staticUrl = new SearchPageStaticUrl(paramMap);
				}
				
				if (i == pageNum)
					ret_val += "<font color=\"#666666\">" + (i+1) + "</font>\n";
				else 
					ret_val += "<a style=\"color: #336699; text-decoration: none\" href=\"" + staticUrl.encode() + "\">" + (i+1) + "</a>\n";

				if ( i < totalNumberOfPage - 1) 
					ret_val += " | ";
			}
			ret_val += "</td></tr>\n";
		}
		ret_val += "</table>\n";
		
		return ret_val;
	}
	

	public String getEntryPageContent() {
		String ret_val = "";
		
		
		
		List entryComments = entry.getComments();
		
		ret_val += "<table width = \"100%\">\n";
		ret_val += "<tr>\n";
		ret_val += "<td colspan=\"2\">\n";
		ret_val += entry.getEntryPageHTML(rb);
		ret_val += "</td>\n";
		ret_val += "</tr>\n";
		ret_val += "<tr><td colspan=\"2\" valign=\"middle\" ><h3>&#32;" + rb.getString("leaveComment") + "</h3></td></tr>\n";
		ret_val += "<tr>\n";
		ret_val += "<td>\n";
		
		ret_val += "<form action=\"" + StaticUrlFactory.root_dir + "/main/Pages/singleEntry.jsp\" method=\"post\">\n";
		ret_val += "<input type=\"hidden\" name=\"cmd\" value=\"action\"/>\n";
		ret_val += "<input type=\"hidden\" name=\"id\" value=\"" + id + "\"/>\n";
		ret_val += "<input type=\"hidden\" name=\"lang\" value=\"" + language + "\"/>\n";
		ret_val += "<input type=\"hidden\" name=\"outputMode\" value=\"" + category + "\"/>\n";
		
		ret_val += "<tr><td width=\"20%\">\n";
		ret_val += rb.getString("name")+" :\n";
		ret_val += "</td><td>\n";
		ret_val += "<input type=\"text\" name=\"name\"/><br/>\n";
		ret_val += "</td></tr>\n";
		
		ret_val += "<tr><td width=\"20%\">\n";
		ret_val += rb.getString("email") + " :\n";
		ret_val += "</td><td>\n";
		ret_val += "<input type=\"text\" name=\"email\"/><br/>\n";
		ret_val += "</td></tr>\n";
		
		ret_val += "<tr><td colspan=\"2\">\n";
		ret_val += rb.getString("comment") + " :<br/>\n";
		ret_val += "<textarea name=\"comment\" COLS=40 ROWS=10></textarea><br/>\n";
		ret_val += "</td></tr>\n";
		
		ret_val += "<tr><td colspan=\"2\">\n";
		ret_val += "<input type=\"submit\" name=\"Submit\" value=\"Submit\"/>\n";
		ret_val += "</form>\n";
		
		ret_val += "</td>\n";
		ret_val += "</tr>\n";

		if ( entryComments == null || entryComments.size() <= 0) {
			ret_val += "<tr><td><div align=\"left\"><i>" + rb.getString("noComment") + "</i></div></td></tr>";
		} else {

			for (Iterator iter = entryComments.iterator(); iter.hasNext(); ) {
				EntryComment comment = (EntryComment) iter.next();
				ret_val += comment.getHTML();
				if (iter.hasNext())
					ret_val += "<tr><td><div width=\"98%\" style=\"height:2px; border-bottom:1px solid #A8D0F6\"/></td></tr>";
			}
		}
		
		ret_val += "</table>\n";

		return ret_val;
	}

	public String getPageContent() {
		String ret_val = "";
		
		if (pageType == StaticUrlFactory.FRONT_PAGE || pageType == StaticUrlFactory.SEARCH_PAGE)
			ret_val = getFrontPageContent();
		else if (pageType == StaticUrlFactory.SINGLE_POST)
			ret_val = getEntryPageContent();
		
		return ret_val;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageType() {
		return pageType;
	}
	public void setPageType(int pageType) {
		this.pageType = pageType;
	}
	public String getSearchTerm() {
		return searchTerm;
	}
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AbstractEntry getEntry() {
		return entry;
	}
	
	public String getEntryTitle() {
		if (entry != null)
			return entry.getTitle();
		return "";
	}
	public void setEntry(AbstractEntry entry) {
		this.entry = entry;
	}
	public List getEntryList() {
		return entryList;
	}


	public void setEntryList(List entryList) {
		this.entryList = entryList;
	}


	public static void main (String args[]) {

	}
	
}
