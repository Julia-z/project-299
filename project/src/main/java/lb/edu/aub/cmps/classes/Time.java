package lb.edu.aub.cmps.classes;

import java.util.Arrays;

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
				if (this.timeSlots[i].getDay() == time2.timeSlots[j].getDay()) {
					if (Integer.parseInt(this.timeSlots[i].getStart()) == Integer
							.parseInt(time2.timeSlots[j].getStart())) {
						conflict = true;
					} else if (Integer.parseInt(this.timeSlots[i].getStart()) > Integer
							.parseInt(time2.timeSlots[j].getStart())
							&& (Integer.parseInt(this.timeSlots[i].getStart()) <= Integer
									.parseInt(time2.timeSlots[j].getEnd()))) {
						conflict = true;
					} else if (Integer.parseInt(this.timeSlots[i].getStart()) < Integer
							.parseInt(time2.timeSlots[j].getStart())
							&& (Integer.parseInt(this.timeSlots[i].getEnd()) >= Integer
									.parseInt(time2.timeSlots[j].getStart()))) {

						conflict = true;
					}
				}
			}

		}
		return conflict;
	}
}