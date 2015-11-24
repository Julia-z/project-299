package testing;

import static org.junit.Assert.*;
import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;

import org.junit.Test;

public class ProfTestCase4 {

	@Test
	public void test() {
		//Test when slot requested is same time as an unavailable time slot but different day
		TimeSlot[] times = new TimeSlot[2];
		times[0] = new TimeSlot();
		times[0].setDay(Day.T);
		times[0].setStart("1500");
		times[0].setEnd("1615");
		times[1] = new TimeSlot();
		times[1].setDay(Day.R);
		times[1].setStart("1500");
		times[1].setEnd("1615");

		Professor myProfessor = new Professor();
		myProfessor.initializeUnavailable();
		Time profTime = new Time(times);
		myProfessor.addUnavailable(profTime);
		TimeSlot[] times2 = new TimeSlot[1];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("1500");
		times2[0].setEnd("1600");
		Time myTime = new Time(times2);
		assertEquals(true, myProfessor.isAvailable(myTime));
	}

}
