package app.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringEscapeUtils;

public class HttpUtils {
	
	public static String get(String url) {
		return get(url,null,null);
	}
	
	public static String get(String url, String userName, String password) {
		String ret_val = null;
		HttpClient client = new HttpClient();
	
		if (userName != null && password != null) {
			client.getState().setCredentials(null, null, new UsernamePasswordCredentials(userName,password));
		}
		
		GetMethod get = new GetMethod(url);
		get.setFollowRedirects(false);
		get.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.12) Gecko/20080201 Firefox/2.0.0.12");
		
		try {
			int status = client.executeMethod(get);
			if (status == HttpStatus.SC_OK) {
				InputStream stream = get.getResponseBodyAsStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				
				String line = "";
				ret_val = "";
				while ((line = reader.readLine()) != null) {
					ret_val += line + "\n";
				}
			} else if ( status == HttpStatus.SC_MOVED_PERMANENTLY || status == HttpStatus.SC_MOVED_TEMPORARILY) {
				Header locationHeader = get.getResponseHeader("location");
				if (locationHeader != null) {
					System.out.println(locationHeader.getValue());
					return get(locationHeader.getValue());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret_val;
	}
	
	public static void main (String args[]) {
		System.out.println(get("http://www.bbc.co.uk/vietnamese/"));
	}
}


