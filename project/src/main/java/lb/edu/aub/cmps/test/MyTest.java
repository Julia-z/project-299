package lb.edu.aub.cmps.test;

import java.util.HashSet;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.services.ClassService;

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
		HashSet<Class> classes = (HashSet<Class>) c.getAllClasses();

		int i =0;
		for (Class cl : classes) {
/*			int id = cl.getClass_id();
			HashSet<Integer> ids= (HashSet<Integer>) c.getAccessoriesInClass(id);
			System.out.println(ids.size());
			for(Integer i: ids){
				System.out.println(i.toString());
			}
			*/
			if(i==0 && cl.getClass_id()== 1){
				cl.setGivenDay(2);
				
				c.updateLecture_Time(cl);
				i++;
			}
		}

		/*
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
