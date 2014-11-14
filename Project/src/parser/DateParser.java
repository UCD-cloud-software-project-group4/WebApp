package parser;
import java.util.Calendar;

public class DateParser {
	public static Calendar cal;
    
	//Months start from 0, i.e. 0 is January, 11 is December
	public DateParser(int year, int month, int day) {
		this.cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, (year));
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
	}
	/**Returns the date in milliseconds*/
	public long parse(){
		return cal.getTimeInMillis();
	}
    
	public static void main(String[] args){
		System.out.println(new DateParser(1970, 2, 1).parse());
	}
    
}