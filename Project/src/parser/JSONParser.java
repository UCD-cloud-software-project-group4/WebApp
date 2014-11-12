package parser;
import org.json.*;

public class JSONParser {
	


<<<<<<< Updated upstream
	public static String parseJson(String wholeJSONList, String id, String element){
=======
	public static String parseJson(String wholeJSONList, String id, String element) throws JSONException{
>>>>>>> Stashed changes
		JSONObject obj = new JSONObject(wholeJSONList);
		String pageName = obj.getJSONObject(id).getString(element);
		
		return pageName;
	}
	
	
}

<<<<<<< Updated upstream
	


=======
	
>>>>>>> Stashed changes
