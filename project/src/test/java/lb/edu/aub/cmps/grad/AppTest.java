package lb.edu.aub.cmps.grad;

import static org.junit.Assert.assertEquals;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import lb.edu.aub.cmps.classes.Day;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
	
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testProf1() {
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
    public void testProf2() {
		//Test when time slot requested is equal to start time of slot of which professor is not available
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
		times2[0].setStart("1000");
		times2[0].setEnd("1100");
		Time myTime = new Time(times2);
		assertEquals(false, myProfessor.isAvailable(myTime));
	}
    public void testProf3() {
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
    public void testProf4() {
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
    public void testRoom1() {
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
		myRoom.reserveRoom(times, "CMPS200");
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
    public void testRoom2() {
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
		myRoom.reserveRoom(times, "CMPS-212");
		myRoom.reserveRoom(times2, "CMPS-213");
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
    public void testRoom3() {
		//Checking if available in middle of two reserved times
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
		times2[0].setStart("1100");
		times2[0].setEnd("1150");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.W);
		times2[1].setStart("1100");
		times2[1].setEnd("1150");
		times2[2] = new TimeSlot();
		times2[2].setDay(Day.F);
		times2[2].setStart("1100");
		times2[2].setEnd("1150");
		Room myRoom = new Room();
		myRoom.setId(1);
		myRoom.setNumber("322");
		myRoom.setRoom_capacity(15);
		myRoom.setBuilding_id(3);
		myRoom.initializeReserved();
		myRoom.reserveRoom(times, "CMPS-253");
		myRoom.reserveRoom(times2, "CMPS-200");
		TimeSlot[] times3 = new TimeSlot[2];
		times3[0] = new TimeSlot();
		times3[0].setDay(Day.M);
		times3[0].setStart("1030");
		times3[0].setEnd("1145");
		times3[1] = new TimeSlot();
		times3[1].setDay(Day.W);
		times3[1].setStart("1030");
		times3[1].setEnd("1145");
		assertEquals(false, myRoom.is_available(times3));
	}
    public void testTime1() {
		//test if time conflicts when time slots are on same day but different times
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1700");
		times[0].setEnd("1750");
		times[1] = new TimeSlot();
		times[1].setDay(Day.T);
		times[1].setStart("1700");
		times[1].setEnd("1750");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1700");
		times[2].setEnd("1750");
		Time checking1 = new Time(times);

		TimeSlot[] times2 = new TimeSlot[3];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("0800");
		times2[0].setEnd("0850");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.W);
		times2[1].setStart("0800");
		times2[1].setEnd("0850");
		times2[2] = new TimeSlot();
		times2[2].setDay(Day.F);
		times2[2].setStart("0800");
		times2[2].setEnd("0850");
		Time checking2 = new Time(times2);
		assertEquals(false, checking1.conflicts(checking2));
	}
    public void testTime2() {
		//exactly equal time slots being compared
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1700");
		times[0].setEnd("1750");
		times[1] = new TimeSlot();
		times[1].setDay(Day.T);
		times[1].setStart("1700");
		times[1].setEnd("1750");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1700");
		times[2].setEnd("1750");
		Time checking1 = new Time(times);

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
		Time checking2 = new Time(times2);
		assertEquals(true, checking1.conflicts(checking2));
	}
    public void testTime3() {
		//Conflict between a slot thats in the middle of a two hour slot
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1200");
		times[0].setEnd("1400");
		times[1] = new TimeSlot();
		times[1].setDay(Day.T);
		times[1].setStart("1200");
		times[1].setEnd("1400");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1200");
		times[2].setEnd("1400");
		Time checking1 = new Time(times);

		TimeSlot[] times2 = new TimeSlot[3];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("1300");
		times2[0].setEnd("1350");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.W);
		times2[1].setStart("1300");
		times2[1].setEnd("1350");
		times2[2] = new TimeSlot();
		times2[2].setDay(Day.F);
		times2[2].setStart("1300");
		times2[2].setEnd("1350");
		Time checking2 = new Time(times2);
		assertEquals(true, checking1.conflicts(checking2));
	}
    public void testTime4() {
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
    public void testTime5() {
		//different lengths of Time with only one day as conflict
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

		TimeSlot[] times2 = new TimeSlot[2];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("1700");
		times2[0].setEnd("1750");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.R);
		times2[1].setStart("1700");
		times2[1].setEnd("1750");
		Time checking2 = new Time(times2);
		assertEquals(true, checking1.conflicts(checking2));
	}
    public void testTime6() {
		//One time is all scheduled in one day and other has one time slot that is in between two scheduled times
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1700");
		times[0].setEnd("1750");
		times[1] = new TimeSlot();
		times[1].setDay(Day.M);
		times[1].setStart("1800");
		times[1].setEnd("1850");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("1900");
		times[2].setEnd("1950");
		Time checking1 = new Time(times);

		TimeSlot[] times2 = new TimeSlot[2];
		times2[0] = new TimeSlot();
		times2[0].setDay(Day.M);
		times2[0].setStart("1730");
		times2[0].setEnd("1850");
		times2[1] = new TimeSlot();
		times2[1].setDay(Day.R);
		times2[1].setStart("1700");
		times2[1].setEnd("1750");
		Time checking2 = new Time(times2);
		assertEquals(true, checking1.conflicts(checking2));
	}



}
