package lb.edu.aub.cmps.grad.classes;

import java.util.Comparator;

public class TimeSlotComparator implements Comparator<TimeSlot>{

	public int compare(TimeSlot arg0, TimeSlot arg1) {
		return arg0.compareTo(arg1);
	}	
}
