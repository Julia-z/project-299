package lb.edu.aub.cmps.classes;
import java.util.HashSet;
import java.util.LinkedList;

import lb.edu.aub.cmps.enums.Location;



public class Building {
	private int id;
	private String name;
	private Location location;
	private HashSet<Room> rooms;
	
	public Building(int id, String name, Location location, HashSet<Room> rooms){
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

	public HashSet<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public LinkedList<Building> getNearBuildings(){
		/*
		 * to find buildings with same locations
		 */
		return null;
	}
}
