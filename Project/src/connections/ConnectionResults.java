package connections;

import org.json.JSONArray;

import parser.JSONParser;

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

	// To be used if looking to create a results for a given Host/server
	public ConnectionResults(int new_dc_id, int new_floor_id, int new_rack_id,
			int new_host_id) {
		dc_id = new_dc_id;
		floor_id = new_floor_id;
		rack_id = new_rack_id;
		host_id = new_host_id;
	}

	public String getRacks() {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id
				+ "/allracks";
		storeLocation = "/home/papillon/Desktop/getRackUsage.txt";
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		return result;
	}

	public String getHosts() {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/allhosts";
		storeLocation = "/home/papillon/workspace/Papillon Interface/appClientModule/Connections/TestResults.txt";
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		return result;
	}
	
	public JSONArray getUsageRack(long start_time, long end_time) {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/floors/" + floor_id + "/racks/" + rack_id  + "/power?starttime="+ start_time +"&endtime=" + end_time;
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		JSONArray result1 = JSONParser.parseJson(result, "power");
		return result1;
	}
	
	public JSONArray getUsageHost(long start_time, long end_time) {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id	+ "/floors/" + floor_id + "/racks/" + rack_id + "/hosts/" + host_id + "/power?starttime="+ start_time +"&endtime=" + end_time;
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		JSONArray result1 = JSONParser.parseJson(result, "power");
		return result1;
	}
	
	public String getActivity(int rack_id) {
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id
				+ "/floors/" + floor_id + "/racks/" + rack_id + "/activity?";
		storeLocation = "/home/papillon/Desktop/getRackActivity.txt";
		Connection connection = new Connection(URL);
		String result = connection.makeRequest(connection);
		return result;
	}

}
