package lb.edu.aub.cmps.grad.services;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Building;
import lb.edu.aub.cmps.grad.mappers.BuildingMapper;

import org.apache.ibatis.session.SqlSession;

/**
 * Uses the mappers to build a set of the buildings saved in the database.
 * @author Bilal Abi Farraj
 */
public class BuildingService implements BuildingMapper {

	/**
	 * @return a set of all buildings in the database
	 */
	public Set<Building> getAllBuildings() {
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
