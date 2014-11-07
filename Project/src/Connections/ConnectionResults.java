package Connections;

public class ConnectionResults{
	static String URL;
	static String storeLocation;
	
	static int dc_id;
	static int floor_id ;
	static int rack_id ;
	static int host_id ;
	
	
	
	
	ConnectionResults(){
		
	}
	
	ConnectionResults(int new_dc_id, int new_floor_id){
		dc_id = new_dc_id;
		floor_id = new_floor_id;
	}
	
	ConnectionResults(int new_dc_id, int new_floor_id, int new_rack_id){
		dc_id = new_dc_id;
		floor_id = new_floor_id;
		rack_id = new_rack_id;
	}
	
	//To be used if looking to create a results for a given Host/server
	ConnectionResults(int new_dc_id, int new_floor_id, int new_rack_id, int new_host_id){
		dc_id = new_dc_id;
		floor_id = new_floor_id;
		rack_id = new_rack_id;
		host_id = new_host_id;
	}
	
	public void getRacks(){
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/allracks";
		storeLocation ="/home/papillon/Desktop/getRackUsage.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
	}
	
	public void getHosts(){
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/allhosts";
		storeLocation ="/home/papillon/Desktop/getRackUsage.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
	}
	
	public void getUsage(int rack_id){
		//URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id +"/power?";
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/allracks";
		storeLocation ="/home/papillon/Desktop/getRackUsage.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
		
	}
	
	public void getActivity(int rack_id){
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id +"/activity?";
	    storeLocation ="/home/papillon/Desktop/getRackActivity.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
	}
	
	public void getUsage(int rack_id, int host_id){
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id +"/hosts/" + host_id + "/power?";
		storeLocation ="/home/papillon/Desktop/getHostActivity.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
	}
	
	public void getActivity(int rack_id, int host_id){
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id + "/hosts/" + host_id + "/activity?";
	    storeLocation ="/home/papillon/Desktop/getHostActivity.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
	}
	
	public void test(){
		URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id + "/floors/1/racks/1/hosts/1/activity?starttime=0&endtime=1415375850";
		storeLocation ="/home/papillon/Desktop/getRackUsage.txt";
		Connection connection = new Connection(URL);
		connection.makeRequest(connection, storeLocation);
	}
	
	
	
	
	public static void main (String args []){
		ConnectionResults connection = new ConnectionResults(1, 1);
		connection.test();
		
	}
	
	
}