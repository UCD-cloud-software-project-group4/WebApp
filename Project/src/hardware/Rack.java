package hardware;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import connections.ConnectionResults;

public class Rack implements Structures{
	private List<Host> rack_hosts = new ArrayList<Host>();
	private int dc_id;
	private int floor_id;
	private int rack_id;
	private double average;
	private double max;
	
	public Rack(){
		rack_hosts = null;
		dc_id = 0;
		floor_id = 0;
		rack_id = 0;
		average = 0;
		max = 0;
	}
	
	public Rack(int dc_id, int floor_id, int rack_id,long start_date, long end_date){
		this.rack_hosts = rack_hosts;
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
		this.average = getAverage(start_date, end_date);
		this.max = getMax(start_date, end_date);
	}
	
	public void addHost(Host h){
		rack_hosts.add(h);
	}
	
	public void removeHost(Host h){
		rack_hosts.remove(h);
	}
	
	public boolean hasHost(Host h){
		return rack_hosts.contains(h);
	}

	public double getMax(long start_date, long end_date) {
		double max = 0;
		ConnectionResults results = new ConnectionResults(dc_id, floor_id, rack_id);
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
		ConnectionResults results = new ConnectionResults(dc_id, floor_id, rack_id);
		JSONArray hold_power_stats = results.getUsageRack(start_date, end_date);
		count_recordings = hold_power_stats.length();
		for(int x=0; x< hold_power_stats.length(); x++){
			running_total += hold_power_stats.getJSONObject(x).getDouble("power");
		}
		return running_total/count_recordings;
	}

	@Override
	public int getID() {
		return rack_id;
	}
	
	public int getFloorID() {
		return floor_id;
	}
	
	public int getDCID() {
		return dc_id;
	}
	
	public List<Host> getHosts(){
		return rack_hosts;
	}
	
	public static void main(String[] args){
		Host host1 = new Host(1,1,1,1,1416400000,1416404989);
		Host host2 = new Host(1,1,1,2,1416400000,1416404989);
		Host host3 = new Host(1,1,1,1,1416400000,1416404989);
		Host host4 = new Host(1,1,1,1,1416400000,1416404989);
		List<Host> host_list = new ArrayList<Host>();
		host_list.add(host1);
		host_list.add(host2);
		host_list.add(host3);
		host_list.add(host4);
		Rack rack= new Rack(1,1,1,1416400000,1416404989);	
		System.out.println(rack.max);	
		System.out.println(rack.average);
	}

}
