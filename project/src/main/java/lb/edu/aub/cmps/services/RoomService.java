package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Accessory;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.mappers.RoomMapper;

import org.apache.ibatis.session.SqlSession;

public class RoomService implements RoomMapper {

	public Set<Room> getAllRooms() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);
			Set<Room> rooms = rm.getAllRooms();
			for(Room room: rooms){
				room.setAccessories(rm.getAccessoriesInRoom(room.getId()));
			}
			return rooms;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public Set<Accessory> getAccessoriesInRoom(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);
			Set<Accessory> accessories = rm.getAccessoriesInRoom(id);
			return accessories;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
