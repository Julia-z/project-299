package lb.edu.aub.cmps.algorithm;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;

public class Runner {

	public static void main(String[] args) throws SecurityException, IOException {

		IScheduler s = new ByTimeScheduler();
		Map<Class, String> notSched = s.schedule();
		
		System.out.println("The classes that are not scheduled are: " + notSched.keySet().size());
		for(Department d: s.getScheduled().keySet()){
			System.out.println("Department: "+d.getName());
			Set<Class> classes = s.getScheduled().get(d);
			for(Class c: classes){
				System.out.println(c.getGiven_time().equals(c.getRequestedTime()));
			}
		}
	}
}
