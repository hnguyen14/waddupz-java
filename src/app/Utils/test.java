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

public class test {
	public static void main(String[] args) {
		try {
		System.out.println(HttpUtils.get("http://www.sggp.org.vn/thegioi/index.html"));
		} catch (Exception e ) {
			e.printStackTrace();
		}
        
	}
}
