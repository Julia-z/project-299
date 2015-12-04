package lb.edu.aub.cmps.test;

import java.util.HashSet;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Day;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.services.ClassService;

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
		
		Class cl = new Class();
		cl.setClass_id(35);
		cl.setReq_day(1);
		cl.setGivenDay(1);
		cl.setGivenStart("1000");
		cl.setGivenEnd("1100");
		
		c.updateLecture_Time(cl);
		/*
		HashSet<Class> classes = (HashSet<Class>) c.getAllClasses();
	
		Class[] array= new Class[classes.size()];//= (Class[]) classes.toArray();
		
		int i= 0;
		for(Class cs: classes){
			array[i]= cs;
			i++;
		}
		
		Class current= array[0];
		
		TimeSlot[] slots= current.getRequestedTime().getTimeSlots();
		
		for(int j=0; j<slots.length; j++){
			if(slots[j]!= null){
				int day= dayToInt(slots[j].getDay());
				String start= slots[j].getStart();
				String end= slots[j].getEnd();

				System.out.println(day+" "+ start+" "+ end);

				current.setGivenDay(day);
				current.setGivenStart("1100");
				current.setGivenEnd("1200");
				
				c.updateLecture_Time(current);
				
				System.out.println(current.getGivenDay()+ " "+ current.getGivenStart()+" "+current.getGivenEnd());
			//	System.out.println(day+" "+ start+" "+ end);
				System.out.println(array[0]);
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

	private  static int dayToInt(Day d) {
		int day = 0;

		if (d == Day.M) {
			day = 1;
		} else if (d == Day.T) {
			day = 2;
		} else if (d == Day.T) {
			day = 2;
		} else if (d == Day.W) {
			day = 3;
		} else if (d == Day.R) {
			day = 4;
		} else if (d == Day.F) {
			day = 5;
		} else if (d == Day.S) {
			day = 6;
		} else if (d == Day.U) {
			day = 7;
		}
		return day;
	}
}
