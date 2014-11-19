package Hardware;

import java.util.ArrayList;
import java.util.List;

public class DataCenter {
	private List<Floor> dc_floors = new ArrayList<Floor>();
	private int dc_id;
	
	public DataCenter() {
		dc_id = 0;;		
		dc_floors = null;
	}
	
	public DataCenter(int dc_id, List<Floor> dc_floors) {
		this.dc_id = dc_id;		
		this.dc_floors = dc_floors;
	}
	
	public void addFloor(Floor r){
		dc_floors.add(r);
	}
	
	public void removeFloor(Floor r){
		dc_floors.remove(r);
	}

	public int getID(){
		return dc_id;
	}
	
	public List<Floor> getFloors(){
		return dc_floors;
	}

}
