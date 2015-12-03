package lb.edu.aub.cmps.output;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lb.edu.aub.cmps.algorithm.SetUp;
import lb.edu.aub.cmps.classes.Class;
/**
 * Generates not scheduled classes info
 * @author Yasmin Kadah
 */
public class GenerateNotScheduledInfo {


	/**
	 * TODO yasmin
	 */
	public static void generateInfo(Map<Class, String> noSched){
		//loop through all the not scheduled classes and print them and the cause they couldn't be scheduled
		//the string in the map actually hides the reason why the class was not scheduled
		
	}
	
	public static void main(String[] args) throws SecurityException, IOException{
		SetUp s = new SetUp();
		Map<Class, String> not = new HashMap<Class, String>();
		for(Class c: s.getId_class().values()){
			not.put(c, "NO more options");
		}
		
	}
	
}
