package lb.edu.aub.cmps.grad;

import static org.junit.Assert.*;
import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;

import org.junit.Test;

public class TimeSlotsTestCase4 {

	@Test
	public void test() {
		//Same times but different days
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
		Time checking1 = new Time(times);

		TimeSlot[] times2 = new TimeSlot[3];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.T);
		times2[0].setStart("1700");
		times2[0].setEnd("1750");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.R);
		times2[1].setStart("1700");
		times2[1].setEnd("1750");
		times2[2] = new TimeSlot();
		times2[2].setDay(Day.S);
		times2[2].setStart("1700");
		times2[2].setEnd("1750");
		Time checking2 = new Time(times2);
		assertEquals(false, checking1.conflicts(checking2));
	}

}
