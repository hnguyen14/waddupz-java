package app.Entry;

import java.util.Map;

import org.apache.lucene.document.Document;


public class EntryFactory {

	public static AbstractEntry GetEntryInstance(String entryType , EntryDataSource data) {
		AbstractEntry ret_val = null;
		if (entryType.equalsIgnoreCase(AbstractEntry.NEWS_ENTRY))
			return new NewsEntry(data);
		else if (entryType.equalsIgnoreCase(AbstractEntry.SHOPPING_DEAL_ENTRY))
			return new ShoppingDealsEntry(data);
		else if (entryType.equalsIgnoreCase(AbstractEntry.COUPONS_ENTRY))
			return new CouponEntry(data);

		return ret_val;
	}
	
	public static AbstractEntry GetEntryInstance(String entryType , Document doc) {
		AbstractEntry ret_val = null;
		if (entryType.equalsIgnoreCase(AbstractEntry.NEWS_ENTRY))
			return new NewsEntry(doc);
		else if (entryType.equalsIgnoreCase(AbstractEntry.SHOPPING_DEAL_ENTRY))
			return new ShoppingDealsEntry(doc);
		else if (entryType.equalsIgnoreCase(AbstractEntry.COUPONS_ENTRY))
			return new CouponEntry(doc);

		return ret_val;
	}
	
}
