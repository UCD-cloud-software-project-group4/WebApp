package parser;
import org.json.*;

public class JSONParser {
	public static JSONArray parseJson(String wholeJSONList, String key){
		key = "hostExtended";
		JSONObject obj = new JSONObject(wholeJSONList);
		JSONArray array = obj.getJSONArray(key);
		return array;		
	}
	
	
	public static String parseJson(String wholeJSONList, String key, String element) throws JSONException{
		JSONObject obj = new JSONObject(wholeJSONList);
		obj = obj.getJSONArray(key).getJSONObject(3);
		String rack = obj.getString(element);
		return rack;
	}
}
	
	
