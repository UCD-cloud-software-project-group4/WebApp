package joiner;

import hardware.DataCenter;
import hardware.Floor;
import hardware.Host;
import hardware.Rack;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import parser.JSONParser;

public class FrontScreen {
	static List<DataCenter> datacenters = new ArrayList<DataCenter>();
	static JSONObject json_object_holder;
	static int tempRackid;
	static int tempFloorid;
	static int tempDCid;
	static int tempHostid;
	
	public FrontScreen(){
		start();
		
	}
	
	
	/**Adds the datacenter*/
	private static void addDataCenter(){
		
		boolean isUnique=true;
		if(datacenters.isEmpty()){
			datacenters.add(new DataCenter(tempDCid));
		}
		else{
			for(DataCenter dc: datacenters){
				if(tempDCid==dc.dc_id){
					isUnique=false;
					break;
				}
		}		
			
		if(isUnique){
			datacenters.add(new DataCenter(tempDCid));
			
		}
					
	}
		
		
	}
		
	/**Adds the floors as a list to the datacenters*/
	private static void addFloor(){
		
		outerloop:
		for(DataCenter datacenter:datacenters){

			if(tempDCid == datacenter.dc_id){
				
				for(Floor floor: datacenter.dc_floors){
					if(floor.getID()==tempFloorid){
						break outerloop;
					}
					
				}
				
				datacenter.dc_floors.add(new Floor(tempDCid,tempFloorid));
				break;
			}
		}
	}
	
	/**Adds the racks as a list to the relevant floors*/
	private static void addRack(){
		
		
		outerloop:
		for(DataCenter datacenter:datacenters){
			for(Floor floor: datacenter.dc_floors){
				
				if(tempDCid==floor.dc_id && tempFloorid == floor.floor_id){
					for(Rack rack :floor.floor_racks){
						if(rack.getID()==tempRackid){
							break outerloop;
						}
					
				}
					floor.floor_racks.add(new Rack(tempDCid, tempFloorid, tempRackid));
					break outerloop;
				}
			}
		}
	}
	
	/**Adds the hosts as a list to the relevant racks*/
	private static void addHost(){
		
		for(DataCenter datacenter: datacenters){
			for(Floor floor: datacenter.getFloors()){
				for(Rack rack: floor.getRacks()){
					if(tempDCid==datacenter.getID()&&tempRackid==rack.getID()&&tempFloorid==floor.getID()){
						rack.rack_hosts.add(new Host(tempDCid, tempFloorid, tempRackid, tempHostid));
						System.out.println(tempHostid);
					}
				}
			}
		}
	}

	
	public static String hostToString(){
		String hosts="";
		
		start();
		
		for(DataCenter dc: datacenters){
			for(Floor floor:dc.dc_floors){
				for(Rack rack:floor.floor_racks){
					for(Host host:rack.rack_hosts){
						hosts+="\""+host.getID()+"\",";
					}
				}
			}
		}
		
		
		return hosts.substring(0,hosts.length()-1);
	}
	
	public static String serverAndRackCreation(){
		String output="";
		for(Floor floor:datacenters.get(0).dc_floors){
			for(Rack rack: floor.floor_racks){
				output+=("\t<div class=\"rack\">\n");
				output+=("\t\t<div id=\"rackinner"+ rack.getID()+"\"  ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\">\n");
				for(Host host:rack.rack_hosts){
					output+=("\t\t\t<img name=\"server\" onClick=\"displayServerInfo(this, "+(host.getID()-1) +")\" src=\"server.jpg\" id=\"drag"+(host.getID()-1)+"\" draggable=\"true\" ondragstart=\"drag(event)\" />\n");
				}
				
				
				output+=("\t\t</div>\n");
				output+=("\t</div>\n");
				
				
			}
			
			
		}
		reset();
		return output;
	}
	
	public static void reset(){
		datacenters= new ArrayList<DataCenter>();
		
	}
	
	
	public static void start(){
		
	
			
		//ConnectionResults connection = new ConnectionResults(1, 1);
		String test = "{\"hostExtended\":[{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"1\",\"rackName\":\"R1\",\"rackDescription\":\"R1\",\"hostId\":\"1\",\"hostName\":\"Host1\",\"hostDescription\":\"Host1\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"},{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"1\",\"rackName\":\"R1\",\"rackDescription\":\"R1\",\"hostId\":\"2\",\"hostName\":\"Host2\",\"hostDescription\":\"Host 2\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"},{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"1\",\"rackName\":\"R1\",\"rackDescription\":\"R1\",\"hostId\":\"3\",\"hostName\":\"Host3\",\"hostDescription\":\"Host 3\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"},{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"2\",\"rackName\":\"R2\",\"rackDescription\":\"Rack 2\",\"hostId\":\"4\",\"hostName\":\"Host4\",\"hostDescription\":\"Host 4\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"}]}";
		// The info Papillon JSON returns is in a JSON array made up of JSON
		// objects
		JSONArray test2 = JSONParser.parseJson(test, "hostExtended");
		
		int number_of_hosts = test2.length();
		
				
		for (int x = 0; x < number_of_hosts; x++) {
			json_object_holder = test2.getJSONObject(x);
			tempDCid = Integer.parseInt((String) json_object_holder.get("datacenterId"));
			tempFloorid = Integer.parseInt((String) json_object_holder.get("floorId"));
			tempRackid = Integer.parseInt((String) json_object_holder.get("rackId"));
			tempHostid = Integer.parseInt((String) json_object_holder.get("hostId"));
			
			addDataCenter();
			addFloor();
			addRack();
			addHost();
		}
		/*
		System.out.println("Datacenter: "+ datacenters.get(0).dc_id);
		System.out.println("Floor: " + datacenters.get(0).dc_floors.get(0).floor_id);
		System.out.println("Rack: "+ datacenters.get(0).dc_floors.get(0).floor_racks.get(1).getID());
		System.out.println("Host: " + datacenters.get(0).dc_floors.get(0).floor_racks.get(0).rack_hosts.get(0).getID());
		
		System.out.println(hostToString());
		*/
	}
	}
		
		

	