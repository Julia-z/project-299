package lb.edu.aub.cmps.classes;

import java.util.Arrays;

public class Time {
	private TimeSlot[] timeSlots;

	/*
	 * TESTING public static void main(String[] args) { TimeSlot[] times = new
	 * TimeSlot[3]; times[0] = new TimeSlot(); times[0].setDay(Day.M);
	 * times[0].setStart("0500"); times[0].setEnd("0600"); times[1] = new
	 * TimeSlot(); times[1].setDay(Day.T); times[1].setStart("700");
	 * times[1].setEnd("730"); times[2] = new TimeSlot();
	 * times[2].setDay(Day.F); times[2].setStart("731");
	 * times[2].setEnd("1930"); Time checking1 = new Time(times);
	 * 
	 * TimeSlot[] times2 = new TimeSlot[3]; times2[0] = new TimeSlot();
	 * times2[0].setDay(Day.M); times2[0].setStart("700");
	 * times2[0].setEnd("800"); times2[1] = new TimeSlot();
	 * times2[1].setDay(Day.W); times2[1].setStart("700");
	 * times2[1].setEnd("730"); times2[2] = new TimeSlot();
	 * times2[2].setDay(Day.F); times2[2].setStart("1531");
	 * times2[2].setEnd("1630"); Time checking2 = new Time(times2);
	 * System.out.println
	 * ("testing conflicts method "+checking1.conflicts(checking2)); }
	 */
	public Time(TimeSlot[] timeSlots) {
		this.timeSlots = timeSlots;
	}

	public TimeSlot[] getTimeSlots() {
		return timeSlots;
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
				if (this.timeSlots[i].conflicts(time2.timeSlots[j]))
					conflict = true;
			}

		}
		return conflict;
	}

	/**
	 * TODO by julia return a
	 * 
	 * @return null in case we cant shift the time anymore
	 */
	public Time nextTime() {
		TimeSlot[] ts = new TimeSlot[this.timeSlots.length];
		for (int i = 0; i < ts.length; i++) {
			if(this.timeSlots[i].nextTimeSlot() == null) return null;
			ts[i] = this.timeSlots[i].nextTimeSlot();
		}
		return new Time(ts);
	}
	
	public Time previousTime(){
		TimeSlot[] ts = new TimeSlot[this.timeSlots.length];
		for (int i = 0; i < ts.length; i++) {
			if(this.timeSlots[i].previousTimeSlot() == null) return null;
			ts[i] = this.timeSlots[i].previousTimeSlot();
		}
		return new Time(ts);
	}

	/*
	public static void main(String[] args) {
		TimeSlot t1 = new TimeSlot(Day.M, "1400", "1450");
		TimeSlot t2 = new TimeSlot(Day.W, "1400", "1450");
		TimeSlot t3 = new TimeSlot(Day.F, "1400", "1450");
		TimeSlot t4 = new TimeSlot(Day.W, "1000", "1100");
		//TimeSlot t2 = new TimeSlot(Day.F, "1400", "1450");

		TimeSlot[] t = { t1, t2, t3 };
		Time time1 = new Time(t);
		TimeSlot[] tt = { t4 };
		Time time2 = new Time(tt);
		System.out.println(time1);
		System.out.println(time1.nextTime());
	}
	*/
}
