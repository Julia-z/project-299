package lb.edu.aub.cmps.algorithm;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Room;

public class Runner {

	public static void main(String[] args) throws SecurityException, IOException {

		IScheduler s = new ByTimeScheduler();
		s.schedule();
		
		//System.out.println("The classes that are not scheduled are: " + notSched.keySet().size());
		for(Department d: s.getScheduled().keySet()){
			Set<Class> classes = s.getScheduled().get(d);
			for(Class c: classes){
				System.out.printf("%-4d in room: %-14s at time: %-30s\n", c.getClass_id(), c.getGiven_room().getNumber(), c.getGiven_time());
			}
		}
		System.out.println("__________________________________________________________________________");
		//Yasmin check this
		
		//for the rooms
		Map<Integer, Room > id_room =s.getIdRoomMap(); 
		for(Integer i: id_room.keySet()){
			Room r = id_room.get(i);
			System.out.printf("%-14s: Reserved at %-30s\n", r.getNumber(), r.getReserved());
		}
	}
}
