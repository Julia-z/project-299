package lb.edu.aub.cmps.service;

import java.util.Set;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.mappers.CourseMapper;

import org.apache.ibatis.session.SqlSession;

public class CourseService implements CourseMapper {

	public Set<Course> getAllCourses() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try{
			
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);
			Set<Course> courses = cm.getAllCourses();
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
