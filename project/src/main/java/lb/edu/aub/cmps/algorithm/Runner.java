package lb.edu.aub.cmps.algorithm;

import java.util.Set;
import lb.edu.aub.cmps.classes.Class;



public class Runner {

	public static void main(String[] args) {
		IScheduler s = new BasicScheduler();
		Set<Class> notSched = s.schedule();
		System.out.println(notSched.size());
	}
}
