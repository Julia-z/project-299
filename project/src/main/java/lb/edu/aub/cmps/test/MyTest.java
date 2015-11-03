package lb.edu.aub.cmps.test;

import java.util.List;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.mappers.BuildingMapper;
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

			BuildingMapper bm = sqlSession.getMapper(BuildingMapper.class);

			List<Building> bldgs = bm.getAllBuildings();
			System.out.println(bldgs.get(0).getName());

			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);

			List<Room> rooms = rm.getAllRooms();
			System.out.println(rooms.size());
			
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);

			List<Course> courses = cm.getAllCourses();
			System.out.println(courses.size());

			DepartmentMapper dm = sqlSession.getMapper(DepartmentMapper.class);

			List<Department> departments = dm.getAllDepartments();
			System.out.println(departments.size());
			
			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);

			List<Professor> Professors = pm.getAllProfessors();
			System.out.println(Professors.size());
			
			// Department d = departmentMapper.(1);
			// System.out.println(u);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// sqlSession.close();
		}

	}
}
