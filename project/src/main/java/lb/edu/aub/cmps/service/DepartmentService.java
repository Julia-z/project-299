package lb.edu.aub.cmps.service;

import java.util.Set;

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
			return deps;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
