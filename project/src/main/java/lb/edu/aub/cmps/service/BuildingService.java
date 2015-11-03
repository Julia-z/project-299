package lb.edu.aub.cmps.service;

import java.util.HashSet;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.mappers.BuildingMapper;

import org.apache.ibatis.session.SqlSession;

public class BuildingService {

	public HashSet<Building> getAllBuildings() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			BuildingMapper userMapper = sqlSession.getMapper(BuildingMapper.class);
			return userMapper.getAllBuildings();
		} finally {
			sqlSession.close();
		}
	}
}
