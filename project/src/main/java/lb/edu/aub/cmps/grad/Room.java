package lb.edu.aub.cmps.grad;

public class Room {
	private int room_number;
	private int room_capacity;
	private Building building;
	
	public Room(int room_number, int room_capacity, Building building){
		this.room_number= room_number;
		this.room_capacity= room_capacity;
	}

	public int getRoom_number() {
		return room_number;
	}
	
	public int getRoom_capacity() {
		return room_capacity;
	}

	public Building getBuilding() {
		return building;
	}
}