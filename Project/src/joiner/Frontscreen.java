package joiner;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import parser.JSONParser;
import Hardware.*;
import Connections.*;

public class Frontscreen {
	public static void main(String args[]) {
		ConnectionResults connection = new ConnectionResults(1, 1);
		String test = connection.getHosts();
		
		//The info Papillon json returns is in a JSON array made up of JSON objects
		JSONArray test2 = JSONParser.parseJson(test, "hostExtended");
		int number_of_hosts = test2.length();
		
		/*
		 * This section creats the hosts
		 */
		
		Host[] hold_hosts = new Host[number_of_hosts];
		
		// Loop through each host to add them to the array of Hosts created above
		for(int x=0; x<number_of_hosts; x++){
			JSONObject json_object_holder = test2.getJSONObject(x);
			int dc_id_holder = Integer.parseInt((String) json_object_holder.get("datacenterId"));
			int floor_id_holder = Integer.parseInt((String) json_object_holder.get("floorId"));
			int rack_id_holder = Integer.parseInt((String) json_object_holder.get("rackId"));
			int host_id_holder = Integer.parseInt((String) json_object_holder.get("hostId"));
			
			Host temp_host = new Host(dc_id_holder, floor_id_holder, rack_id_holder, host_id_holder);
			hold_hosts[x] = temp_host;			
		}
		
		/*
		 *This section creates the Racks 
		 */
		
		//Find out how many racks there are
		int number_of_racks = 0;	
		for(int x = 0, y = 1; y<number_of_hosts; y++){
			number_of_racks = Math.max(hold_hosts[x].rack_id,hold_hosts[y].rack_id);
		}
		
		//Create an array of racks to hold them as they are created
		Rack[] hold_racks = new Rack[number_of_racks];
		
		for(int x=0; x<number_of_racks; x++){
			List<Host> host_list_holder = new ArrayList<Host>();
			int dc_id = 0;
			int floor_id = 0;
			int rack_id = 0;
			
			for(int y=0; y<number_of_hosts; y++){
				if(hold_hosts[y].rack_id == x+1){
					host_list_holder.add(hold_hosts[y]);
					dc_id = hold_hosts[y].dc_id;
					floor_id = hold_hosts[y].floor_id;
					rack_id = hold_hosts[y].rack_id;
				}
			}			
			Rack rack = new Rack(dc_id, floor_id, rack_id, host_list_holder);
			hold_racks[x] = rack;
		}
		
		/*
		 *This section creates the Floors
		 */
		
		//Find out how many racks there are
		int number_of_floors = 0;	
		
		if(hold_racks.length<=1){
			number_of_floors = 1;
		}else{		
			for(int x = 0, y = 1; y<number_of_racks; y++){
				number_of_floors = Math.max(hold_racks[x].floor_id,hold_racks[y].floor_id);
			}
		}
		
	
		//Create an array of racks to hold them as they are created
		Floor[] hold_floors = new Floor[number_of_floors];		
		
		for(int x=0; x<number_of_floors; x++){
			List<Rack> rack_list_holder = new ArrayList<Rack>();
			int dc_id = 0;
			int floor_id = 0;
			
			for(int y=0; y<number_of_racks; y++){
				if(hold_racks[y].floor_id == x+1){
					rack_list_holder.add(hold_racks[y]);
					dc_id = hold_racks[y].dc_id;
					floor_id = hold_racks[y].floor_id;
				}
			}			
			Floor floor = new Floor(dc_id, floor_id, rack_list_holder);
			hold_floors[x] = floor;
		}
		
		/*
		 *This section creates the Overall DC
		 */
		
		//Find out how many dcs there are
		int number_of_dcs = 0;	
		
		if(hold_floors.length<=1){
			number_of_dcs = 1;
		}else{		
			for(int x = 0, y = 1; y<number_of_floors; y++){
				number_of_dcs = Math.max(hold_floors[x].dc_id,hold_floors[y].dc_id);
			}
		}
		
		
		//Create an array of racks to hold them as they are created
		DataCenter[] hold_dcs = new DataCenter[number_of_dcs];
		
		for(int x=0; x<number_of_dcs; x++){
			List<Floor> floor_list_holder = new ArrayList<Floor>();
			int dc_id = 0;
			
			for(int y=0; y<number_of_floors; y++){
				if(hold_floors[y].dc_id == x+1){
					floor_list_holder.add(hold_floors[y]);
					dc_id = hold_floors[y].dc_id;
				}
			}			
			DataCenter dc = new DataCenter(dc_id, floor_list_holder);
			hold_dcs[x] = dc;
		}
				
		
		System.out.println(hold_dcs[0].toString());
	}
		
}