package Hardware;

public class Host2 implements Structures{
	static private int id=0;
	private int uniqueID=0;
	private double average;
	private double max;
	
	public Host2(){
		id++;
		this.uniqueID=id;
		this.max=-1;
		this.average=-1;
	}
	
	public Host2(double max, double average){
		id++;
		this.uniqueID=id;
		this.max=max;
		this.average=average;
	}
	
	public double getMax(){
		return max;
	}

	public double getAverage(){
		return average;
	}

	public int getID(){
		return uniqueID;
	}

	public void resetCounter(){
		id=0;
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
}
