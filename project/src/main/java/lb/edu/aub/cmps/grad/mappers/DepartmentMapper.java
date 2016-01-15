package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Building;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.Room;

/**
 * @author Bilal Abi Farraj
 */

public interface DepartmentMapper {

	public Set<Department> getAllDepartments();

	public Set<Course> getGivenCourses(int id);

	public Room getGradConferenceRoom(int id);
	
	public Building[] getBuildingByPriority(int id);
	
	public Room[] getLectureRoomsByPriority(int id);
}
