package lb.edu.aub.cmps.service;

import java.util.List;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.mappers.BuildingMapper;

import org.apache.ibatis.session.SqlSession;

public class BuildingService implements BuildingMapper {

	public List<Building> getAllBuildings() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			BuildingMapper bm = sqlSession.getMapper(BuildingMapper.class);
			return bm.getAllBuildings();
		} finally {
			sqlSession.close();
		}
	}
}
