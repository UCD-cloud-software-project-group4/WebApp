package Hardware;

import java.util.ArrayList;
import java.util.List;

public class Floor implements Structures{
	List<Rack> floor_racks = new ArrayList<Rack>();
	private int floor_id = 1;
	
	public Floor(){
		this.floor_id++;	
		this.floor_racks = null;
	}
	
	public void addRack(Rack r){
		floor_racks.add(r);
	}
	
	public void removeHost(Rack r){
		floor_racks.remove(r);
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
	
}
