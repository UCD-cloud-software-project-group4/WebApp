package Hardware;

import java.util.ArrayList;
import java.util.List;

public class Rack implements Structures{
	List<Host> rack_hosts = new ArrayList<Host>();
	private int rack_id = 1;
	
	public Rack(){
		this.rack_id++;		
		this.rack_hosts = null;
	}
	
	public void addHost(Host h){
		rack_hosts.add(h);
	}
	
	public void removeHost(Host h){
		rack_hosts.remove(h);
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
