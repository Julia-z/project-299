package lb.edu.aub.cmps.algorithm;
import lb.edu.aub.cmps.classes.Class;

import java.util.Map;

public class Runner {

	public static void main(String[] args) {

		IScheduler s = new BasicScheduler();
		Map<Class, String> notSched = s.schedule();
		System.out.println(notSched.keySet().size());
	}
}
