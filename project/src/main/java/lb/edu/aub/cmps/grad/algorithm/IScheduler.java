package lb.edu.aub.cmps.grad.algorithm;

import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.Room;

/**
 * @author Julia El Zini
 */

/**
 * Interface for the scheduling
 * @author Julia
 *
 */
public interface IScheduler {

	public Map<Class, String> schedule();
	public Map<Department, Set<Class>> getScheduled();
	public Map<Integer, Room> getIdRoomMap();
	public Map<Department, Set<Course>> getDepCoursesMap();
	public Map<Department, Double> getStatisticsByDepartment();
	public double getOverallStatistics();
}
