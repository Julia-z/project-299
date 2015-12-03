package lb.edu.aub.cmps.grad;

import java.io.IOException;
import java.util.Set;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;

public class App {

	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		//Map<Class, String> not = 
				s.schedule();
		System.out.println("__________________________________________________________________________");
		for(Department d: s.getScheduled().keySet()){
			Set<Class> classes = s.getScheduled().get(d);
			for(Class c: classes){
				String met = (c.getIsMet())? "Request Met": "Request not met";
				if(!c.getIsMet()){
					System.out.printf("%-4d %-10s %-10s in room: %-14s at time: %-30s\n",
							c.getClass_id(), 
							c.getCourse_name(),
							"("+met+")",
							c.getGivenRoom().getNumber(),
							c.getGivenTime());
				}
			}
		}
		System.out.println("__________________________________________________________________________");
		System.out.println("__________________________________________________________________________");

	}
	

}
