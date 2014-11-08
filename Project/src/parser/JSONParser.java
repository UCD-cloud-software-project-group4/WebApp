package parser;
import org.json.*;

public class JSONParser {
	


	public static String parseJson(String wholeJSONList, String id, String element){
		JSONObject obj = new JSONObject(wholeJSONList);
		String pageName = obj.getJSONObject(id).getString(element);
		
		return pageName;
	}
	
	
}

	


