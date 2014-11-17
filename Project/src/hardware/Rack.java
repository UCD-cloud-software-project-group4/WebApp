package hardware;

import java.util.ArrayList;
import java.util.List;

public class Rack implements Structures{
	public List<Host> rack_hosts = new ArrayList<Host>();
	private int rack_id;
	public int floor_id;
	public  int dc_id;
	
	public Rack(int dc_id, int floor_id, int rack_id){
		this.dc_id=dc_id;
		this.rack_id=rack_id;
		this.floor_id = floor_id;
		this.rack_hosts = rack_hosts;
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
		return rack_id;
	}

	@Override
	public int setID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
