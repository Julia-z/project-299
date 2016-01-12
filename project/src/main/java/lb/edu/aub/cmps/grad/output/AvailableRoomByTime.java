package lb.edu.aub.cmps.grad.output;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Day;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.services.RoomService;

public class AvailableRoomByTime {

	public static void main(String[] args) {
		RoomService rs = new RoomService();
		HashSet<Room> allRooms = (HashSet<Room>) rs.getAllRooms();

		HashMap<TimeSlot, Set<Room>> availableRooms = new HashMap<TimeSlot, Set<Room>>();

		TimeSlot mon = new TimeSlot();
		mon.setDay(Day.M); // I tried 700 750
		mon.setStart("700");
		mon.setEnd("800");

		TimeSlot tue = new TimeSlot();
		tue.setDay(Day.T);
		tue.setStart("700");
		tue.setEnd("815");

		TimeSlot wed = new TimeSlot();
		wed.setDay(Day.W);
		wed.setStart("700");
		wed.setEnd("800");

		TimeSlot thurs = new TimeSlot();
		thurs.setDay(Day.R);
		thurs.setStart("700");
		thurs.setEnd("815");

		TimeSlot fri = new TimeSlot();
		fri.setDay(Day.F);
		fri.setStart("700");
		fri.setEnd("800");

		TimeSlot sat = new TimeSlot();
		sat.setDay(Day.S);
		sat.setStart("700");
		sat.setEnd("750");

		TimeSlot[] days = { mon, wed, fri }; // Will be done on TR as well, but
												// its generating a null pointer
												// exception i don't know why

		for (int i = 0; i < 3; i++) {
			int count = 0;
			TimeSlot d = days[i];
			while (count < 12) { //12 time slots per day
				HashSet<Room> available = new HashSet<Room>();

				for (Room room : allRooms) {
					room.initializeReserved();
					if (room.getReserved() != null) {
						if (!room.getReserved().containsKey(d)) {
							available.add(room);
						}
					}
				}
				System.out.println(d.toString() + " --> " + available.size());
				availableRooms.put(d, available);
				d = d.nextTimeSlot();
				count++;
			}
		}

	}
}
