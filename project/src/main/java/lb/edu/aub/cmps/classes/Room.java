package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.Set;

public class Room {
	private int id;
	private String number;
	private int room_capacity;
	private int building_id;
	private String type;
	private Set<Accessory> accessories;
	private HashSet<TimeSlot> reserved;

	/*
	 * TESTING public static void main(String[] args){ TimeSlot[] times = new
	 * TimeSlot[3]; times[0] = new TimeSlot(); times[0].setDay(Day.M);
	 * times[0].setStart("0500"); times[0].setEnd("0600"); times[1] = new
	 * TimeSlot(); times[1].setDay(Day.T); times[1].setStart("0700");
	 * times[1].setEnd("0730"); times[2] = new TimeSlot();
	 * times[2].setDay(Day.F); times[2].setStart("0731");
	 * times[2].setEnd("0830"); HashSet<TimeSlot> wish = new
	 * HashSet<TimeSlot>(); wish.add(times[0]); wish.add(times[2]);
	 * wish.add(times[1]); Room myRoom = new Room(); myRoom.id = 1;
	 * myRoom.number = "322"; myRoom.room_capacity = 15; myRoom.building_id = 3;
	 * myRoom.reserved = wish; TimeSlot[] times2 = new TimeSlot[3]; times2[0] =
	 * new TimeSlot(); times2[0].setDay(Day.M); times2[0].setStart("0700");
	 * times2[0].setEnd("0800"); times2[1] = new TimeSlot();
	 * times2[1].setDay(Day.W); times2[1].setStart("0700");
	 * times2[1].setEnd("0730"); times2[2] = new TimeSlot();
	 * times2[2].setDay(Day.F); times2[2].setStart("1531");
	 * times2[2].setEnd("1630");
	 * 
	 * TimeSlot[] times3 = new TimeSlot[3]; times3[0] = new TimeSlot();
	 * times3[0].setDay(Day.M); times3[0].setStart("0900");
	 * times3[0].setEnd("1000"); times3[1] = new TimeSlot();
	 * times3[1].setDay(Day.W); times3[1].setStart("1700");
	 * times3[1].setEnd("1730"); times3[2] = new TimeSlot();
	 * times3[2].setDay(Day.F); times3[2].setStart("1931");
	 * times3[2].setEnd("1930"); System.out.println("testing reserving rooms "+
	 * myRoom.reserveRoom(times2)); System.out.println(myRoom.reserved);
	 * System.out.println("test 2 reserving room "+myRoom.reserveRoom(times3));
	 * System.out.println(myRoom.reserved); myRoom.id = 1; myRoom.number =
	 * "322"; myRoom.room_capacity = 15; myRoom.building_id = 3; myRoom.reserved
	 * = wish;
	 * System.out.println("testing is available method "+myRoom.is_available
	 * (times2)); }
	 */
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
	 * @param slots
	 * @return true if room is available during slots returns false otherwise
	 */
	public boolean is_available(TimeSlot[] slots) {
		boolean available = true;
		// we should loop over all the time slots and see if any time slot
		// conflicts
		if (this.reserved == null)
			return available;
		for (TimeSlot reservedTimeSlot : this.reserved) {
			for (int i = 0; i < slots.length; i++) {
				if (reservedTimeSlot.conflicts(slots[i]))
					available = false;
			}
		}
		return available;
	}

	/**
	 * Checks if Room is available during TimeSlot[] slots if available modifies
	 * the reserved timeSlots[] and returns true
	 * 
	 * @param slots
	 * @return if available returns true, if not available returns false
	 */
	public boolean reserveRoom(TimeSlot[] slots) {
		if (this.is_available(slots)) {
			for (int i = 0; i < slots.length; i++) {
				this.reserved.add(slots[i]);
			}
			return true;
		} else {
			return false;
		}
	}

	public HashSet<TimeSlot> getReserved() {
		return reserved;
	}

	public Set<Accessory> getAccessories() {
		return accessories;
	}

	public void setAccessories(Set<Accessory> accessories) {
		this.accessories = accessories;
	}

	public String toString() {
		return "id " + id + " ,number " + number + " ,accessories: "
				+ accessories +"type: "+type+" capacity: " + room_capacity
				+ " building Id: " + building_id +" Reserved Times: "+reserved;
	}
	public void initializeReserved(){
		this.reserved = new HashSet<TimeSlot>();
	}
}
