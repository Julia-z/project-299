package lb.edu.aub.cmps.mappers;

import java.util.Set;

import lb.edu.aub.cmps.classes.Accessory;
import lb.edu.aub.cmps.classes.Room;

public interface RoomMapper {

	public Set<Room> getAllRooms();
	
	public Set<Accessory> getAccessoriesInRoom(int id); 
}
