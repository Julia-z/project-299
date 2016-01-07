package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Course;

/**
 * @author Bilal Abi Farraj
 */

public interface CourseMapper {

	public Set<Course> getAllCourses();
	public Set<Class> getAllClasses(int id);
	public Set<Integer> getSectionsInCourse(int id);
	public Set<Course> getGradCourses();
}
