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
import connections.ConnectionResults;

public class FrontScreen {
	static List<DataCenter> datacenters = new ArrayList<DataCenter>();
	static JSONObject json_object_holder;
	static int tempRackid;
	static int tempFloorid;
	static int tempDCid;
	static int tempHostid;
	static String tempHostName;
	static String tempRackName;
	static int test = 0;
	//static long start = 1416400000;
	//static long end = 1416410374;

	/** Adds the datacenter */
	private static void addDataCenter() {
		boolean isUnique = true;
		if (datacenters.isEmpty()) {
			datacenters.add(new DataCenter(tempDCid));
		} else {
			for (DataCenter dc : datacenters) {
				if (tempDCid == dc.getID()) {
					isUnique = false;
					break;
				}
			}
			if (isUnique) {
				datacenters.add(new DataCenter(tempDCid));
			}
		}
	}

	/** Adds the floors as a list to the datacenters */
	private static void addFloor() {
		outerloop: for (DataCenter datacenter : datacenters) {
			if (tempDCid == datacenter.getID()) {
				for (Floor floor : datacenter.getFloors()) {
					if (floor.getID() == tempFloorid) {
						break outerloop;
					}
				}
				datacenter.getFloors().add(new Floor(tempDCid, tempFloorid));
				break;
			}
		}
	}

	/** Adds the racks as a list to the relevant floors */
	private static void addRack(long start, long end) {
		outerloop: for (DataCenter datacenter : datacenters) {
			for (Floor floor : datacenter.getFloors()) {
				if (tempDCid == floor.getDCID() && tempFloorid == floor.getID()) {
					for (Rack rack : floor.getRacks()) {
						if (rack.getID() == tempRackid) {
							break outerloop;
						}
					}
					floor.getRacks().add(
							new Rack(tempDCid, tempFloorid, tempRackid,
									tempRackName, start, end));
					break outerloop;
				}
			}
		}
	}

