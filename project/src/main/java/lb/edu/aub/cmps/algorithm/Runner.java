package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;

public class Runner {

	public static void main(String[] args){
		SetUp setup = new SetUp();
		
		//get the requests of all the departments
		HashMap<Integer, Set<Class>> allrequests = setup.getDep_classes_map();
		
		//schedule all the classes
		ScheduleAllClasses sched = new ScheduleAllClasses(allrequests);
		
		//here is the main part that schedule all the requested classes
		Set<Class> not_scheduled = sched.schedule();
		if(not_scheduled == null) System.out.println("ALL THE CLASSES HAVE BEEN SUCCESSFULLY SCHEDULED");
		
	}
}
