package app.Entry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;

import app.API.Cache.ICache;
import app.API.URL.StaticUrlFactory;
import app.API.URL.StaticUrl.EntryPageStaticUrl;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.API.URL.StaticUrl.SearchPageStaticUrl;
import app.DB.Pools;
import app.Utils.ConfigUtils;

public class NewsEntry extends AbstractEntry {
	
	public NewsEntry(){
		super();
	}

	public NewsEntry(EntryDataSource data) {
		super(data);
	}
	
	public NewsEntry(Document doc) {
		super(doc);
	}
	
	public String getDisplayHTML(ResourceBundle rb) {
		
		Map<String, String> param = new HashMap<String, String>();
		param.put("lang",getLanguage());
		param.put("outputMode", FrontPageStaticUrl.PAGE_TYPE_NEWS);
		param.put("id", String.valueOf(getId()));
		
		EntryPageStaticUrl url = new EntryPageStaticUrl(param);
		
		String ret_val = "";
		ret_val += "<tr onmouseover=\"style.backgroundColor='#FFF0B5';\" onmouseout=\"style.backgroundColor='#FFFFFF'\"><td align=\"center\" valign=\"top\" width=\"100px\">\n";
		ret_val += "<font style=\"text-decoration: none; font-family: tahoma,verdana,sans-serif; font-size: 11px; color: #555555;\">" +
					getDomain() + "</font>\n";
		ret_val += "<br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					getPoints() + " Votes\n";
		ret_val += "</font>\n";
		ret_val += "</td>\n";
		ret_val += "<td id=\"entry_title\" align=\"left\" valign=\"top\">\n";
		ret_val += "<a rel=\"nofollow\" target=\"_blank\" href=\"" + getUrl() + "\"><b>" + getTitle().trim().replaceAll("\n", "") + "</b></a><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 11px; color: #888888;\">\n";
		ret_val += rb.getString("submittedBy") + " " + getAuthor() + " On </font>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					getCreatedDate().toString() + "\n";
		ret_val += "</font><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					rb.getString("original") + " : " + getDomain() + "\n";
		ret_val += "</font><br/>";
		ret_val += "<a href=\"" + url.encode() + "\" style=\"color: #336699; font-size:12px;\">\n" +
					getComments().size() + " " + rb.getString("comment");
		ret_val += "</a>&nbsp;-&nbsp;";
		ret_val += "<a href=\"" + url.encode() + "\" style=\"color: #336699; font-size:12px;\">\n" +
					"More ... ";
		ret_val += "</a><br/>\n";
		
		ret_val += "</td></tr>\n";
		
		return ret_val;
	}
	
	public String getEntryPageHTML(ResourceBundle rb) {
		
		String thumbsUp = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.thumbsUp");
		String thumbsDown = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.thumbsDown");
		String voteJsp = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.jsp.voteJsp");
		
		String ret_val = "";
		ret_val += "<div id=\"entry_title\">\n";
		ret_val += "<h3>\n";
		ret_val += "<a href=\"" + getUrl() + "\">" + getTitle() + "</a>\n";
		ret_val += "</h3>\n";
		ret_val += "</div>\n";
		ret_val += "<div>\n<table><tr>\n";
		ret_val += "<td align=\"center\" valign=\"top\">\n"; 
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #CCCCCC;\">\n" + 
						getDomain() + 
				   "</font><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					getPoints() + " Votes\n";
		ret_val += "</font>\n";
		ret_val += "</td>\n"; 
		ret_val += "<td valign=\"top\" align=\"left\">"+ getSummary();
		
		ret_val += "&#32;...&#32;&#32;<a href=\"" + getUrl() + "\"><b> Full Story  >> </b></a>\n";
		
		ret_val += "<br/><br/>";
		ret_val += "<div align=\"right\" style=\"height:45px\">\n";
		ret_val += "<img src=\"" + thumbsUp + "\" border=0 width=\"22px\" height=\"22px\"/>";
		ret_val += "<a rel=\"nofollow\" style=\"text-decoration:none;color:#0000FF\" href=\"" + voteJsp + "?id=" + getId() + "&action=up\">&#32;Good Post&#32;&#32;&#32;</a>";
		ret_val += "<img src=\"" + thumbsDown + "\" border=0 width=\"22px\" height=\"22px\"/>";
		ret_val += "<a rel=\"nofollow\" style=\"text-decoration:none;color:#0000FF\" href=\"" + voteJsp + "?id=" + getId() + "&action=down\">&#32;Bad Post&#32;</a>";
		ret_val += "</div>";
		ret_val += "</td>";
		ret_val += "</tr>\n";
		ret_val += "</table>\n</div><br/>\n";
		return ret_val;
	}

	public void updateEntry(AbstractEntry entry) {
		if (entry instanceof NewsEntry) {
			NewsEntry newsEntry = (NewsEntry) entry;
			String newSum = newsEntry.getSummary();
			if (newSum != null && newSum.length() > getSummary().length()) {
				setSummary(newSum);
				setCreatedDate(newsEntry.getCreatedDate());
			}
		}		
	}	
	
	protected String getCacheName() {
		return ICache.NEWS_ENTRY_CACHE;
	}
	
	public void addPoint() {
		this.points++;
	}
	
	public void decreasePoint() {
		this.points--;
	}
	
	public static void main (String args[]) {
		
	}
}
