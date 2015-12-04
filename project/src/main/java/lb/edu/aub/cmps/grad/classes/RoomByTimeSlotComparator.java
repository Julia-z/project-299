package lb.edu.aub.cmps.grad.classes;
import java.util.Comparator;

public class RoomByTimeSlotComparator implements Comparator<TimeSlot> {

	public int compare(TimeSlot t1, TimeSlot t2) {
		return t1.compareTo(t2);
	}

	
}
