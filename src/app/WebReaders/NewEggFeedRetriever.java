package app.WebReaders;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;

public class NewEggFeedRetriever {

	private String feedURI;
	private String fileName;
	private boolean onlyGetDeal;
	
	public NewEggFeedRetriever(String uri, String fileName, boolean onlyGetDeal) {
		this.feedURI = uri;
		this.fileName = fileName;
		this.onlyGetDeal = onlyGetDeal;
	}
	
	public void retrieve() throws Exception {
		URL url = new URL(feedURI);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fileName));
		
		String line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			if (line.length() > 0) {
				String toks[] = line.split(",");
				double rebate = Double.parseDouble(toks[14]) - Double.parseDouble(toks[13]);
				double threshold = onlyGetDeal ? 0 : -1;
				if (rebate > threshold) {
					String sku = toks[0];
					String tagUrl = toks[10];
					String cjUrl = "http://www.jdoqocy.com/click-2940316-10440897?url=" + URLEncoder.encode(tagUrl) + "&cjsku=" + sku;
					String outline = 	toks[1] + "|" + cjUrl + "|" + 
										"csvUploader" + "|" + "0" + "|" + "en" + "|" + 
										toks[2] + "|" + "0" + "|" + "0.0" + "|" +
										"1" + "|" + "1" + "|" + toks[13] + "|" + toks[14] + "|" +
										rebate + "|" +
										"USD" + "|" + toks[12] + "|" + "N/A" + "|" + "0" + "|" + "0" + "|" + "N/A" + "\n";
					out.write(outline);
				}
			}
		}
		out.close();
		reader.close();
		is.close();
	}
	
	
	public static void main(String args[]) {
	}
}
