package lb.edu.aub.cmps.test;

import java.util.Set;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.mappers.CourseMapper;
import lb.edu.aub.cmps.mappers.DepartmentMapper;
import lb.edu.aub.cmps.mappers.ProfessorMapper;
import lb.edu.aub.cmps.mappers.RoomMapper;
import lb.edu.aub.cmps.service.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;

public class MyTest {
	public static void main(String[] args) {
		// new BuildingService();
		try {
			SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
					.openSession();



			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);

			Set<Room> rooms = rm.getAllRooms();
			System.out.println(rooms.size());
			
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);

			Set<Course> courses = cm.getAllCourses();
			System.out.println("Courses" + courses.size());

			DepartmentMapper dm = sqlSession.getMapper(DepartmentMapper.class);

			Set<Department> departments = dm.getAllDepartments();
			System.out.println("Deps: " + departments.size());
			
			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);

			Set<Professor> Professors = pm.getAllProfessors();
			System.out.println("Professors: " + Professors.size());
			
			// Department d = departmentMapper.(1);
			// System.out.println(u);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// sqlSession.close();
		}

	}
}
