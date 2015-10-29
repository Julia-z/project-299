package lb.edu.aub.cmps.classes;

import java.util.Arrays;

public class Time {
	//starting time
	private TimeSlot[] timeSlots;
	
	public Time(TimeSlot[] timeSlots){
		this.timeSlots = timeSlots;
	}

	public String toString(){
		return Arrays.toString(timeSlots);
	}
	
	public boolean conflicts(Time time2){
		/**
		 * TODO
		 */
		return false;
	}
}