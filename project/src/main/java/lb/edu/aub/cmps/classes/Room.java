package lb.edu.aub.cmps.classes;

import java.util.ArrayList;
import java.util.HashSet;

public class Room {
	private int room_number;
	private int room_capacity;
	private int building_id;
	private HashSet<TimeSlot> reserved;

	public Room(int room_number, int room_capacity, int building_id) {
		this.room_number = room_number;
		this.room_capacity = room_capacity;
		this.building_id = building_id;
	}

	public int getRoom_number() {
		return room_number;
	}

	public int getRoom_capacity() {
		return room_capacity;
	}

	public int getBuilding() {
		return building_id;
	}

	/**
	 * TODO
	 * @return array list of rooms
	 * priority to less or equal capacity 
	 * second priority is to rooms in the same building
	 * third priority is to rooms in the nearby buildings
	 */
	public ArrayList<Room> getSimilar_available_rooms(TimeSlot[] t) {
		return null;
	}

	/**
	 * returns true if the room is available during the time slots
	 * i.e. the room is available during each time slot in the array
	 * TODO
	 * @param slots
	 * @return
	 */
	public boolean is_available(TimeSlot[] slots) {
		//we should loop over all the time slots and see if any time slot conflicts
		return true;
	}

	/**
	 * TODO
	 * modifies the timeSlots[]
	 * @param slots
	 * @return
	 */
	public boolean reserveRoom(TimeSlot[] slots){
		return true;
	}

	public HashSet<TimeSlot> getReserved() {
		return reserved;
	}

	public void addReserved(TimeSlot newReserved) {
		reserved.add(newReserved);
	}

}