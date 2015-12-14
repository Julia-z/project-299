package lb.edu.aub.cmps.grad.classes;
import java.util.Comparator;

/**
 * compare two rooms given thier time slots
 * needed to ensure the order in the excel file
 * @author Julia
 *
 */
public class RoomByTimeSlotComparator implements Comparator<TimeSlot> {

	public int compare(TimeSlot t1, TimeSlot t2) {
		return t1.compareTo(t2);
	}

	
}
