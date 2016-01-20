package lb.edu.aub.cmps.grad.output;

import lb.edu.aub.cmps.grad.classes.Day;
import lb.edu.aub.cmps.grad.classes.TimeSlot;

public class GetTimeSlots {
	public static TimeSlot[] GetMWF(){
		Day[] mwf = { Day.M, Day.W, Day.F };
		TimeSlot[] slots = new TimeSlot[39];
		int i = 0;
		for (Day d : mwf) {
			TimeSlot slot = new TimeSlot();
			slot.setDay(d);
			slot.setStart("700");
			slot.setEnd("750");
			slots[i] = slot;
			i++;
			int count = 1;
			while (count <= 12) {
				slot = slot.nextTimeSlot();
				slots[i] = slot;
				i++;
				count++;
			}
		}
		return slots;
	}
	public static TimeSlot[] GetTR(){
		Day[] tr = { Day.T, Day.R};
		TimeSlot[] slots = new TimeSlot[16];
		int i = 0;
		for (Day d : tr) {
			TimeSlot slot = new TimeSlot();
			slot.setDay(d);
			slot.setStart("800");
			slot.setEnd("930");
			slots[i] = slot;
			i++;
			int count = 1;
			while (count <= 7) {
				slot = slot.nextTimeSlot();
				slots[i] = slot;
				i++;
				count++;
			}
		}
		return slots;
	}

}
