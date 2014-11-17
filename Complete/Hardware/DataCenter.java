package Hardware;

import java.util.ArrayList;
import java.util.List;

public class DataCenter implements Structures{
	public List<Floor> dc_floors = new ArrayList<Floor>();
	public int dc_id;
	
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
	
	@Override
	public double getMax(Object O) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAverage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
//	public String toString(){
//		String value = "DataCenter ID: " + dc_id + " Floors: " + dc_floors.iterator().next().toString() + " Racks: " + dc_floors.iterator().next().showRacks();
//		return value;
//		
//	}

}
