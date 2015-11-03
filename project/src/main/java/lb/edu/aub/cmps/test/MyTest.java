package lb.edu.aub.cmps.test;

import java.util.HashSet;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.mappers.BuildingMapper;
import lb.edu.aub.cmps.service.BuildingService;
import lb.edu.aub.cmps.service.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;


public class MyTest {
	public static void main(String[] args) {
		new BuildingService();

		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			BuildingMapper bm = sqlSession.getMapper(BuildingMapper.class);
			HashSet<Building> bldgs = bm.getAllBuildings();
			System.out.println(bldgs.size());
			//Department d = departmentMapper.(1);
			//System.out.println(u);
		} finally {
			sqlSession.close();
		}

	}
}
