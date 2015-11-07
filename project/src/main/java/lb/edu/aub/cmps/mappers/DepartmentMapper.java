package lb.edu.aub.cmps.mappers;

import java.util.Set;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;

public interface DepartmentMapper {

	public Set<Department> getAllDepartments();
	public Set<Course> getGivenCourses(int id);
}
