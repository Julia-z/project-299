package lb.edu.aub.cmps.grad.services;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.mappers.CourseMapper;

import org.apache.ibatis.session.SqlSession;

/**
 * Uses the mappers to build a set of the courses found in the database, with
 * their corresponding classes.
 * 
 * @author Bilal Abi Farraj
 */

public class CourseService implements CourseMapper {

	/**
	 * @return a set of all the courses in the database with their details
	 */
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

	/**
	 * @return a set of all the classes of a given course
	 */
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

	/**
	 * @return a set of sections of a given class
	 */
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
