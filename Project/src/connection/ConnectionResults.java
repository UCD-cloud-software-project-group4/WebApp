package connection;

import org.json.JSONArray;

import parser.JSONParser;

/** This class enables specific connections to the Papillon system which returns real data on the state
 * of the various components of the data centre
 */ 
public class ConnectionResults {
	static String URL;
	static String storeLocation;
	static int dc_id;
	static int floor_id;
	static int rack_id;
	static int host_id;

	public ConnectionResults() {
	}
	
	
	public ConnectionResults(int new_dc_id) {
		dc_id = new_dc_id;
	}

	public ConnectionResults(int new_dc_id, int new_floor_id) {
		dc_id = new_dc_id;
		floor_id = new_floor_id;
	}

	public ConnectionResults(int new_dc_id, int new_floor_id, int new_rack_id) {
		dc_id = new_dc_id;
		floor_id = new_floor_id;
		rack_id = new_rack_id;
	}

	/** connection results constructor which sets all elements of the data centre
         */ 
	public ConnectionResults(int new_dc_id, int new_floor_id, int new_rack_id,
			int new_host_id) {
		dc_id = new_dc_id;
		floor_id = new_floor_id;
		rack_id = new_rack_id;
		host_id = new_host_id;
	}
        
        /** Get data describing all the racks in the data centre
         */ 
	public String getRacks() {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id
				+ "/allracks";
		storeLocation = "/home/papillon/Desktop/getRackUsage.txt";
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		return result;
	}
       
        /** Get data describing all the racks in the data centre
         */ 
	public String getHosts() {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/allhosts";
		storeLocation = "/home/papillon/workspace/Papillon Interface/appClientModule/Connections/TestResults.txt";
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		return result;
	}
	
	/** Get power usage data for a practicular rack between points in time provided as method parameters
	 */ 
	public JSONArray getUsageRack(long start_time, long end_time) {
		JSONArray result1 = null;
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/floors/" + floor_id + "/racks/" + rack_id  + "/power?starttime="+ start_time +"&endtime=" + end_time;
		try{
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		result1 = JSONParser.parseJson(result, "power");
		}catch(Exception e){
			URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/floors/" + floor_id + "/racks/" + rack_id  + "/power?starttime=0&endtime=1417730183";
			Connection connection = new Connection(URL);
			String result = connection.makeRequest(connection);
			result1 = JSONParser.parseJson(result, "power");
		}
		return result1;
	}
	/** Get power usage data for a practicular host (server) between points in time provided as method parameters
	 */ 
	public JSONArray getUsageHost(long start_time, long end_time) {
		JSONArray result1 = null;
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id	+ "/floors/" + floor_id + "/racks/" + rack_id + "/hosts/" + host_id + "/power?starttime="+ start_time +"&endtime=" + end_time;
		try{
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		result1 = JSONParser.parseJson(result, "power");
		}catch(Exception e){
			URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id	+ "/floors/" + floor_id + "/racks/" + rack_id + "/hosts/" + host_id + "/power?starttime=0&endtime=1417730183";
			Connection connection = new Connection(URL);
			String result = connection.makeRequest(connection);
			result1 = JSONParser.parseJson(result, "power");
		}
		return result1;
	}
	/** Get activity of a certain rack
	 */ 
	public String getActivity(int rack_id) {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id
				+ "/floors/" + floor_id + "/racks/" + rack_id + "/activity?";
		storeLocation = "/home/papillon/Desktop/getRackActivity.txt";
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		return result;
	}

}
