package lb.edu.aub.cmps.output;

import java.io.IOException;
import java.util.Map;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.classes.Class;

public class GenerateNotScheduledInfo {


	/**
	 * TODO yasmin
	 */
	public static void generateInfo(Map<Class, String> noSched){
		//loop through all the not scheduled classes and print them and the cause they couldn't be scheduled
		//the string in the map actually hides the reason why the class was not scheduled
	}
	
	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		Map<Class, String> not = s.schedule();
		generateInfo(not);
	}
	
}
