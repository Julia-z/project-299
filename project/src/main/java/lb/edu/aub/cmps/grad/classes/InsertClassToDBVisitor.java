package lb.edu.aub.cmps.grad.classes;

import java.io.IOException;
import java.util.logging.Logger;

import lb.edu.aub.cmps.grad.services.ClassService;

/**
 * implementing the visitor pattern
 * inserts the class/lecture with its granted room and time to the database
 * @author Julia
 * @author Bilal Abi Farraj
 */
public class InsertClassToDBVisitor implements ClassVisitor {

	/**
	 * @param c
	 *            given the Class to be updated in the database with the given
	 *            room and time
	 * @throws IOException for the logger
	 * @throws SecurityException for the logger 
	 */

	public void visit(Class c) throws SecurityException, IOException {
		MyLogger loggerWrapper = MyLogger.getInstance();
		Logger log = loggerWrapper.getLogger();

		if (!c.getIsMet()) {
			ClassService cs = new ClassService();
			cs.updateLecture_Classroom(c);

			Time t = c.getGivenTime();
			TimeSlot[] ts = t.getTimeSlots();

			for (int i = 0; i < ts.length; i++) {
				if (ts[i] != null) {
					Day d = ts[i].getDay();
					int day = dayToInt(d);
					String start = ts[i].getStart();
					String end = ts[i].getEnd();
					c.setReq_day(day);
					c.setGivenDay(day);
					c.setGivenEnd(end);
					c.setGivenStart(start);
					cs.updateLecture_Time(c);
					
					log.info("----------------------------------\nUpdating "
							+ c.getCourse_name() + ": ID: " + c.getClass_id()
							+ ", Section: " + c.getSection_number() + " DAY: "
							+ day + ", START: " + start + ", END: " + end
							+ ", Room: " + c.getGivenRoom().getNumber()
							+ ". \nInitially requested START: "
							+ c.getRequestedTime().getTimeSlots()[i].getStart()
							+ ", END: "
							+ c.getRequestedTime().getTimeSlots()[i].getEnd()
							+ ", Room: " + c.getRequestedRoom().getNumber());
				}
			}
			cs.updateLecture_Classroom(c);
		}
	}

	private int dayToInt(Day d) {
		int day = 0;

		if (d == Day.M) {
			day = 1;
		} else if (d == Day.T) {
			day = 2;
		} else if (d == Day.T) {
			day = 2;
		} else if (d == Day.W) {
			day = 3;
		} else if (d == Day.R) {
			day = 4;
		} else if (d == Day.F) {
			day = 5;
		} else if (d == Day.S) {
			day = 6;
		} else if (d == Day.U) {
			day = 7;
		}
		return day;
	}

}
