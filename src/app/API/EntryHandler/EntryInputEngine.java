package app.API.EntryHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.API.EntryHandler.Input.IEntryInput;
import app.API.EntryHandler.Input.SingleEntryInput;
import app.API.EntryHandler.Input.XMLEntryInput;
import app.API.Search.Keyword.Phrase;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.Entry.AbstractEntry;
import app.Entry.CouponEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;
import app.Entry.NewsEntry;
import app.Entry.ShoppingDealsEntry;
import app.Utils.DBUtils;
import app.Utils.HibernateUtil;
import app.Utils.TextUtils;

public class EntryInputEngine {
	
	private AbstractEntry[] entries;
	private Logger log = null;
	
	public EntryInputEngine(IEntryInput entryInputMethod, Logger log) throws Exception{
		this.log = log;
		List entryList = entryInputMethod.getInputList();
		this.entries = new AbstractEntry[entryList.size()];
		for (int i = 0; i < entryList.size() ; i++ ) {
			this.entries[i] = (AbstractEntry) entryList.get(i);
		}
	}
	
	public EntryInputEngine(IEntryInput entryInputMethod) throws Exception{
		this (entryInputMethod, null);
	}
	
	private String getCategoryName(AbstractEntry entry) {
		if (entry instanceof NewsEntry) {
			return FrontPageStaticUrl.PAGE_TYPE_NEWS;
		} else if (entry instanceof ShoppingDealsEntry) {
			return FrontPageStaticUrl.PAGE_TYPE_SHOP_DEALS;
		} else if (entry instanceof CouponEntry) {
			return FrontPageStaticUrl.PAGE_TYPE_COUPON;
		}
		return null;
	}
	
//	public void upsertToDB() {
//		
//		
//		try{
//			int count = 0;
//			long time = System.currentTimeMillis();
//			Session session = HibernateUtil.getSessionFactory().openSession();
//			Transaction t = session.beginTransaction();
//			for (AbstractEntry entry : entries) {
//				List<String> keywords = TextUtils.extractKeyword(entry.getTitle() + " " + entry.getSummary());
//				for (String keyword : keywords) {
//					Phrase p = new Phrase(keyword,getCategoryName(entry),entry.getLanguage(),true);
//					entry.addTags(p);
//				}
////				try {
//					session.saveOrUpdate(entry);
//
////					if (count++ % 1000 == 0) {
////						System.out.println("Processing ..... " + count + " products ... " + ((System.currentTimeMillis() - time) / 60000) + " mins");
////						time = System.currentTimeMillis();
////						// Flush the batch on the session. then clear 1st level cache to release memory allocated.
////						session.flush();
////						session.clear();
////					}
//					// Checking for duplicate Entry Already existed in DB
////				} catch (Exception e) {
////					System.out.println("ENTRY Already exist");
////				}
//				
//			}
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}

	public void upsertToDB() {
		int count = 0;
		long time = System.currentTimeMillis();
		for (AbstractEntry entry : entries) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction t = session.beginTransaction();

			if (count++ % 1000 == 0 && log != null) {
				log.info ("Processing ..... " + count + " products ... " + ((System.currentTimeMillis() - time) / 60000) + " mins");
				time = System.currentTimeMillis();
			}
			try {
//				 Checking for duplicate Entry Already existed in DB
				byte[] utf8bytes = entry.getTitle().getBytes("UTF8");
				AbstractEntry existedEntry = (AbstractEntry) session.get(AbstractEntry.class, new String(utf8bytes,"UTF8"));

				if (existedEntry != null) {
					existedEntry.updateEntry(entry);
					session.update(existedEntry);
				} else {
					List<String> keywords = TextUtils.extractKeyword(entry.getTitle() + " " + entry.getSummary());
					for (String keyword : keywords) {
						Phrase p = new Phrase(keyword,getCategoryName(entry),entry.getLanguage(),true);
						entry.addTags(p);
					}
					// save or update to prevent multithread batch update
					session.saveOrUpdate(entry);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			t.commit();
			session.close();
		}


	}
	
	
	public static void main (String args[]) { 
		try {
//			Map ruleMap = new HashMap();
//			ruleMap.put("title", "title");
//			ruleMap.put("url", "link");
			EntryDataSource eds = new EntryDataSource("title test id", "https://ttie.com/test_title", 
					"ttie.com", "dfs", 0, 
					new Date(System.currentTimeMillis()), "en", "title test it", 
					0, 0.0, new ArrayList(), 
					null, true, true,
					0.0,0.0,0.0, 
					"USD", "",
					"","", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
			
			
			IEntryInput method = new SingleEntryInput(EntryFactory.GetEntryInstance(AbstractEntry.NEWS_ENTRY,eds));
			EntryInputEngine e = new EntryInputEngine(method);
			e.upsertToDB();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
