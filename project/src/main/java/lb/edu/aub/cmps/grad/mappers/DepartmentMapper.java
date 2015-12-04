package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;

/**
 * @author Bilal Abi Farraj
 */

public interface DepartmentMapper {

	public Set<Department> getAllDepartments();
	public Set<Course> getGivenCourses(int id);
}
