package hardware;

public class Host implements Structures {
<<<<<<< HEAD
=======
    
>>>>>>> 3de10dcc58d4bb99cb0df574a79cbca3bcb531dc
	// Left these as public but we can change to private and have get ids
	// instead?
	public int dc_id;
	public int floor_id;
	public int rack_id;
	public int host_id;
<<<<<<< HEAD

=======
    
>>>>>>> 3de10dcc58d4bb99cb0df574a79cbca3bcb531dc
	public Host() {
		dc_id = 0;
		floor_id = 0;
		rack_id = 0;
		host_id = 0;
<<<<<<< HEAD
	}

	public Host(int dc_id, int floor_id, int rack_id, int host_id) {
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
		this.host_id = host_id;
	}

	public double getMax() {
		return 0;
	}

	public double getAverage() {
		return 0;
	}

	public int getID() {
		return host_id;
	}
	
	public int getFloor(){
		return floor_id;
		
	}
	
	public int getRack(){
		return rack_id;
		
	}


	@Override
	public double getMax(Object O) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setID() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		String to_string = "Host ID: " + host_id;
		return to_string;
	}
=======
	}
    
	public Host(int dc_id, int floor_id, int rack_id, int host_id) {
		this.dc_id = dc_id;
		this.floor_id = floor_id;
		this.rack_id = rack_id;
		this.host_id = host_id;
	}
    
	public double getMax() {
		return 0;
	}
    
	public double getAverage() {
		return 0;
	}
    
	public int getID() {
		return host_id;
	}
    
	@Override
	public double getMax(Object O) {
		// TODO Auto-generated method stub
		return 0;
	}
    
	@Override
	public int setID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString(){
		String to_string = "Host ID: " + host_id;
		return to_string;
	}
	
>>>>>>> 3de10dcc58d4bb99cb0df574a79cbca3bcb531dc
}