package lb.edu.aub.cmps.grad;

import static org.junit.Assert.*;

import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;

import org.junit.Test;

public class ProfTestCase1 {

	@Test
	public void test() {
		//Test when a time slot requested is exactly equal to a time slot the professor is already unavailable
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1200");
		times[0].setEnd("1250");
		times[1] = new TimeSlot();
		times[1].setDay(Day.W);
		times[1].setStart("1200");
		times[1].setEnd("1250");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1200");
		times[2].setEnd("1250");
		TimeSlot[] times2 = new TimeSlot[3];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("0900");
		times2[0].setEnd("0950");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.W);
		times2[1].setStart("0900");
		times2[1].setEnd("0950");
		times2[2] = new TimeSlot();
		times2[2].setDay(Day.F);
		times2[2].setStart("0900");
		times2[2].setEnd("0950");
		Professor myProfessor = new Professor();
		myProfessor.initializeUnavailable();
		Time profTime = new Time(times);
		Time profTime2 = new Time(times2);
		myProfessor.addUnavailable(profTime);
		myProfessor.addUnavailable(profTime2);
		TimeSlot[] times3 = new TimeSlot[1];
		times3[0] = new TimeSlot();
		times3[0].setDay(Day.M);
		times3[0].setStart("0900");
		times3[0].setEnd("0950");
		Time myTime = new Time(times3);
		assertEquals(false, myProfessor.isAvailable(myTime));
	}

}
