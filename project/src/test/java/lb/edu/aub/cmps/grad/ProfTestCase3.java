package lb.edu.aub.cmps.grad;

import static org.junit.Assert.*;
import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;

import org.junit.Test;

public class ProfTestCase3 {

	@Test
	public void test() {
		// Test when time slot requested is in the middle of a slot of which
		// professor is not available
		TimeSlot[] times = new TimeSlot[2];
		times[0] = new TimeSlot();
		times[0].setDay(Day.T);
		times[0].setStart("1100");
		times[0].setEnd("1215");
		times[1] = new TimeSlot();
		times[1].setDay(Day.R);
		times[1].setStart("1100");
		times[1].setEnd("1215");

		Professor myProfessor = new Professor();
		myProfessor.initializeUnavailable();
		Time profTime = new Time(times);
		myProfessor.addUnavailable(profTime);
		TimeSlot[] times2 = new TimeSlot[1];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.T);
		times2[0].setStart("1200");
		times2[0].setEnd("1250");
		Time myTime = new Time(times2);
		assertEquals(false, myProfessor.isAvailable(myTime));
	}

}
