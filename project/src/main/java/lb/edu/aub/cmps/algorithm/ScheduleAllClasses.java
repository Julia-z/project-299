package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;


public class ScheduleAllClasses {

	HashMap<Integer, Set<Class>> requests;
	double[] weights;
	
	public ScheduleAllClasses(HashMap<Integer, Set<Class>> requests){
		this.requests = requests;
		
		//normalize and get the weights
	}
	
	/**
	 * 
	 * @return set of classes that couldn't be scheduled
	 * 			NULL in case of success
	 */
	public Set<Class> schedule(){
		//sort departments by their weight
		//for number of iterations
		
		//take Ni of classes from the ith department
		//try to schedule the class as requested
		//in case it didn't work schedule it in the second best way
		
		//if there is no way to schedule it add it to the set of classes you need to return
		return null;
	}
}
