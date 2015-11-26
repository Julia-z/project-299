package lb.edu.aub.cmps.grad;

import static org.junit.Assert.*;
import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.TimeSlot;

import org.junit.Test;

public class RoomTestCase2 {

	@Test
	public void test() {
		// when Room is not reserved at the time requested
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1000");
		times[0].setEnd("1050");
		times[1] = new TimeSlot();
		times[1].setDay(Day.W);
		times[1].setStart("1000");
		times[1].setEnd("1050");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1000");
		times[2].setEnd("1050");
		TimeSlot[] times2 = new TimeSlot[3];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("1700");
		times2[0].setEnd("1750");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.W);
		times2[1].setStart("1700");
		times2[1].setEnd("1750");
		times2[2] = new TimeSlot();
		times2[2].setDay(Day.F);
		times2[2].setStart("1700");
		times2[2].setEnd("1750");
		Room myRoom = new Room();
		myRoom.setId(1);
		myRoom.setNumber("322");
		myRoom.setRoom_capacity(15);
		myRoom.setBuilding_id(3);
		myRoom.initializeReserved();
		myRoom.reserveRoom(times);
		myRoom.reserveRoom(times2);
		TimeSlot[] times3 = new TimeSlot[3];
		times3[0] = new TimeSlot();
		times3[0].setDay(Day.M);
		times3[0].setStart("1100");
		times3[0].setEnd("1150");
		times3[1] = new TimeSlot();
		times3[1].setDay(Day.W);
		times3[1].setStart("1100");
		times3[1].setEnd("1150");
		times3[2] = new TimeSlot();
		times3[2].setDay(Day.F);
		times3[2].setStart("1100");
		times3[2].setEnd("1150");
		assertEquals(true, myRoom.is_available(times3));
	}

}
