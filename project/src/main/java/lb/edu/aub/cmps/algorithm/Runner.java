package lb.edu.aub.cmps.algorithm;

import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;

public class Runner {

	public static void main(String[] args) {
		Scheduler s = new Scheduler(10);
		Map<Department, Set<Course>> req = s.getRequests();
		for(Department d: req.keySet()){
			System.out.println(d.getId() + ": "+d.getCourses_offered().size());
		}
	}
}
