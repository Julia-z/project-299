package lb.edu.aub.cmps.grad.output;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import lb.edu.aub.cmps.grad.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Day;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.classes.TimeSlotComparator;

public class AvailableRoomsByTime {
	static Collection<Room> rooms;

	@SuppressWarnings("static-access")
	public AvailableRoomsByTime(Collection<Room> rooms) {
		this.rooms = rooms;

		// u can add anything here
	}

	public void generate() {
		// here u generate the excel file
		System.out.println("hello");
		/*
		 * for(Room r: rooms){ System.out.println(r.getNumber());
		 * System.out.println(r.getReserved()); }
		 */

		TreeMap<TimeSlot, Set<Room>> availableRooms = new TreeMap<TimeSlot, Set<Room>>(
				new TimeSlotComparator());

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

		TimeSlot[] mwf = { mon, wed, fri };
		findAvailable(mwf, availableRooms);

	}

	private void findAvailable(TimeSlot[] days,
			TreeMap<TimeSlot, Set<Room>> availableRooms) {

		int limit = 0;
		if (days[0].getDay() == Day.M) // Case of MWF
			limit = 12;
		else
			limit = 7; // TODO Case of TR isn't working

		for (int i = 0; i < 3; i++) {
			int count = 0;
			TimeSlot d = days[i];
			System.out.println(d.getDay());
			while (count < limit) { // 12 time slots per day
				HashSet<Room> available = new HashSet<Room>();

				for (Room room : rooms) {
					TimeSlot[] ts = { d };
					if (room.is_available(ts)) {
						available.add(room);
					}
				}
				System.out.println("Putting " + available.size()
						+ " available classes in " + d.toString());

				// TODO Putting rooms in days different than Monday isn't
				// working

				availableRooms.put(d, available);

				System.out.println("Size :" + availableRooms.size());
				d = d.nextTimeSlot();
				count++;
			}
		}

		Set<TimeSlot> ts = availableRooms.keySet();

		System.out.println("Size: " + ts.size());
		for (TimeSlot t : ts) {
			System.out.print(t.toString() + ": {");
			for (Room rm : availableRooms.get(t)) {
				System.out.print(rm.getNumber() + ", ");
			}
			System.out.println("}");
		}
	}

	public static void main(String[] args) throws SecurityException,
			IOException {
		System.out.println("starting...");
		Scheduler s = new ByTimeScheduler();

		System.out.println("scheduler created...");
		s.schedule();
		System.out.println("all classes scheduled...");

		AvailableRoomsByTime available_rooms = new AvailableRoomsByTime(
				s.getRooms());
		available_rooms.generate();
	}
}
