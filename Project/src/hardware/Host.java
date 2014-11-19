package hardware;

import org.json.JSONArray;

import Connections.ConnectionResults;

public class Host implements Structures {
	private int dc_id;
	private int floor_id;
	private int rack_id;
	private int host_id;
	private double average;
	private double max;

	public Host() {
		dc_id = 0;
		floor_id = 0;
		rack_id = 0;
		host_id = 0;
		average = 0;
		max = 0;
	}

	public Host(int dc_id, int floor_id, int rack_id, int host_id, long start_date, long end_date) {
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
		this.host_id = host_id;
		this.average = getAverage(start_date, end_date);
		this.max = getMax(start_date, end_date);
	}
		
	public double getMax(long start_date, long end_date) {
		double max = 0;
		ConnectionResults results = new ConnectionResults(dc_id, floor_id, rack_id, host_id);
		JSONArray hold_power_stats = results.getUsageHost(start_date, end_date);
		
		for(int x=0; x< hold_power_stats.length(); x++){
			if(max<hold_power_stats.getJSONObject(x).getDouble("power")){
				max = hold_power_stats.getJSONObject(x).getDouble("power");
			}
		}
		return max;
	}

	public double getAverage(long start_date, long end_date) {
		double running_total = 0;
		double count_recordings = 0;
		ConnectionResults results = new ConnectionResults(dc_id, floor_id, rack_id, host_id);
		JSONArray hold_power_stats = results.getUsageHost(start_date, end_date);
		count_recordings = hold_power_stats.length();
		
		for(int x=0; x< hold_power_stats.length(); x++){
			running_total += hold_power_stats.getJSONObject(x).getDouble("power");
		}
		return running_total/count_recordings;
	}

	public int getID() {
		return host_id;
	}
	
	public int getHostRackID(){
		return rack_id;
	}
	
	public int getHostFloorID(){
		return floor_id;
	}
	
	public int getHostDCID(){
		return dc_id;
	}
	
	public String toString(){
		String to_string = "Host ID: " + host_id;
		return to_string;
	}

	public static void main(String[] args){
		Host host = new Host(1,1,1,1,1416400000,1416404989);
		System.out.println(host.max);
		System.out.println(host.average);	
	}
}
