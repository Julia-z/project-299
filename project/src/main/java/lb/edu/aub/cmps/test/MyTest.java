package lb.edu.aub.cmps.test;

import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.services.ClassService;
import lb.edu.aub.cmps.services.CourseService;
import lb.edu.aub.cmps.services.ProfessorService;
import lb.edu.aub.cmps.services.RoomService;

public class MyTest {
	public static void main(String[] args) {
		/*
		 * RoomMapper rm = sqlSession.getMapper(RoomMapper.class);
		 * 
		 * Set<Room> rooms = rm.getAllRooms(); Set<Accessory> accessoriesInRoom
		 * = rm.getAccessoriesInRoom(1); System.out.println(rooms.size());
		 * System.out.println("Accessories: " + accessoriesInRoom.size());
		 * 
		 * CourseMapper cm = sqlSession.getMapper(CourseMapper.class);
		 * 
		 * Set<Course> courses = cm.getAllCourses();
		 * System.out.println("Courses: " + courses.size());
		 * 
		 * // DepartmentMapper dm =
		 * sqlSession.getMapper(DepartmentMapper.class);
		 * 
		 * /*Set<Department> departments = dm.getAllDepartments();
		 * System.out.println("Deps: " + departments.size()); Set<Course>
		 * coursesGiven = dm.getGivenCourse(1);
		 * System.out.println("Courses given: " + coursesGiven.size());
		 */
		/*
		 * ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
		 * 
		 * Set<Professor> Professors = pm.getAllProfessors();
		 * System.out.println("Professors: " + Professors.size());
		 * 
		 * Set<Class> coursesByProfessor = pm.getClassesGiven(1); System.out
		 * .println("Classes by p: " + coursesByProfessor.toString());
		 * 
		 * AccessoryMapper am = sqlSession.getMapper(AccessoryMapper.class);
		 * 
		 * Set<Accessory> Accessories = am.getAllAccessories(); System.out
		 * .println("Accessories in room: " + Accessories.toString());
		 * 
		 * DepartmentService d = new DepartmentService(); Set<Department> ds =
		 * d.getAllDepartments(); for(Department dep: ds){
		 * System.out.println(dep.toString()); }
		 * System.out.println(ds.toString());
		 */
		ClassService c = new ClassService();
		Set<Class> cs = c.getAllClasses();
		/*
		 * for(Class cl: cs){ System.out.println(cl.toString()); }
		 */
		System.out.println(cs.toString());

		ProfessorService p = new ProfessorService();
		Set<Professor> ps = p.getAllProfessors();
		System.out.println(ps.toString());

		RoomService r = new RoomService();
		Set<Room> rs = r.getAllRooms();
		System.out.println(rs.toString());

		CourseService allCourses = new CourseService();
		Set<Class> classes = allCourses.getAllClasses(1);
		System.out.println("Classes are: " + classes.toString());

		/*
		 * TimeSlot[] timeSlots= clm.getClassTimes(1); Time T= new
		 * Time(timeSlots); System.out.println(T.toString());
		 * 
		 * Professor professor= clm.getProfessor(1);
		 * System.out.println(professor.getName());
		 * 
		 * Set<Accessory> accessoriesInClass= clm.getAccessoriesInClass(1);
		 * System
		 * .out.println("Accessories in class: "+accessoriesInClass.toString
		 * ());
		 * 
		 * Room room= clm.getClassroom(1); System.out.println("Room: "+
		 * room.getNumber());
		 */
	}
}
