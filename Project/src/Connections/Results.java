package Hardware;

import javax.swing.text.html.HTML;

public class Results{
	Connection connection;
	String URL;
	static DataCenter dc_id;
	static Floor floor_id;
	Rack rack_id;
	Host host_id;
	
	
	Results(){
		// some deault URL;
	}
	
	// To be used when 
	Results(DataCenter dc_id, Floor floor_id, Rack rack_id){
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
	}
	
	//To be used if looking to create a results for a given Host/server
	Results(DataCenter dc_id, Floor floor_id, Rack rack_id, Host host_id){
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
		this.host_id = host_id;
	}
	
	public static String getUsage(Rack rack_id){
		String URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id +"/power?";
		return URL;
	}
	
	public static String getActivity(Rack rack_id){
		String URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id +"/activity?";
		return URL;
	}
	
	public static String getUsage(Rack rack_id, Host host_id){
		String URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id +"/hosts/" + host_id + "/power?";
		return URL;
	}
	
	public static String getActivity(Rack rack_id, Host host_id){
		String URL = "http://localhost:8080/papillonserver/rest/datacenters/" + dc_id +"/floors/"+ floor_id + "/racks/" + rack_id + "/hosts/" + host_id + "/activity?";
		return URL;
	}
	
	//I will add the methods that allow us to use a time (Epoch) at later stage
	
	
}
