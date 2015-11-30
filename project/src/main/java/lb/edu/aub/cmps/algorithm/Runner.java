package lb.edu.aub.cmps.algorithm;
import java.io.IOException;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;

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
	}
}
