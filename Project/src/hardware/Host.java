package hardware;

import java.text.DecimalFormat;

import org.json.JSONArray;

import connection.ConnectionResults;

public class Host implements Structures {
	private int dc_id;
	private int floor_id;
	private int rack_id;
	private int host_id;
	private double average;
	private double max;
	private String name;
	private long weight;

	public Host() {
		dc_id = 0;
		floor_id = 0;
		rack_id = 0;
		host_id = 0;
		average = 0;
		max = 0;
		name = "";
	}

	public Host(int dc_id, int floor_id, int rack_id, int host_id, String name, long start_date, long end_date) {
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
		this.host_id = host_id;
		this.average = getAverage(start_date, end_date);
		this.max = getMax(start_date, end_date);
		this.name = name;
	}
		
	/** 
	 * getMax method passes an Connection to return a JSONArray which is manipulated to get the max for a given Host
	 * Pass in an epoch start time and epoch end time (Both epochs in seconds)
	 */
	
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
	
	/** 
	 * getAverage method passes an Connection to return a JSONArray which is manipulated to get the average for a given Host
	 * Pass in an epoch start time and epoch end time (Both epochs in seconds)
	 */

	public double getAverage(long start_date, long end_date) {
		double running_total = 0;
		ConnectionResults results = new ConnectionResults(dc_id, floor_id, rack_id, host_id);
		JSONArray hold_power_stats = results.getUsageHost(start_date, end_date);
		weight = hold_power_stats.length();
		
		
		for(int x=0; x< hold_power_stats.length(); x++){
			running_total += hold_power_stats.getJSONObject(x).getDouble("power");
		}
		return running_total/weight;
	}

	/** Getter to return the host's ID **/
	public int getID() {
		return host_id;
	}
	
	/** Getter to return the host's rack ID **/
	public int getHostRackID(){
		return rack_id;
	}
	
	/** Getter to return the host's floor ID **/
	public int getHostFloorID(){
		return floor_id;
	}
	
	/** Getter to return the host's DC ID **/
	public int getHostDCID(){
		return dc_id;
	}
	
	
	/** Overwrites the toString method to display the Host's ID **/
	public String toString(){
		String to_string = "Host ID: " + host_id;
		return to_string;
	}
	
	/** A getter to return the average figure **/
	public String getAverageFigure(){
		DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(average);
	}
	
	/** A getter to return the max figure **/	
	public String getMaxFigure(){
		DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(max);
	}
	
	/** Getter to return a String name **/
	public String getName(){
		return name;
	}
	
	/**Sets the weight for each host*/
	public long getWeight(){
		return weight;
	}
	

//	public static void main(String[] args){
//		Host host = new Host(1,1,1,2,1416400000,1416410374);
//		System.out.println(host.getID());
//		System.out.println(host.getMaxFigure());
//		System.out.println(host.getAverageFigure());	
//	}
}
