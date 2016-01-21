package lb.edu.aub.cmps.grad;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.grad.algorithm.EnhancedScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.RoomVisitor;
import lb.edu.aub.cmps.grad.classes.RoomsScheduleVisitor;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.output.AvailableRoomsByTime;
import lb.edu.aub.cmps.grad.output.ExportSectionsToExcel;
import lb.edu.aub.cmps.grad.output.GenerateRoomInfo;
import lb.edu.aub.cmps.grad.output.GenerateStatisticsInfo;
import lb.edu.aub.cmps.grad.output.GetTimeSlots;

public class App {

	// test
	public static void main(String[] args) throws SecurityException,
			IOException {

		System.out.println("starting...");
		Scheduler s = new EnhancedScheduler();
		System.out.println("scheduler created...");
		Map<lb.edu.aub.cmps.grad.classes.Class, String> not = s.schedule();
		System.out.println("all classes scheduled...");
		
		//generate department-course.xls
		Map<Department, Set<Course>> scheduled = s.getDepCoursesMap();
		ExportSectionsToExcel e = new ExportSectionsToExcel();
		e.export(scheduled);
		System.out.println("exported to excel");
		e.generateInfo(not);

		// update the database
		/*
		 * ClassVisitor visitor = new InsertClassToDBVisitor(); Set<Class>
		 * classes = new HashSet<Class>(); Map<Department, Set<Class>>
		 * dep_classes = s.getScheduled(); for (Department d :
		 * dep_classes.keySet()) { classes.addAll(dep_classes.get(d)); } for
		 * (Class cl : classes) { cl.accept(visitor); }
		 */
		
		// generate room info rooms_fixed_slots.xls
		TimeSlot[] mwf = GetTimeSlots.GetMWF();
		TimeSlot[] tr = GetTimeSlots.GetTR();		
		RoomVisitor roomvisitor = new RoomsScheduleVisitor(mwf, tr);
		GenerateRoomInfo roomInfo = new GenerateRoomInfo(roomvisitor, s.getIdRoomMap());
		roomInfo.generate();

		// statistics generate statistics.xls
		Map<Department, Double> statByDep = s.getStatisticsByDepartment();
		double stat = s.getOverallStatistics();
		GenerateStatisticsInfo statistics = new GenerateStatisticsInfo();
		statistics.generate(statByDep, stat);
		
		//availableRoomsByTime.xls
		AvailableRoomsByTime available_rooms = new AvailableRoomsByTime(
				s.getRooms());
		// available_rooms.generate();
		available_rooms.exportAvailableRoomsByTime();

	}

}
