package hardware;

import java.util.ArrayList;
import java.util.List;

public class Rack implements Structures{
	List<Host> rack_hosts = new ArrayList<Host>();
	private static int rack_id = 1;
	
	public Rack(int id){
		this.rack_id=id;		
		this.rack_hosts = getHosts();
	}
	
	
	public List getHosts(){
		Host one = new Host();
		rack_hosts.add(one);
		Host two = new Host();
		rack_hosts.add(two);
		Host three = new Host();
		rack_hosts.add(three);
		Host four = new Host();
		rack_hosts.add(four);
		Host five = new Host();
		rack_hosts.add(five);
		
		
		return rack_hosts;
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
