package lb.edu.aub.cmps.classes;

import java.util.ArrayList;
import java.util.HashSet;

import lb.edu.aub.cmps.enums.Day;

public class Room {
	private int id;
	private String number;
	private int room_capacity;
	private int building_id;
	private HashSet<Accessory> accessories;
	private HashSet<TimeSlot> reserved;

	
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
	public Room getSimilar_available_rooms(HashSet<Room> rooms, TimeSlot[] t) {
		return null;
	}

	/**
	 * Checks if the room is available during the time slots
	 * i.e. the room is available during each time slot in the array
	 * @param slots
	 * @return true if room is available during slots
	 * returns false otherwise
	 */
	public boolean is_available(TimeSlot[] slots) {
		boolean available = true;
		//we should loop over all the time slots and see if any time slot conflicts
		for(TimeSlot reservedTimeSlot : this.reserved){
            for(int i=0; i<slots.length; i++){
            	if(reservedTimeSlot.conflicts(slots[i]))
            		available = false;
            }
        }
		return available;
	}

	/**
	 * Checks if Room is available during TimeSlot[] slots
	 * if available modifies the reserved timeSlots[] and returns true
	 * @param slots
	 * @return if available returns true, if not available returns false
	 */
	public boolean reserveRoom(TimeSlot[] slots){
		if(this.is_available(slots)){
			for(int i=0; i<slots.length; i++){
				this.reserved.add(slots[i]);
			}
			return true;
		}else{
			return false;
		}
	}

	public HashSet<TimeSlot> getReserved() {
		return reserved;
	}

	public HashSet<Accessory> getAccessories() {
		return accessories;
	}

	public void setAccessories(HashSet<Accessory> accessories) {
		this.accessories = accessories;
	}

}