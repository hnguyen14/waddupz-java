package app.Entry;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.lucene.document.Document;

import app.API.Cache.ICache;


public class CouponEntry extends AbstractEntry{

	private String couponCode;
	private String category;
	private Date startDate;
	private Date endDate;
	
	public CouponEntry() {}

	public CouponEntry(EntryDataSource	data) {
		super(data);
		this.couponCode = (String) data.getData("couponCode","N/A");
		this.startDate = (Date) data.getData("startDate",null);
		this.endDate = (Date) data.getData("endDate",null);
		this.category = (String) data.getData("category", "N/A");
	}
	
	public CouponEntry(Document doc) {
		super(doc);
		this.couponCode = (String) doc.get("couponCode");
		this.startDate = new Date(Long.parseLong(doc.get("startDate")));
		this.endDate = new Date(Long.parseLong(doc.get("endDate")));
		this.category = (String) doc.get("category");
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDisplayHTML(ResourceBundle rb) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEntryPageHTML(ResourceBundle rb) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateEntry(AbstractEntry entry) {
		setSummary(entry.getSummary());
	}	
	
	protected String getCacheName() {
		return ICache.COUPONS_ENTRY_CACHE;
	}
	
	public static void main(String args[]) {
		
	}
	
	
}
