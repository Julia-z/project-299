package lb.edu.aub.cmps.mappers;

import java.util.HashSet;
import java.util.LinkedList;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.classes.Room;

public interface BuildingMapper {

	public HashSet<Room> getRooms(int id);
	public LinkedList<Building> getNearBuildings(int id);

}
