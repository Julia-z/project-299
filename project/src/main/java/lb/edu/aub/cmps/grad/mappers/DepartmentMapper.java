package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

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

	public Room getGradClass(int id);
	
	public Building getBuildingByPriority(@Param("id") int id, @Param("priority") int priority);
}
