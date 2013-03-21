package app.API.EntryHandler.Input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.quartz.spi.ThreadPool;

import com.cj.api.ProductSearchServiceV2;
import com.cj.api.ProductSearchServiceV2Locator;
import com.cj.api.ProductSearchServiceV2PortType;
import com.cj.domain.product.ArrayOfProduct;
import com.cj.domain.product.Product;
import com.cj.service.product.ProductResponse;

import app.API.EntryHandler.EntryInputEngine;
import app.API.URL.StaticUrl.FrontPageStaticUrl;
import app.DB.Pools;
import app.Entry.AbstractEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;
import app.Utils.DBUtils;

public class CJProductInput implements IEntryInput{

	public static String developerKey = "008223d727769cc02130381c425b0e624d64ed288b469486a48e6b7ecb1fe08792d10a82cc5aaa41d1779619d79ca5b0fc3747d261d2c10e0bb32feb91722073b1/79d4638ea613882989a27d9200fc2e3a95c90eb393976794955f3c8b9b83ee65c5015309a21a961689fb32d66f027ed1370adfe2fdb91ed87a83d377f8626981";
	public static String websiteId = "2902194";
	public static String BuyAid = "1566996,237343,2014561,1807847,1957211,2125808";
	public static String serviceableArea = "";
	public static String upc = "";
	public static String manufacturerName ="";
	public static String manufacturerSku = "";
	public static String advertiserSku = "";
	public static String lowPrice = "";
	public static String highPrice = "";
	public static String lowSalePrice = "";
	public static String highSalePrice = "";
	public static String currency = "";
	public static String isbn = "";
	public static String sortBy = "";
	public static String sortOrder = "";
	public static String startAt = "0";
	public static String maxResult = "100";
	
	private List<String> keywords = null;
	
	public CJProductInput(List<String> keywords) {
		this.keywords = keywords;
	}
	
	public List getInputList() throws Exception {
		List ret_val = new ArrayList();
		ProductSearchServiceV2 serv = new ProductSearchServiceV2Locator();
		ProductSearchServiceV2PortType service = serv.getproductSearchServiceV2HttpPort();
		int count = 1;
		for (String keyword : this.keywords) {
			ProductResponse response = service.search(  developerKey, websiteId, BuyAid, 
														keyword, serviceableArea, upc, 
														manufacturerName, manufacturerSku, advertiserSku, 
														lowPrice, highPrice, lowSalePrice, 
														highSalePrice, currency, isbn, 
														sortBy, sortOrder, startAt, maxResult);

			ArrayOfProduct productArray = response.getProducts();
			Product[] products = productArray.getProduct();
			if (products != null && products.length > 0) {
				for (Product p : products) {
					
					String cat = p.getCatalogId().trim();
					if (!cat.equalsIgnoreCase("cjo:994") &&	!cat.equalsIgnoreCase("cjo:986")) {
						String title = p.getName();
						String url = p.getBuyUrl();
						String author = "SysUploader";
						long authorId = 0;
						String lang = "en";
						String summary = p.getDescription();
						int points = 0;
						double boost = 0.0;

						boolean showFrontPage = false;
						boolean showSearchPage = true;		

						String domain = url.replaceAll("http://", "").replaceAll("https://", "");
						int idx = domain.indexOf("/");
						if (idx > 0)
							domain = domain.substring(0,idx);

						Date time = new Date(System.currentTimeMillis());


						// THESE VALUE ARE NEEDED FOR SHOPPING DEAL ENTRY
						// THESE VALUE WILL BE DEFAULTED IF ITS NOT SHOPPING DEAL ENTRY FEED
						double price = p.getPrice();
						double listed_price = p.getRetailPrice();
						double rebate = 0.0;
						String mu = p.getCurrency();
						String imageLink = p.getImageUrl();
						
						if (price < listed_price)
							showFrontPage = true;

						// THESE VALUE ARE NEEDED FOR COUPON ENTRY .. 
						// AGAIN ... IT SHOULD BE DEFAULTED.
//						THESE VALUE ARE NEEDED FOR COUPON ENTRY
						String couponCode = "";
						Date startDate = new Date(System.currentTimeMillis());
						Date endDate = new Date(System.currentTimeMillis());
						String category = "N/A";
						// END OF GETTING COUPON DATA

						EntryDataSource data = new EntryDataSource
						(	title,url,domain,author,authorId,time,lang,
								summary,points,boost,null,null,showFrontPage,showSearchPage,
								price,listed_price,rebate,mu,imageLink,
								couponCode,category,startDate,endDate
						);
						ret_val.add(EntryFactory.GetEntryInstance(AbstractEntry.SHOPPING_DEAL_ENTRY,data));
					}
				}
			}
		}
		
		return ret_val;
	}
	
	public static class CJQuery implements Runnable{
		private List<String> keywords;
		private String name;
		public CJQuery(String name , List<String> keywords) {
			this.keywords = keywords;
			this.name = name;
		}
		public void run() {
			try {
				Thread.sleep(500);
				System.out.println("THREAD : " + name + " - QUERYING CJ .....") ;
				IEntryInput method = null;
				method = new CJProductInput(keywords);
				EntryInputEngine input = new EntryInputEngine(method);
				System.out.println("THREAD : " + name + " - INSERT TO DB .....") ;
				input.upsertToDB();
				System.out.println("THREAD : " + name + " - DONE ....." + (new Date(System.currentTimeMillis())).toString()) ;
			} catch (Exception e) {
				System.out.println("ERROR IN THREAD : " + name);
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String args[]) {
	}

}
