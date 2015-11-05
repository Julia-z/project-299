package lb.edu.aub.cmps.test;

import java.util.Set;

import lb.edu.aub.cmps.classes.Accessory;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;
import lb.edu.aub.cmps.mappers.AccessoryMapper;
import lb.edu.aub.cmps.mappers.ClassMapper;
import lb.edu.aub.cmps.mappers.CourseMapper;
import lb.edu.aub.cmps.mappers.DepartmentMapper;
import lb.edu.aub.cmps.mappers.ProfessorMapper;
import lb.edu.aub.cmps.mappers.RoomMapper;
import lb.edu.aub.cmps.services.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class MyTest {
	public static void main(String[] args) {
		// new BuildingService();
		try {
			SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
					.openSession();



			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);

			Set<Room> rooms = rm.getAllRooms();
			Set<Accessory> accessoriesInRoom = rm.getAccessoriesInRoom(1);
			System.out.println(rooms.size());
			System.out.println("Accessories: "+accessoriesInRoom.size());
			
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);

			Set<Course> courses = cm.getAllCourses();
			System.out.println("Courses: " + courses.size());

			DepartmentMapper dm = sqlSession.getMapper(DepartmentMapper.class);

			Set<Department> departments = dm.getAllDepartments();
			System.out.println("Deps: " + departments.size());
			Set<Course> coursesGiven= dm.getGivenCourse(1);
			System.out.println("Courses given: " + coursesGiven.size());
			
			
			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);

			Set<Professor> Professors = pm.getAllProfessors();
			System.out.println("Professors: " + Professors.size());
			
			AccessoryMapper am = sqlSession.getMapper(AccessoryMapper.class);

			Set<Accessory> Accessories = am.getAllAccessories();
			System.out.println("Accessories: " + Accessories.size());

			ClassMapper clm = sqlSession.getMapper(ClassMapper.class);

			Set<Class> classes = clm.getAllClasses();
			System.out.println("Classes: " + classes.size());
			
			TimeSlot[] timeSlots= clm.getClassTimes(1);
			Time T= new Time(timeSlots);
			System.out.println(T.toString());
			
			Professor professor= clm.getProfessor(1);
			System.out.println(professor.getName());
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// sqlSession.close();
		}

	}
}
