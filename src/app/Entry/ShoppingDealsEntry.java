package app.Entry;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.security.action.PutAllAction;

import app.API.Cache.ICache;
import app.API.URL.StaticUrlFactory;
import app.API.URL.StaticUrl.EntryPageStaticUrl;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.API.URL.StaticUrl.SearchPageStaticUrl;
import app.Utils.ConfigUtils;
import app.Utils.HibernateUtil;

public class ShoppingDealsEntry extends AbstractEntry {

	private double price;
	private double listedPrice;
	private double rebate;
	private String monetaryUnit;
	private String imageLink;
	
	public ShoppingDealsEntry(){
		super();
	}

	public ShoppingDealsEntry(EntryDataSource data) {
		super(data);
		this.price = ((Double) data.getData("price", new Double(0))).doubleValue();
		this.listedPrice = ((Double) data.getData("listedPrice", new Double(0))).doubleValue();
		this.rebate = ((Double) data.getData("rebate", new Double(0))).doubleValue();
		this.monetaryUnit = (String) data.getData("monetaryUnit", "USD");
		this.imageLink = (String) data.getData("imageLink", null);
	}
	
	public ShoppingDealsEntry(Document doc) {
		super(doc);
		this.price = Double.parseDouble(doc.get("price"));
		this.listedPrice = Double.parseDouble(doc.get("listedPrice"));
		this.rebate = Double.parseDouble(doc.get("rebate"));
		this.monetaryUnit = doc.get("monetaryUnit");
		this.imageLink = doc.get("imageLink");
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMonetaryUnit() {
		return monetaryUnit;
	}
	public void setMonetaryUnit(String monetaryUnit) {
		this.monetaryUnit = monetaryUnit;
	}
	public double getListedPrice() {
		return listedPrice;
	}
	public void setListedPrice(double listedPrice) {
		this.listedPrice = listedPrice;
	}
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate = rebate;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	private String getSavedPercentage() {
		if ( getPrice() < getListedPrice() && getListedPrice() > 0) {
			double discount = ((getListedPrice() - getPrice()) / getListedPrice()) * 100;
			NumberFormat formatter = new DecimalFormat("#");
			String ret_val = formatter.format(discount);
			return ret_val;
		} else {
			return null;
		}
	}

	public String getDisplayHTML(ResourceBundle rb) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("lang",getLanguage());
		param.put("id", String.valueOf(getId()));
		param.put("outputMode", FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS);
		
		EntryPageStaticUrl url = new EntryPageStaticUrl(param);
		
		String image = getImageLink();
		if (image == null || image.length() <= 0) 
			image = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.noImg");
		
		String ret_val = "";
		ret_val += "<tr onmouseover=\"style.backgroundColor='#FFF0B5';\" onmouseout=\"style.backgroundColor='#FFFFFF'\">";
		
		ret_val += "<td width=\"120px\" align=\"center\" valign=\"top\" ><a rel=\"nofollow\" href=\"" + getUrl() + "\">" +
				   "<img src=\"" + image + "\" border=0 width=\"100px\" height=\"100px\"/></a><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					getPoints() + " Votes\n";
		ret_val += "</font>\n";
		ret_val += "</td>\n";
		
		ret_val += "<td align=\"left\" id=\"entry_title\" valign=\"top\">\n";
		ret_val += "<a rel=\"nofollow\" target=\"_blank\" href=\"" + getUrl() + "\"><b>" + getTitle().trim().replaceAll("\n", "") + "</b></a><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 11px; color: #888888;\">\n";
		ret_val += rb.getString("submittedBy") + " " + getAuthor() + " On </font>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					getCreatedDate().toString() + "\n";
		ret_val += "</font><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
				   rb.getString("original") + " : " + getDomain() + "\n";
		ret_val += "</font><br/>\n";
//		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #0000FF;\">\n" +
//		" - " + getComments().size() + " Comments\n";
//		ret_val += "</font><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 14px; color: #000000;\"><b>\n" +
					rb.getString("price") + " : \n";
		ret_val += "</b></font>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 14px; color: #FF0000;\"><b>\n" +
					"$ " + getPrice() + " (" + getMonetaryUnit() + ")\n";
		ret_val += "</b></font>\n";
		
		String saved_str = getSavedPercentage();
		if (saved_str != null) {
			ret_val += "&nbsp;-&nbsp;\n";
			ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
			rb.getString("youSave") + " " + getSavedPercentage() + "%\n";
			ret_val += "</font><br/>\n";
		} else {
			ret_val += "<br/>\n";
		}
		
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
		
		String image = getImageLink();
		if (image == null || image.length() <= 0) 
			image = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.noImg");
		
		String thumbsUp = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.thumbsUp");
		String thumbsDown = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.img.thumbsDown");
		String voteJsp = StaticUrlFactory.root_dir + ConfigUtils.getAppProperty("server.jsp.voteJsp");
		
		String ret_val = "";
		ret_val += "<table><tr>\n";
		ret_val += "<td width=\"120px\" align=\"center\" valign=\"top\" ><a rel=\"nofollow\" href=\"" + getUrl() + "\">" +
					"<img src=\"" + image + "\" border=0 width=\"100px\" height=\"100px\"/></a><br/>\n";
		ret_val += "<font style=\"font-family: tahoma,verdana,sans-serif; font-size: 12px; color: #000000;\">\n" +
					getPoints() + " Votes\n";
		ret_val += "</font>\n";
		ret_val += "</a></td>\n"; 
		ret_val += "<td valign=\"top\" align=\"left\">";
		ret_val += "<div id=\"entry_title\">\n";
		ret_val += "<h3>\n";
		ret_val += "<a rel=\"nofollow\" href=\"" + getUrl() + "\">" + getTitle() + " - " + getDomain() + "</a>\n";
		ret_val += "</h3>\n";
		ret_val += "</div>\n";
		ret_val += getSummary() + "<br/>";
		ret_val += "<span><b>Price :</b></span><b><font color=\"red\">$" + getPrice() + " (" + getMonetaryUnit() + ")\n" +
					"</font></b><br/>";
		ret_val += "&#32;&#32;<a rel=\"nofollow\" href=\"" + getUrl() + "\"> <b> Go to Deal Page  >> </b></a>\n";
		ret_val += "<div align=\"right\" style=\"height:45px\">\n";
		ret_val += "<img src=\"" + thumbsUp + "\" border=0 width=\"22px\" height=\"22px\"/>";
		ret_val += "<a rel=\"nofollow\" style=\"text-decoration:none;color:#0000FF\" href=\"" + voteJsp + "?id=" + getId() + "&action=up\">&#32;Good Post&#32;&#32;&#32;</a>";
		ret_val += "<img src=\"" + thumbsDown + "\" border=0 width=\"22px\" height=\"22px\"/>";
		ret_val += "<a rel=\"nofollow\" style=\"text-decoration:none;color:#0000FF\" href=\"" + voteJsp + "?id=" + getId() + "&action=down\">&#32;Bad Post&#32;</a>";
		ret_val += "</div>";
		
		ret_val += "</td></tr>\n";
		ret_val += "</table>\n<br/>\n";
		return ret_val;
	}

	public void updateEntry(AbstractEntry entry) {
		if (entry instanceof ShoppingDealsEntry) {
			ShoppingDealsEntry newEntry = (ShoppingDealsEntry) entry;
			String newSum = newEntry.getSummary();
			double newPrice = newEntry.getPrice();
			String newImage = newEntry.getImageLink();

			if (newPrice < getPrice()) {
				setPrice(newPrice);
				setCreatedDate(newEntry.getCreatedDate());
			}
			
			if (newSum.length() > getSummary().length()) {
				setSummary(newSum);
				setCreatedDate(newEntry.getCreatedDate());
			}	
			
			if (newImage != null && getImageLink() == null) {
				setImageLink(newImage);
				setCreatedDate(newEntry.getCreatedDate());
			}
		}		
	}	
	
	protected String getCacheName() {
		return ICache.SHOPPING_DEAL_ENTRY_CACHE;
	}
	
	public static void main(String args[]) {

	}
	
}
