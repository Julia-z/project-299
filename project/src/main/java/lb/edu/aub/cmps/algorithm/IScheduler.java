package lb.edu.aub.cmps.algorithm;

import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;

public interface IScheduler {

	public Map<Class, String> schedule();
	public Map<Department, Set<Class>> getScheduled();

}
