package lb.edu.aub.cmps.grad;

import java.io.IOException;
import java.util.Set;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;

public class App {

	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		System.out.println("__________________________________________________________________________");

		
		//System.out.println("The classes that are not scheduled are: " + notSched.keySet().size());
		for(Department d: s.getScheduled().keySet()){
			Set<Class> classes = s.getScheduled().get(d);
			for(Class c: classes){
				String met = (c.getIsMet())? "Request Met": "Request not met";
				System.out.printf("%-4d %-18sin room: %-14s at time: %-30s\n", c.getClass_id(), "("+met+")", c.getGivenRoom().getNumber(), c.getGivenTime());
			}
		}
		System.out.println("__________________________________________________________________________");

		
	}
	


}
