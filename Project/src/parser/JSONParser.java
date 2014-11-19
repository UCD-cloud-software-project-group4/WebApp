package parser;
import org.json.*;

public class JSONParser {
	public static JSONArray parseJson(String wholeJSONList, String key){
		JSONObject obj = new JSONObject(wholeJSONList);
		JSONArray array = obj.getJSONArray(key);
		return array;		
	}
	
	//Not being used yet
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
	
	
