package hardware;

import java.util.ArrayList;
import java.util.List;


/**This class represents the*/
public class DataCenter {
	private List<Floor> dc_floors = new ArrayList<Floor>();
	private int dc_id;
	
	public DataCenter() {
		dc_id = 0;;		
		dc_floors = null;
	}
	
	public DataCenter(int dc_id) {
		this.dc_id = dc_id;		
		
	}
	
	/**Method to add existing floors that are relevant to the DC object **/
	
	public void addFloor(Floor r){
		dc_floors.add(r);
	}
	
	/**Method to add remove a floors that is part of the list associated with the DC object **/
	public void removeFloor(Floor r){
		dc_floors.remove(r);
	}

	/** Return the data center's id publicly **/
	public int getID(){
		return dc_id;
	}
	
	
	/** Return the List of Floors **/
	public List<Floor> getFloors(){
		return dc_floors;
	}

}
