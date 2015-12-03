package lb.edu.aub.cmps.classes;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.services.ClassService;

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
		if (c.getIsMet()) {
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
					c.setGivenDay(day);
					c.setGivenEnd(start);
					c.setGivenStart(end);
					cs.updateLecture_Time(c);
				}
			}
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

	public static void main(String[] args) throws SecurityException,
			IOException {
		ClassVisitor visitor = new InsertClassToDBVisitor();
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		Set<Class> classes = new HashSet<Class>();
		Map<Department, Set<Class>> dep_classes = s.getScheduled();
		for (Department d : dep_classes.keySet()) {
			classes.addAll(dep_classes.get(d));
		}
		for (Class cl : classes) {
			cl.accept(visitor);
		}
	}

}
