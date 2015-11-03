package lb.edu.aub.cmps.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.mappers.CourseMapper;

public class CourseService implements CourseMapper {

	public List<Course> getAllCourses() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try{
			
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);
			List<Course> courses = cm.getAllCourses();
			return courses;
		}
		 catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}


}
