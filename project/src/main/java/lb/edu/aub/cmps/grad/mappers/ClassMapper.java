package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.TimeSlot;

/**
 * @author Bilal Abi Farraj
 */

public interface ClassMapper {

	public Set<Class> getAllClasses();

	/**
	 * 
	 * @param id
	 *            the id of the class we want to search for its sections
	 * @return a set of integer denoting the section numbers for sections in a
	 *         class whose id is given
	 */
	public Set<Integer> getSectionsInClass(int id);

	public TimeSlot[] getClassTimes(int id);

	public Professor getProfessor(int id);

	public Set<Integer> getAccessoriesInClass(int id);

	public Room getClassroom(int id);

	public String getType(int id);

	public Set<Class> getLabs();

	public Set<Class> getLowerCampusLectures();

	public Set<Class> getUpperCampusLectures();
	
	public Set<Class> getRecitations();
	
	public void updateLecture_Classroom(Class c);

	public void updateLecture_Time(Class c);
}
