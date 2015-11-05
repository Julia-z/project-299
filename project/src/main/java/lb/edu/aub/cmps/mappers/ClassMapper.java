package lb.edu.aub.cmps.mappers;

import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.TimeSlot;
public interface ClassMapper {

	public Set<Class> getAllClasses();
	
	/**
	 * TODO there is supposed to be a class section so that we can retrieve the results
	 * @param id
	 * @return
	 */
	public Set<Integer> getSectionsInClass(int id);
	
	public TimeSlot[] getClassTimes(int id);
	
	public Professor getProfessor(int id);
}
