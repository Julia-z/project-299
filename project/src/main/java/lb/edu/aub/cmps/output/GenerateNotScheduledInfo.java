package lb.edu.aub.cmps.output;

import java.util.Map;
import lb.edu.aub.cmps.classes.Class;

public class GenerateNotScheduledInfo {
	private Map<Class, String> notSched;

	public GenerateNotScheduledInfo(Map<Class, String> notSched) {
		this.notSched = notSched;
	}
	
	/**
	 * TODO yasmin
	 */
	public void generateInfo(){
		//loop through all the not scheduled classes and print them and the cause they couldn't be scheduled
		
	}
	public Map<Class, String> getNotSched(){
		return notSched;
	}
	
}
