package hardware;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Floor implements Structures{
	public List<Rack> floor_racks = new ArrayList<Rack>();
	public int dc_id;
	public int floor_id;
	
	public Floor(){
		floor_racks = null;
		dc_id = 0;
		floor_id = 0;
	}
	
<<<<<<< HEAD
	public Floor(int dc_id, int floor_id){
=======
	public Floor(int dc_id, int floor_id, List<Rack> floor_racks){
>>>>>>> 3de10dcc58d4bb99cb0df574a79cbca3bcb531dc
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
	
	public Rack getRack(Rack id){
		return null;
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
		return floor_id;
	}
    
	@Override
	public int setID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
<<<<<<< HEAD
	public List<Rack> getRacks(){
		
		return floor_racks;
	}
	
//	public String showRacks(){
//		Iterator<Rack> i = new 
//
//		
//		String racks = floor_racks.iterator().next().toString();
//		while(floor_racks.iterator().hasNext()){
//			racks += " " + floor_racks.iterator().next().toString();
//		}
//		return racks;		
//	}
=======
    /*	public String showRacks(){
    		Iterator<Rack> i = new
    
    
    		String racks = floor_racks.iterator().next().toString();
    		while(floor_racks.iterator().hasNext()){
    			racks += " " + floor_racks.iterator().next().toString();
    		}
    		return racks;
    */	}
>>>>>>> 3de10dcc58d4bb99cb0df574a79cbca3bcb531dc
	
	public String toString(){
		String to_string = "Floor ID: " + floor_id;
		return to_string;
	}
	
<<<<<<< HEAD
}
=======
}
>>>>>>> 3de10dcc58d4bb99cb0df574a79cbca3bcb531dc
