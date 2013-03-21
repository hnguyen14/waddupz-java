package app.Utils;

import org.apache.lucene.document.Field;

public class LuceneUtil {

	/********************************** INDEXER HELPER *******************************************/
	//	Factory Method                      		Tokenized 	Indexed 	Stored Use for 
	//	Field.Text(String name, String value) 		Yes 		Yes 		Yes contents you want stored 
	//	Field.Text(String name, Reader value) 		Yes 		Yes 		No  contents you don't want stored 
	//	Field.Keyword(String name, String value) 	No 			Yes 		Yes values you don't want broken down 
	//	Field.UnIndexed(String name, String value) 	No  		No 			Yes values you don't want indexed 
	//	Field.UnStored(String name, String value)	Yes 		Yes 		No  values you don't want stored 

	
	public static Field createKeywordField(String field_name, String value){	
		Field ret_val = new Field(field_name,value,Field.Store.YES,Field.Index.UN_TOKENIZED);
		return ret_val;
	}
	
	public static Field createTextField(String field_name, String value){	
		Field ret_val = new Field(field_name,value,Field.Store.YES,Field.Index.TOKENIZED);
		return ret_val;
	}
	
	public static Field createUnStoredField(String field_name, String value){	
		Field ret_val = new Field(field_name,value,Field.Store.NO,Field.Index.TOKENIZED);
		return ret_val;
	}
	
	public static Field createUnIndexedField(String field_name, String value){	
		Field ret_val = new Field(field_name,value,Field.Store.YES,Field.Index.NO);
		return ret_val;
	}
	
	
}
