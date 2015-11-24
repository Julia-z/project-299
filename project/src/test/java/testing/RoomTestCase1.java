package testing;

import static org.junit.Assert.*;

import java.util.HashSet;

import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.TimeSlot;

import org.junit.Test;

public class RoomTestCase1 {

	@Test
	public void test() {
		//when Room is reserved at same exact times should not be available
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1700");
		times[0].setEnd("1750");
		times[1] = new TimeSlot();
		times[1].setDay(Day.W);
		times[1].setStart("1700");
		times[1].setEnd("1750");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1700");
		times[2].setEnd("1750");
		Room myRoom = new Room();
		myRoom.setId(1);
		myRoom.setNumber("322");
		myRoom.setRoom_capacity(15);
		myRoom.setBuilding_id(3);
		myRoom.initializeReserved();
		myRoom.reserveRoom(times);
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
		assertEquals(false, myRoom.is_available(times2));
	}

}
