package lb.edu.aub.cmps.grad.services;

import java.util.Set;

import lb.edu.aub.cmps.grad.mappers.AccessoryMapper;

import org.apache.ibatis.session.SqlSession;

/**
 * Uses the mappers to build a set of the accessories used.
 * @author Bilal Abi Farraj
 */
public class AccessoryService implements AccessoryMapper {

	/**
	 * @return a set of all accessories in the database
	 */
	public Set<Integer> getAllAccessories() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			AccessoryMapper am = sqlSession.getMapper(AccessoryMapper.class);
			return am.getAllAccessories();
		} finally {
			sqlSession.close();
		}
	}
}
