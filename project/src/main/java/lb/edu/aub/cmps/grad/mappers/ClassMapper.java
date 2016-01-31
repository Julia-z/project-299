package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.TimeSlot;

/**
 * @author Bilal Abi Farraj
 */

public interface ClassMapper {

	public Set<Class> getAllClasses(int term);

	public Set<Integer> getSectionsInClass(@Param("id") int id,
			@Param("term") int term);

	public TimeSlot[] getClassTimes(@Param("id") int id, @Param("term") int term);

	public Professor getProfessor(@Param("id") int id, @Param("term") int term);

	public Set<Integer> getAccessoriesInClass(@Param("id") int id,
			@Param("term") int term);

	public Room getClassroom(@Param("id") int id, @Param("term") int term);

	public String getType(@Param("id") int id, @Param("term") int term);

	public Set<Class> getLabs(int term);

	public Set<Class> getLowerCampusLectures(int term);

	public Set<Class> getUpperCampusLectures(int term);

	public Set<Class> getLowerCampusRecitations(int term);

	public Set<Class> getUpperCampusRecitations(int term);

	public void updateLecture_Classroom(Class c);

	public void updateLecture_Time(Class c);

	public Set<Class> getTime_fixed_classes(int term);

	public Set<Class> getLoc_fixed_classes(int term);

	public Set<Class> getGrad_classes(int term);

	public Set<Class> getBig_lectures(int term);
}
