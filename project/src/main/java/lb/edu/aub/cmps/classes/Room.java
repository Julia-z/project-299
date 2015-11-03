package lb.edu.aub.cmps.classes;

import java.util.ArrayList;
import java.util.HashSet;

public class Room {
	private int id;
	private String number;
	private int room_capacity;
	private int building_id;
	private HashSet<TimeSlot> reserved;

	public Room(int id, String number, int room_capacity, int building_id) {
		super();
		this.id = id;
		this.number = number;
		this.room_capacity = room_capacity;
		this.building_id = building_id;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getRoom_capacity() {
		return room_capacity;
	}

	public void setRoom_capacity(int room_capacity) {
		this.room_capacity = room_capacity;
	}

	public int getBuilding_id() {
		return building_id;
	}

	public void setBuilding_id(int building_id) {
		this.building_id = building_id;
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

}