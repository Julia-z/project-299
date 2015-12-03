package lb.edu.aub.cmps.grad;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.ClassVisitor;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.InsertClassToDBVisitor;
import lb.edu.aub.cmps.classes.PrintTimeClassInRoomVisitor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.RoomVisitor;
import lb.edu.aub.cmps.output.ExportSectionsToExcel;

public class App {

	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		Map<Class, String> not = s.schedule();
		Map<Department, Set<Course>> scheduled = s.getDepCoursesMap();
		ExportSectionsToExcel e = new ExportSectionsToExcel();
		e.export(scheduled);
		e.generateInfo(not);
		
		//update the database
		ClassVisitor visitor = new InsertClassToDBVisitor();
		Set<Class> classes = new HashSet<Class>();
		Map<Department, Set<Class>> dep_classes = s.getScheduled();
		for (Department d : dep_classes.keySet()) {
			classes.addAll(dep_classes.get(d));
		}
		for (Class cl : classes) {
			cl.accept(visitor);
		}
		
		//generate room info
		RoomVisitor roomvisitor = new PrintTimeClassInRoomVisitor();
		Map<Integer, Room> id_room = s.getIdRoomMap();
		for (Integer i : id_room.keySet()) {
			Room r = id_room.get(i);
			r.accept(roomvisitor);
		}

	}
	

}
