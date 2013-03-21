package app.Test;

import com.cj.api.ProductSearchServiceV2;
import com.cj.api.ProductSearchServiceV2HttpBindingStub;
import com.cj.api.ProductSearchServiceV2Locator;
import com.cj.api.ProductSearchServiceV2PortType;
import com.cj.domain.product.ArrayOfProduct;
import com.cj.domain.product.Product;
import com.cj.service.product.ProductResponse;

public class CjProductSearch {
	
	public static String developerKey = "008223d727769cc02130381c425b0e624d64ed288b469486a48e6b7ecb1fe08792d10a82cc5aaa41d1779619d79ca5b0fc3747d261d2c10e0bb32feb91722073b1/79d4638ea613882989a27d9200fc2e3a95c90eb393976794955f3c8b9b83ee65c5015309a21a961689fb32d66f027ed1370adfe2fdb91ed87a83d377f8626981";
	public static String websiteId = "2902194";
	public static String BuyAid = "1566996,237343,2014561";
	public static String keywords = "Tunes For Tots  (1998)";
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
	
	public static void main (String args[]) {
		try {
			
			ProductSearchServiceV2 serv = new ProductSearchServiceV2Locator();
			ProductSearchServiceV2PortType service = serv.getproductSearchServiceV2HttpPort();
			
			ProductResponse response = service.search( developerKey, websiteId, BuyAid, 
														keywords, serviceableArea, upc, 
														manufacturerName, manufacturerSku, advertiserSku, 
														lowPrice, highPrice, lowSalePrice, 
														highSalePrice, currency, isbn, 
														sortBy, sortOrder, startAt, maxResult);
			
			ArrayOfProduct productArray = response.getProducts();
			Product[] products = productArray.getProduct();
			for (Product p : products) {
					System.out.println(p.getName() + " - " + p.getBuyUrl());
					System.out.println("\t" + p.getDescription());
					System.out.println("\t" + p.getCatalogId());
					System.out.println("\t You saved : " + p.getCurrency() + (p.getRetailPrice() - p.getSalePrice()));
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
