package app.API.EntryHandler.Input;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.API.EntryHandler.EntryInputEngine;
import app.Entry.AbstractEntry;
import app.Entry.EntryDataSource;
import app.Entry.EntryFactory;

public class CSVEntryInput implements IEntryInput{
/**
 * 
 * CSV file is formatted as follow:
 * File name : Author/Seller + "_" + type.csv
 * 
 * Columns
 * 
0 - title
1 - url
2 - author  
3 - authorId 
4 - language 
5 - summary 
6 - points) 
7 - internalBoost)
8 - showOnFrontPage)
9 - showOnSearchPage)
10- price)
11- listedPrice)
12- rebate 
13- monetaryUnit  
14- imageLink 
15- couponCode
16- startDate
17- endDate
18- category
 * 
 */
	
	private String type;
	private String fileName;
	
	public CSVEntryInput ( String fileName) {
		this.fileName = fileName;
		String absoluteFileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
		String fn = absoluteFileName.substring(0,absoluteFileName.length() - 4);
		String toks[] = fn.split("_");
		String typeStr = toks[1];
		if (typeStr.equalsIgnoreCase("news"))
			this.type = AbstractEntry.NEWS_ENTRY;
		else if (typeStr.equalsIgnoreCase("shop"))
			this.type = AbstractEntry.SHOPPING_DEAL_ENTRY;
	}
	
	
	public List getInputList() throws Exception{
		List ret_val = new ArrayList();
		InputStream is = new FileInputStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = "";
		int count = 0;
		while ((line = reader.readLine()) != null ) {
			if (line.trim().length() > 0) {
				count++;
				try {
					String toks[] = line.split("\\|");
					// ENTRY CORE DATA
					String title = toks[0];
					String url = toks[1];
					String author = toks[2];
					long authorId = Long.parseLong(toks[3]);
					String lang = toks[4];
					String summary = toks[5];
					String domain = url.replaceAll("http://", "").replaceAll("https://", "");
					int points = Integer.parseInt(toks[6]);
					double boost = Double.parseDouble(toks[7]);

					boolean showFrontPage = Boolean.parseBoolean(toks[8]);
					boolean showSearchPage = Boolean.parseBoolean(toks[9]);
					int idx = domain.indexOf("/");
					if (idx > 0)
						domain = domain.substring(0,idx);		
					Date time = new Date(System.currentTimeMillis());


					// THESE VALUE ARE NEEDED FOR SHOPPING DEAL ENTRY
					// THESE VALUE WILL BE DEFAULTED IF ITS NOT SHOPPING DEAL ENTRY FEED
					double price = Double.parseDouble(toks[10]);
					double listed_price = Double.parseDouble(toks[11]);
					double rebate = Double.parseDouble(toks[12]);
					String mu = toks[13];
					String imageLink = toks[14];
					
					// THESE VALUE ARE NEEDED FOR COUPON ENTRY .. 
					// AGAIN ... IT SHOULD BE DEFAULTED.
//					 THESE VALUE ARE NEEDED FOR COUPON ENTRY
					String couponCode = toks[15];
					Date startDate = new Date(Long.parseLong(toks[16]));
					Date endDate = new Date(Long.parseLong(toks[17]));
					String category = toks[18];
					// END OF GETTING COUPON DATA
					
					EntryDataSource data = new EntryDataSource
					(	title,url,domain,author,authorId,time,lang,
							summary,points,boost,null,null,showFrontPage,showSearchPage,
							price,listed_price,rebate,mu,imageLink,
							couponCode,category,startDate,endDate
					);
					ret_val.add(EntryFactory.GetEntryInstance(type,data));
				} catch (Exception e) {
//					e.printStackTrace();
//					return ret_val;
				}
			}
		}
		return ret_val;
	}
	public static void main(String args[] ) {
//		try {
//			System.out.println(new Date(System.currentTimeMillis()));
//			IEntryInput method = new CSVEntryInput("C:/waddupz/import/NewEgg_shop.csv");
//			EntryInputEngine e = new EntryInputEngine(method);
//			e.upsertToDB();
//			System.out.println(new Date(System.currentTimeMillis()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	

}
