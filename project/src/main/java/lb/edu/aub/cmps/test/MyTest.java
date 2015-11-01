package lb.edu.aub.cmps.test;

import lb.edu.aub.cmps.mappers.DepartmentMapper;
import lb.edu.aub.cmps.service.DepartmentService;
import lb.edu.aub.cmps.service.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;


public class MyTest {

	public static void main(String[] args) {
		new DepartmentService();

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			DepartmentMapper userMapper = sqlSession.getMapper(DepartmentMapper.class);
			//Department d = departmentMapper.(1);
			//System.out.println(u);
		} finally {
			sqlSession.close();
		}

	}
}
