package hardware;
import java.util.ArrayList;
import java.util.List;

public class DataCenter implements Structures{
	List<Floor> dc_floors = new ArrayList<Floor>();
	private int dc_id = 1;
	
	public DataCenter() {
		this.dc_id++;		
		this.dc_floors = null;
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
	

}
