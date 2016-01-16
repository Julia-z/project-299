package lb.edu.aub.cmps.grad;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.grad.algorithm.EnhancedScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.PrintTimeClassInRoomVisitor;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.RoomVisitor;
import lb.edu.aub.cmps.grad.output.ExportSectionsToExcel;
import lb.edu.aub.cmps.grad.output.GenerateStatisticsInfo;

public class App {

	// test
	public static void main(String[] args) throws SecurityException,
			IOException {

		System.out.println("starting...");
		Scheduler s = new EnhancedScheduler();
		System.out.println("scheduler created...");
		// Map<Class, String> not = s.schedule();
		System.out.println("all classes scheduled...");
		Map<Department, Set<Course>> scheduled = s.getDepCoursesMap();

		System.out.println("the map is got");
		ExportSectionsToExcel e = new ExportSectionsToExcel();
		e.export(scheduled);
		System.out.println("exported to excel");
		// e.generateInfo(not);

		// update the database
		/*
		 * ClassVisitor visitor = new InsertClassToDBVisitor(); Set<Class>
		 * classes = new HashSet<Class>(); Map<Department, Set<Class>>
		 * dep_classes = s.getScheduled(); for (Department d :
		 * dep_classes.keySet()) { classes.addAll(dep_classes.get(d)); } for
		 * (Class cl : classes) { cl.accept(visitor); }
		 */
		// generate room info
		RoomVisitor roomvisitor = new PrintTimeClassInRoomVisitor();
		Map<Integer, Room> id_room = s.getIdRoomMap();
		for (Integer i : id_room.keySet()) {
			Room r = id_room.get(i);
			r.accept(roomvisitor);
		}

		// statistics
		Map<Department, Double> statByDep = s.getStatisticsByDepartment();
		double stat = s.getOverallStatistics();
		GenerateStatisticsInfo statistics = new GenerateStatisticsInfo();
		statistics.generate(statByDep, stat);
	}

}
