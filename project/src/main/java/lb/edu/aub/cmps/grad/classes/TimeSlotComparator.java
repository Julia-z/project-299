package lb.edu.aub.cmps.grad.classes;

import java.util.Comparator;

public class TimeSlotComparator implements Comparator<TimeSlot>{

	public int compare(TimeSlot arg0, TimeSlot arg1) {
		int diff= arg0.compareTo(arg1);
		if (diff != 0) return diff;
		else return -1;
	//	return arg0.compareTo(arg1);
	}	
}
