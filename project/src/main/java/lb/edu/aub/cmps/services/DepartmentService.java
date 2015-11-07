package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.mappers.DepartmentMapper;

import org.apache.ibatis.session.SqlSession;

public class DepartmentService implements DepartmentMapper {

	public Set<Department> getAllDepartments() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			DepartmentMapper pm = sqlSession.getMapper(DepartmentMapper.class);
			Set<Department> deps = pm.getAllDepartments();
			for(Department dep: deps){
				dep.setCourses_offered(pm.getGivenCourses(dep.getId()));
			}
			return deps;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public Set<Course> getGivenCourses(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			DepartmentMapper pm = sqlSession.getMapper(DepartmentMapper.class);
			Set<Course> deps = pm.getGivenCourses(id);
			return deps;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
