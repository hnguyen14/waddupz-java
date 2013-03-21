package app.Entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;
import org.hibernate.Session;

import app.API.Cache.ICache;
import app.API.Object.CacheableObject;
import app.API.Search.Keyword.Phrase;
import app.Utils.DBUtils;
import app.Utils.HibernateUtil;

public abstract class AbstractEntry extends CacheableObject{
	
	public static final String NEWS_ENTRY = "app.Entry.NewsEntry";
	public static final String SHOPPING_DEAL_ENTRY = "app.Entry.ShoppingDealsEntry";
	public static final String COUPONS_ENTRY = "app.Entry.CouponEntry";
	
	private long id;
	private String title;
	private String url;
	private String domain;
	private String author;
	private long authorId;
	private Date createdDate;
	private String language;
	private String summary;
	protected int points;
	protected double internalBoost;
	protected List comments = new ArrayList();
	protected List tags;
	private boolean showOnFrontPage;
	private boolean showOnSearchPage;
	

	public AbstractEntry() {}
	
	public AbstractEntry(EntryDataSource data) {
		this.id = DBUtils.getNextLongFromSequence(AbstractEntry.class.getCanonicalName());
		this.title = (String) data.getData( "title", "");
		this.url = (String) data.getData( "url", "");
		this.domain = (String) data.getData( "domain", "");
		this.author = (String) data.getData( "author", "");
		this.authorId = ((Long) data.getData( "authorId", new Long(0))).longValue();
		this.createdDate = (Date) data.getData( "createdDate", new Long(System.currentTimeMillis()));
		this.language = (String) data.getData( "language", "en");
		this.summary = (String) data.getData( "summary", "");
		this.points = ((Integer) data.getData( "points", new Integer(0))).intValue();
		this.internalBoost = ((Double) data.getData( "internalBoost", new Integer(0))).doubleValue();
		this.comments = (List) data.getData( "comments", null);
		this.tags = (List) data.getData( "tags", null);
		this.showOnFrontPage = ((Boolean) data.getData( "showOnFrontPage", Boolean.TRUE)).booleanValue();
		this.showOnSearchPage = ((Boolean) data.getData( "showOnSearchPage", Boolean.TRUE)).booleanValue();
	}
	
	public AbstractEntry(Document doc) {
		this.id = Long.parseLong(doc.get("id"));
		this.title = doc.get("title");
		this.url = doc.get("url");
		this.domain  = doc.get("domain");
		this.author = doc.get("author");
		this.authorId = Long.parseLong(doc.get("authorId"));
		this.createdDate = new Date(Long.parseLong(doc.get("createdDate")));
		this.language = doc.get("language");
		this.summary = doc.get("summary");
		this.points  = Integer.parseInt(doc.get("points"));
		this.internalBoost = Double.parseDouble(doc.get("internalBoost"));
		
		this.comments = new ArrayList();
		String comments_str = doc.get("comments");
		if (comments_str != null && comments_str.length() > 0 ) {
			String comments_toks[] = comments_str.split("\\|");
			for (int i = 0 ; i < comments_toks.length; i++){
				String com = comments_toks[i];
				String toks[] = com.split("##");
				this.comments.add(new EntryComment(Long.parseLong(toks[0]),toks[3],toks[1],toks[2],new Date(Long.parseLong(toks[4]))));
			}
		}
		
		this.tags = new ArrayList();
		String tags_str = doc.get("tags");
		if (tags_str != null && tags_str.length() > 0) {
			String tags_toks[] = tags_str.split("\\|");
			for (int i = 0 ; i < tags_toks.length; i++){
				String com = tags_toks[i];
				String toks[] = com.split("##");
				this.tags.add(new Phrase(Long.parseLong(toks[0]),toks[3],toks[1],toks[2],Boolean.parseBoolean(toks[4])));
			}
		}
		
		this.showOnFrontPage = Boolean.parseBoolean(doc.get("showOnFrontPage"));
		this.showOnSearchPage = Boolean.parseBoolean(doc.get("showOnSearchPage"));
	}
	
	public abstract String getDisplayHTML(ResourceBundle rb);
	public abstract String getEntryPageHTML(ResourceBundle rb);
	public abstract void updateEntry(AbstractEntry entry);
	
	protected String getCachedObjectId() {
		return String.valueOf(getId());
	}

	public void CacheObjectOnUpdate() {
		try {
			updateCache(CacheableObject.CACHE_UPDATE); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public double getInternalBoost() {
		return internalBoost;
	}
	public void setInternalBoost(double internalBoost) {
		this.internalBoost = internalBoost;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List getComments() {
		return comments;
	}
	public void setComments(List comments) {
		this.comments = comments;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void addComment(EntryComment entryComment) {
		this.comments.add(entryComment);
	}
	public List getTags() {
		return tags;
	}
	public void setTags(List tags) {
		this.tags = tags;
	}
	public void addTags(Phrase phrase){
		if (this.tags == null)
			this.tags = new ArrayList();
		if (!this.tags.contains(phrase))
			this.tags.add(phrase);
	}

	public void addPoint(int point) {
		this.points = this.points + point;
	}

	public long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(long authorId) {
		this.authorId = authorId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public boolean isShowOnFrontPage() {
		return showOnFrontPage;
	}

	public void setShowOnFrontPage(boolean showOnFrontPage) {
		this.showOnFrontPage = showOnFrontPage;
	}

	public boolean isShowOnSearchPage() {
		return showOnSearchPage;
	}

	public void setShowOnSearchPage(boolean showOnSearchPage) {
		this.showOnSearchPage = showOnSearchPage;
	}

	public static void main (String args[]) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.close();
	}
}
