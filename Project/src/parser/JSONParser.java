package parser;
import org.json.*;

public class JSONParser {
	
	/** This method is used to read in a full JSON file and key and returns a JSON array for easier manipulation*/
	public static JSONArray parseJson(String wholeJSONList, String key){
		JSONObject obj = new JSONObject(wholeJSONList);
		JSONArray array = obj.getJSONArray(key);
		return array;		
	}
	
	/** This method is used to read in a full JSON file and key and returns a JSON objects for easier manipulation*/
	public static JSONObject parseJsonObject(String wholeJSONList, String key){
		JSONObject obj = new JSONObject(wholeJSONList);
		JSONObject obj2= obj.getJSONObject(key);
		return obj2;		
	}
	
	public static String parseJson(String wholeJSONList, String key, String element) throws JSONException{
		JSONObject obj = new JSONObject(wholeJSONList);
		obj = obj.getJSONArray(key).getJSONObject(3);
		String rack = obj.getString(element);
		return rack;
	}
}
	
	
