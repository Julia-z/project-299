package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.mappers.RoomMapper;

import org.apache.ibatis.session.SqlSession;

/**
 * Uses the mappers to build a set of the rooms saved in the database, with
 * their corresponding accessories.
 * 
 * @author Bilal Abi Farraj
 */
public class RoomService implements RoomMapper {
	
	/**
	 * @return a set of all the rooms in the database with their details 
	 */
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
	
	/**
	 * @return a set of accessories in a given room
	 */
	public Set<Integer> getAccessoriesInRoom(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			RoomMapper rm = sqlSession.getMapper(RoomMapper.class);
			Set<Integer> accessories = rm.getAccessoriesInRoom(id);
			return accessories;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
