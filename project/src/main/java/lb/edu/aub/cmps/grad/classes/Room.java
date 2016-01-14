package lb.edu.aub.cmps.grad.classes;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * Defines the object Room
 * 
 * @author Bilal Abi Farraj
 * @author Julia El Zini
 */

public class Room implements RoomVisitable{
	private int id;
	private String number;
	private int room_capacity;
	private int building_id;
	private String type;
	private Set<Integer> accessories_ids;
	//map: Timeslot -> course name
	private TreeMap<TimeSlot, Class> reserved;

	
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
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public Building getBuilding(Set<Building> buildings){
		for(Building b : buildings){
			if(this.building_id == b.getId()){
				return b;
			}
		}
		return null;
		
	}

	/**
	 * Checks if the room is available during the time slots i.e. the room is
	 * available during each time slot in the array
	 * 
	 * @param slots the time slots to check if the room is available during
	 * @return true if room is available during slots returns false otherwise
	 */
	public boolean is_available(TimeSlot[] slots) {
		boolean available = true;
		// we should loop over all the time slots and see if any time slot
		// conflicts
		if (this.reserved == null)
			return available;
		for (TimeSlot reservedTimeSlot : this.reserved.keySet()) {
			for (int i = 0; i < slots.length; i++) {
				if (reservedTimeSlot.conflicts(slots[i])){
					available = false;
				}
			}
		}
		return available;
	}

	/**
	 * Checks if Room is available during TimeSlot[] slots if available modifies
	 * the reserved timeSlots[] and returns true
	 * 
	 * @param slots the time slots during which we should reserve the room
	 * @param cl the class for wich we need to reserve the room
	 * @return if available returns true, if not available returns false
	 */
	public boolean reserveRoom(TimeSlot[] slots, Class cl) {
		for (int i = 0; i < slots.length; i++) {
			this.reserved.put(slots[i], cl);
		}
		return true;
	}

	public TreeMap<TimeSlot, Class> getReserved() {
		return reserved;
	}


	public String toString() {
		return "id " + id;
//		+ " ,number " + number + " ,accessories: "
//				+ accessories_ids +"type: "+type+" capacity: " + room_capacity
//				+ " building Id: " + building_id +" Reserved Times: "+reserved;
	}
	public void initializeReserved(){
		this.reserved = new TreeMap<TimeSlot, Class>(new RoomByTimeSlotComparator());
	}
	public void addAccessoryId(int id){
		if(accessories_ids == null) accessories_ids = new HashSet<Integer>();
		accessories_ids.add(id);
	}

	/**
	 * prints the time and the course or class given during that time in the room
	 * time should be sorted
	 */
	public void accept(RoomVisitor visitor) {
		visitor.visit(this);
		
	}
	
	public boolean hasAccessory(Set<Integer> a){
		return accessories_ids.containsAll(a);
	}

	public void setAccessories(Set<Integer> accessories_ids) {
		this.accessories_ids = accessories_ids;
		
	}
}
