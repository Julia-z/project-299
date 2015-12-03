package lb.edu.aub.cmps.mappers;

import java.util.Set;
import lb.edu.aub.cmps.classes.Room;

/**
 * @author Bilal Abi Farraj
 */

public interface RoomMapper {

	public Set<Room> getAllRooms();
	
	public Set<Integer> getAccessoriesInRoom(int id); 
}