	/** Adds the hosts as a list to the relevant racks */
	private static void addHost(long start, long end) {
		for (DataCenter datacenter : datacenters) {
			for (Floor floor : datacenter.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					if (tempDCid == datacenter.getID()
							&& tempRackid == rack.getID()
							&& tempFloorid == floor.getID()) {
						rack.getHosts().add(
								new Host(tempDCid, tempFloorid, tempRackid,
										tempHostid, tempHostName, start, end));
						System.out.println(tempHostid);
					}
				}
			}
		}
	}

	public static String serverAndRackCreation(long start, long end) {
		String output = "";
		start(start, end);
		for (Floor floor : datacenters.get(0).getFloors()) {
			for (Rack rack : floor.getRacks()) {
				output += ("\t<div class=\"outerRack\" draggable=\"false\">\n");
				output += ("\t\t<div class=\"innerRack\" draggable=\"false\" id=\""
						+ rack.getID() + "\" ondrop=\"drop(event, this.id)\" ondragover=\"allowDrop(event)\">\n");
				for (Host host : rack.getHosts()) {
					output += ("\t\t\t<img name=\"server" + host.getID()
							+ "\" onClick=\"displayServerInfo(this, "
							+ (host.getID() - 1)
							+ ")\" src=\"server.png\" id=\"h"
							+ (host.getID() - 1) + "\" draggable=\"true\" ondragstart=\"drag(event, this.id)\" />\n");
				}
				output += ("\t\t</div>\n");
				output += ("<div class='addServer'>\n\t\t<img src='addserver.png'></div><div class='rackInfo' id='rack"
						+ rack.getID()
						+ "'><p id=\"rn"
						+ rack.getID()
						+ "\">Rack Name:"
						+ rack.getName()
						+ "</p><p id=\"ra"
						+ rack.getID()
						+ "\">Average:"
						+ rack.getAverageFigure()
						+ "</p><p id =\"rm"
						+ rack.getID() + "\">Maximum:" + rack.getMaxFigure() + "</p></div>");
				output += ("\t</div>\n");
			}
		}
		reset();
		return output;
	}

	public static void addVariable() {
		test++;
	}

	public static String hostRackToString(long start, long end) {
		String hostrackID = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					for (Host host : rack.getHosts()) {
						hostrackID += "" + host.getHostRackID() + ",";
					}
				}
			}
		}
		reset();
		return hostrackID.substring(0, hostrackID.length() - 1);
	}

	public static String hostToString(long start, long end) {
		String hosts = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					for (Host host : rack.getHosts()) {
						hosts += "" + host.getID() + ",";
					}
				}
			}
		}
		reset();
		return hosts.substring(0, hosts.length() - 1);
	}

	public static String hostAverage(long start, long end) {
		String hosts = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					for (Host host : rack.getHosts()) {
						hosts += "" + host.getAverageFigure() + ",";
					}
				}
			}
		}
		reset();
		return hosts.substring(0, hosts.length() - 1);
	}

	public static String hostMax(long start, long end) {
		String hosts = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					for (Host host : rack.getHosts()) {
						hosts += "" + host.getMaxFigure() + ",";
					}
				}
			}
		}
		reset();
		return hosts.substring(0, hosts.length() - 1);
	}

	public static String hostName(long start, long end) {
		String hosts = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					for (Host host : rack.getHosts()) {
						hosts += "\"" + host.getName() + "\",";
					}
				}
			}
		}
		reset();
		return hosts.substring(0, hosts.length() - 1);
	}

	public static String rackToString(long start, long end) {
		String racks = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					racks += "" + rack.getID() + ",";
				}
			}
		}
		reset();
		return racks.substring(0, racks.length() - 1);
	}

	public static String rackAverage(long start, long end) {
		String racks = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					racks += "" + rack.getAverageFigure() + ",";
				}
			}
		}
		reset();
		return racks.substring(0, racks.length() - 1);
	}

	public static String rackMax(long start, long end) {
		String racks = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					racks += "" + rack.getMaxFigure() + ",";
				}
			}
		}
		reset();
		return racks.substring(0, racks.length() - 1);
	}

	public static String rackName(long start, long end) {
		String racks = "";
		start(start, end);
		for (DataCenter dc : datacenters) {
			for (Floor floor : dc.getFloors()) {
				for (Rack rack : floor.getRacks()) {
					racks += "\"" + rack.getName() + "\",";
				}
			}
		}
		reset();
		return racks.substring(0, racks.length() - 1);
	}

	public static void reset() {
		datacenters = new ArrayList<DataCenter>();
	}

	// public static void main(String[] args){
	public static void start(long start, long end) {
		reset();
		ConnectionResults connection = new ConnectionResults(1, 1); // We know
																	// its DC 1
																	// and floor
																	// 1
		// String result =
		// "{\"hostExtended\":[{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"1\",\"rackName\":\"R1\",\"rackDescription\":\"R1\",\"hostId\":\"1\",\"hostName\":\"Host1\",\"hostDescription\":\"Host1\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"},{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"1\",\"rackName\":\"R1\",\"rackDescription\":\"R1\",\"hostId\":\"2\",\"hostName\":\"Host2\",\"hostDescription\":\"Host 2\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"},{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"1\",\"rackName\":\"R1\",\"rackDescription\":\"R1\",\"hostId\":\"3\",\"hostName\":\"Host3\",\"hostDescription\":\"Host 3\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"},{\"datacenterId\":\"1\",\"datacenterName\":\"D1\",\"datacenterDescription\":\"D1\",\"floorId\":\"1\",\"floorName\":\"F1\",\"floorDescription\":\"F1\",\"rackId\":\"2\",\"rackName\":\"R2\",\"rackDescription\":\"Rack 2\",\"hostId\":\"4\",\"hostName\":\"Host4\",\"hostDescription\":\"Host 4\",\"modelGroupId\":\"1\",\"modelGroupName\":\"PMG-Dell-1920\",\"hostType\":\"SERVER\",\"IPAddress\":\"127.0.0.1\",\"processorCount\":\"1\",\"VMCount\":\"0\"}]}";
		String result = connection.getHosts();
		JSONArray test2 = JSONParser.parseJson(result, "hostExtended");
		int number_of_hosts = test2.length();
		System.out.println(number_of_hosts);
		for (int x = 0; x < number_of_hosts; x++) {
			json_object_holder = test2.getJSONObject(x);
			tempDCid = Integer.parseInt((String) json_object_holder
					.get("datacenterId"));
			tempFloorid = Integer.parseInt((String) json_object_holder
					.get("floorId"));
			tempRackid = Integer.parseInt((String) json_object_holder
					.get("rackId"));
			tempHostid = Integer.parseInt((String) json_object_holder
					.get("hostId"));
			tempHostName = (String) json_object_holder.get("hostName");
			tempRackName = (String) json_object_holder.get("rackName");
			addDataCenter();
			addFloor();
			addRack(start, end);
			addHost(start, end);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		out.println("</head>");
		// Retrieve the dates needed to add to the hosts
		//String startDateString = request.getParameter("start");
		//String endDateString = request.getParameter("end");
		//String[] date = startDateString.split("-");
		// This converts the form date to an epoch time
		//start = new DateParser(Integer.parseInt(date[2]),
		//		Integer.parseInt(date[1]), Integer.parseInt(date[0])).parse();
		//date = endDateString.split("-");
		//end = new DateParser(Integer.parseInt(date[2]),
		//		Integer.parseInt(date[1]), Integer.parseInt(date[0])).parse();
	}

	public static void main(String[] args) {
		//start(start, end);
		//System.out.println(rackToString());
	}
}