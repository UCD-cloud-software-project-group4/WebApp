package joiner;

import hardware.DataCenter;
import hardware.Floor;
import hardware.Host;
import hardware.Rack;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import parser.DateParser;
import parser.JSONParser;

public class FrontScreen {
	static List<DataCenter> datacenters = new ArrayList<DataCenter>();
	static JSONObject json_object_holder;
	static int tempRackid;
	static int tempFloorid;
	static int tempDCid;
	static int tempHostid;
	static long start=22222222;
	static long end=222222;
	
	public FrontScreen(){
		
		
	}
	
	
	/**Adds the datacenter*/
	private static void addDataCenter(){
		
		boolean isUnique=true;
		if(datacenters.isEmpty()){
			datacenters.add(new DataCenter(tempDCid));
		}
		else{
			for(DataCenter dc: datacenters){
				if(tempDCid==dc.getID()){
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

			if(tempDCid == datacenter.getID()){
				
				for(Floor floor: datacenter.getFloors()){
					if(floor.getID()==tempFloorid){
						break outerloop;
					}
					
				}
				
				datacenter.getFloors().add(new Floor(tempDCid,tempFloorid));
				break;
			}
		}
	}
	
	/**Adds the racks as a list to the relevant floors*/
	private static void addRack(){
		
		
		outerloop:
		for(DataCenter datacenter:datacenters){
			for(Floor floor: datacenter.getFloors()){
				
				if(tempDCid==floor.getDCID() && tempFloorid == floor.getID()){
					for(Rack rack :floor.getRacks()){
						if(rack.getID()==tempRackid){
							break outerloop;
						}
					
				}	//TODO input the start and end times
					floor.getRacks().add(new Rack(tempDCid, tempFloorid, tempRackid,start, end));
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
						//TODO Add start and end times
						rack.getHosts().add(new Host(tempDCid, tempFloorid, tempRackid, tempHostid, start,end));
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
			for(Floor floor:dc.getFloors()){
				for(Rack rack:floor.getRacks()){
					for(Host host:rack.getHosts()){
						hosts+="\""+host.getID()+"\",";
					}
				}
			}
		}
		
		return hosts;
		//return hosts.substring(0,hosts.length()-1);
	}
	
	public static String serverAndRackCreation(){
		start();
		String output="";
		for(Floor floor:datacenters.get(0).getFloors()){
			for(Rack rack: floor.getRacks()){
				output+=("\t<div class=\"rack\">\n");
				output+=("\t\t<div id=\"rackinner"+ rack.getID()+"\"  ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\">\n");
				for(Host host:rack.getHosts()){
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
	
		
	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("</head>");
		
		
		//Retrieve the dates needed to add to the hosts
		String startDateString= request.getParameter("start");
		String endDateString=request.getParameter("end");
		
		String[] date = startDateString.split("-");
		//This converts the form date to an epoch time
		start = new DateParser(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])).parse();
		
		date=endDateString.split("-");
		
		end = new DateParser(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0])).parse();

	}
}