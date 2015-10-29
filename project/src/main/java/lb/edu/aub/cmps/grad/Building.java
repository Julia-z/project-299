package lb.edu.aub.cmps.grad;
import java.util.LinkedList;

import enums.Location;


public class Building {
	private int id;
	private String name;
	private Location location;
	private LinkedList<Room> rooms;
	
	public Building(int id, String name, Location location, LinkedList<Room> rooms){
		this.id= id;
		this.name= name;
		this.location= location;
		this.rooms= rooms;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public LinkedList<Room> getRooms() {
		return rooms;
	}
	
	public LinkedList<Building> getNearBuildings(){
		/*
		 * to find buildings with same locations
		 */
		return null;
	}
}
