package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
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
			for(Course c: cm.getAllCourses()){
				c.setSectionNbrs(getSectionsInCourse(c.getCourse_id()));
			}
			return courses;
		}
		 catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public Set<Class> getAllClasses(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try{
			
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);
			Set<Class> classes = cm.getAllClasses(id);
			return classes;
		}
		 catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public Set<Integer> getSectionsInCourse(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			CourseMapper cm = sqlSession.getMapper(CourseMapper.class);
			return cm.getSectionsInCourse(id);
		} finally {
			sqlSession.close();
		}
	}


}
