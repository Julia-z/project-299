package lb.edu.aub.cmps.service;

import java.util.List;

import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.mappers.RoomMapper;

import org.apache.ibatis.session.SqlSession;

public class RoomService implements RoomMapper {

	public List<Room> getAllRooms() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);
			List<Room> rooms = rm.getAllRooms();
			return rooms;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
