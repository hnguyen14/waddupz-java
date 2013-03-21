package app.API.Indexer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;

import app.API.Search.Keyword.Phrase;
import app.DB.Pools;
import app.Entry.AbstractEntry;
import app.Entry.CouponEntry;
import app.Entry.EntryComment;
import app.Entry.ShoppingDealsEntry;
import app.Utils.ConfigUtils;
import app.Utils.HibernateUtil;
import app.Utils.LuceneUtil;

public class EntriesIndexer {
	
	public static void indexAllEntry(String entryType, Logger logger) {
		try {
			String dir = ConfigUtils.getAppProperty("server.data.directory") + "index/" + entryType;
			File file = new File (dir);
			if (!file.exists())
				file.mkdir();
			Directory idx_dir = FSDirectory.getDirectory(dir);
			IndexWriter writer = new IndexWriter(idx_dir,new StandardAnalyzer(),true);
			
			Connection con = Pools.getConnection();
			String sql = "select title from ENTRY where discriminator='" + entryType + "' order by created_date desc";
			PreparedStatement stmt = con.prepareStatement(sql);
			
			if (logger != null ) {
				logger.info(sql);
			} else {
				System.out.println(sql);
			}
			
			ResultSet rs = stmt.executeQuery();
			
			int count = 1;
			while  (rs.next()) {
				if (count++ % 100 == 0) {
					if (logger != null) {
						logger.info("Processed ..... " + count + " entries");
					} else {
						System.out.println("Processed ..... " + count + " entries");
					}
				}
				try {
					String entry_title = rs.getString("title");
					Session session = HibernateUtil.getSessionFactory().openSession();

					Object entry = session.get(AbstractEntry.class, entry_title);

					if (entry != null) {
						AbstractEntry coreEntry = (AbstractEntry) entry;

						long id = coreEntry.getId();
						String title = coreEntry.getTitle();
						String url = coreEntry.getUrl();
						String domain = coreEntry.getDomain();
						String author = coreEntry.getAuthor();
						long authorId = coreEntry.getAuthorId();
						Date createdDate = coreEntry.getCreatedDate();
						String language = coreEntry.getLanguage();
						String summary = coreEntry.getSummary();
						int points = coreEntry.getPoints();
						double internalBoost = coreEntry.getInternalBoost();
						List comments = coreEntry.getComments();
						List tags = coreEntry.getTags();

						String comment_str = "";
						for (int i = 0 ; i < comments.size(); i++) {
							comment_str += ((EntryComment) comments.get(i)).toString();
							if (i < comments.size() - 1)
								comment_str += "|";
						}

						String tag_str = "";
						for (int i = 0 ; i < tags.size(); i++) {
							tag_str += ((Phrase) tags.get(i)).toString();
							if (i < tags.size() - 1)
								tag_str += "|";
						}

						boolean showOnFrontPage = coreEntry.isShowOnFrontPage();
						boolean showOnSearchPage = coreEntry.isShowOnSearchPage();

						String discriminator = ""; 

						// SHOPPING DEALS ENTRY
						double price = 0.0;
						double listedPrice = 0.0;
						double rebate = 0.0;
						String monetaryUnit = "USD";
						String imageLink = "";

						// COUPON ENTRY
						String couponCode = "";
						String category = "";
						Date startDate = new Date(0);
						Date endDate = new Date(0);

						if (entry instanceof ShoppingDealsEntry) {
							ShoppingDealsEntry shopEntry = (ShoppingDealsEntry) entry;
							discriminator = "app.Entry.ShoppingDealsEntry";
							price = shopEntry.getPrice(); 
							listedPrice = shopEntry.getListedPrice();
							rebate = shopEntry.getRebate();
							monetaryUnit = shopEntry.getMonetaryUnit();
							imageLink = shopEntry.getImageLink();
						} else if (entry instanceof CouponEntry) {
							CouponEntry couponEntry = (CouponEntry) entry;
							discriminator = "app.Entry.CouponEntry";
							couponCode = couponEntry.getCouponCode();
							category = couponEntry.getCategory();
							startDate = couponEntry.getStartDate();
							if (startDate == null)
								startDate = new Date(System.currentTimeMillis());
							endDate = couponEntry.getEndDate();
							if (endDate == null)
								endDate = new Date(System.currentTimeMillis());
						} else {
							discriminator = "app.Entry.NewsEntry";
						}


						Document doc = new Document();
//						System.out.println(title);

						doc.add(LuceneUtil.createTextField("id", String.valueOf(id)));
						doc.add(LuceneUtil.createTextField("entryType", discriminator));
						doc.add(LuceneUtil.createTextField("title", title));
						doc.add(LuceneUtil.createUnIndexedField("url", url));
						doc.add(LuceneUtil.createTextField("author", author));
						doc.add(LuceneUtil.createUnIndexedField("authorId", String.valueOf(authorId)));
						doc.add(LuceneUtil.createTextField("domain", domain));
						doc.add(LuceneUtil.createTextField("createdDate", String.valueOf(createdDate.getTime())));
						doc.add(LuceneUtil.createTextField("language", language));
						doc.add(LuceneUtil.createTextField("summary", summary == null ? "" : summary));
						doc.add(LuceneUtil.createUnIndexedField("points", String.valueOf(points)));
						doc.add(LuceneUtil.createUnIndexedField("internalBoost", String.valueOf(internalBoost)));
						doc.add(LuceneUtil.createUnIndexedField("comments", comment_str));
						doc.add(LuceneUtil.createUnIndexedField("tags", tag_str));
						doc.add(LuceneUtil.createTextField("showOnFrontPage", String.valueOf(showOnFrontPage)));
						doc.add(LuceneUtil.createTextField("showOnSearchPage", String.valueOf(showOnSearchPage)));
						doc.add(LuceneUtil.createUnIndexedField("price", String.valueOf(price)));
						doc.add(LuceneUtil.createUnIndexedField("listedPrice", String.valueOf(listedPrice)));
						doc.add(LuceneUtil.createUnIndexedField("rebate", String.valueOf(rebate)));
						doc.add(LuceneUtil.createUnIndexedField("monetaryUnit", monetaryUnit));
						doc.add(LuceneUtil.createUnIndexedField("imageLink", imageLink));
						doc.add(LuceneUtil.createUnIndexedField("couponCode", couponCode == null ? "" : couponCode));
						doc.add(LuceneUtil.createUnIndexedField("startDate", String.valueOf(startDate.getTime())));
						doc.add(LuceneUtil.createUnIndexedField("endDate", String.valueOf(endDate.getTime())));
						doc.add(LuceneUtil.createTextField("category", category == null ? "" : category));
						
						if (price < listedPrice) {
							double discount = ((listedPrice - price) / listedPrice) * 100;
							doc.setBoost((new Float(discount)).floatValue() * 100f + points * 100f);
						} else {
							doc.setBoost((points+1) * 100f);
						}
						writer.addDocument(doc);
					}
					session.close();
				} catch (Exception e) {
					if (logger != null) {
						logger.error("Error", e);						
					}
				}
			}
			
			writer.optimize();
			writer.close();
		} catch (Exception e) {
			if (logger != null) {
				logger.error("Error", e);
			} else {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		System.out.println(String.valueOf(true));
	}
}
