package app.Utils;

public class StringUtils {

	public static String join(String[] array, String seperator) {
		String ret_val = "" ;
		for (int i = 0 ; i < array.length; i++) {
			ret_val = ret_val + array[i] + seperator;
		}
		
		return ret_val.substring(0,ret_val.length() - seperator.length());
	}
	
}
