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
		this.floor_racks = floor_racks;
		this.dc_id = dc_id;
		this.floor_id = floor_id;
	}
	
	public void addRack(Rack r){
		floor_racks.add(r);
	}
	
	public void removeRack(Rack r){
		floor_racks.remove(r);
	}
	
	public List<Rack> getRacks(){
		return floor_racks;
	}

	public int getID() {
		return floor_id;
	}
	
	public int getDCID(){
		return dc_id;
	}
	
	
	
}
