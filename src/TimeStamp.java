/**
 *
 * @author Arran Willis - A5
 */
/**
 * @file TimeStamp.java
 * @author Arran Willis 
 * @class TimeStamp
 * @date 1/04/2013
 * @brief Class to generate a time stamp
 *
 * This class will take in the system time
 * and date, and the return both as one
 * single string
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


class TimeStamp {
	/**
	 * Method get the system date, set the
	 * format and then return it as a string
	 * 
	 * @return String date
	 */
	public String GetDate (){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	/**
	 * Method to get the system time, set the format
	 * and then return it as a string
	 * 
	 * @return String time
	 */
	public String GetTime (){
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		Date time = new Date();
		return dateFormat.format(time);
	}
	/**
	 * Method to get the time and date from
	 * their methods and print them together 
	 * as a single string
	 * 
	 * @return timeStamp
	 */
	public String GetTimeStamp() {
		boolean test = false;
		final String date = GetDate();
		final String time = GetTime();		
		final String timeStamp = (date + " " + time);
		if (test == true){
			System.out.println(timeStamp);
		}
		return timeStamp;
	}
}