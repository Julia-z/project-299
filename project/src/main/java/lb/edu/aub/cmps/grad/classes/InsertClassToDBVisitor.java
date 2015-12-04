package lb.edu.aub.cmps.grad.classes;

import lb.edu.aub.cmps.grad.services.ClassService;

/**
 * Updates the database with the granted room and time
 * 
 * @author Bilal Abi Farraj
 */
public class InsertClassToDBVisitor implements ClassVisitor {

	/**
	 * @param c
	 *            given the Class to be updated in the database with the given
	 *            room and time
	 */
	public void visit(Class c) {
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
