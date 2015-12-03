package lb.edu.aub.cmps.mappers;

import java.util.Set;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Class;

/**
 * @author Bilal Abi Farraj
 */

public interface CourseMapper {

	public Set<Course> getAllCourses();
	public Set<Class> getAllClasses(int id);
	public Set<Integer> getSectionsInCourse(int id);
}
