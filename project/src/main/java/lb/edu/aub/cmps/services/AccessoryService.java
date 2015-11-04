package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Accessory;
import lb.edu.aub.cmps.mappers.AccessoryMapper;

import org.apache.ibatis.session.SqlSession;

public class AccessoryService implements AccessoryMapper {

	public Set<Accessory> getAllAccessories() {
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
