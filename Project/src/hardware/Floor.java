package hardware;

import java.util.ArrayList;
import java.util.List;

public class Floor {
	private List<Rack> floor_racks = new ArrayList<Rack>();
	private int dc_id;
	private int floor_id;
	
	public Floor(){
		floor_racks = null;
		dc_id = 0;
		floor_id = 0;
	}
	
	public Floor(int dc_id, int floor_id){
		this.dc_id = dc_id;
		this.floor_id = floor_id;
	}
	
	/** Method to add Racks onto Floors **/
	public void addRack(Rack r){
		floor_racks.add(r);
	}
	
	/** Method to remove Racks from Floors **/
	public void removeRack(Rack r){
		floor_racks.remove(r);
	}
	
	/** Return the List of racks associated with the Floor**/
	public List<Rack> getRacks(){
		return floor_racks;
	}
	
	/** return the floor ID **/
	public int getID() {
		return floor_id;
	}
	
	/** return the datacenter ID the floor is assoicated with **/
	public int getDCID(){
		return dc_id;
	}
	
	
	
}
