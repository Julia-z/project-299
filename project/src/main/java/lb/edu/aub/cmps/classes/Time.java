package lb.edu.aub.cmps.classes;

import java.util.Arrays;

import lb.edu.aub.cmps.enums.Day;

public class Time {
	private TimeSlot[] timeSlots;

	public Time(TimeSlot[] timeSlots) {
		this.timeSlots = timeSlots;
	}
	

	public String toString() {
		return Arrays.toString(timeSlots);
	}

	/**
	 * This Method checks whether there is time conflict between this and time2
	 * 
	 * @param time2
	 * @return boolean with true if conflict, false if no conflict
	 */
	public boolean conflicts(Time time2) {
		boolean conflict = false;
		for (int i = 0; i < this.timeSlots.length; i++) {
			for (int j = 0; j < time2.timeSlots.length; j++) {
				if(this.timeSlots[i].conflicts(time2.timeSlots[j]))
					conflict = true;
			}

		}
		return conflict;
	}
	/*
	public static void main(String[] args){
		TimeSlot t1 = new TimeSlot(Day.M, "1400", "1450");
		TimeSlot t2 = new TimeSlot(Day.W, "1400", "1450");
		TimeSlot t3 = new TimeSlot(Day.F, "1400", "1450");
		TimeSlot t4 = new TimeSlot(Day.W, "1000", "1100");
		//TimeSlot t2 = new TimeSlot(Day.F, "1400", "1450");

		TimeSlot[] t = {t1, t2, t3};
		Time time1 = new Time(t);
		TimeSlot[] tt = {t4};
		Time time2 = new Time(tt);
		System.out.println(time1.conflicts(time2));
	}*/
}