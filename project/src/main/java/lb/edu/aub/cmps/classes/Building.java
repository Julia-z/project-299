package lb.edu.aub.cmps.classes;

import java.util.LinkedList;

public class Building {
	private int id;
	private String name;
	private String number;
	private int location_id;

	

	public Building(int id, String name, String number, int location_id) {
		super();
		this.id = id;
		this.name = name;
		this.number = number;
		this.location_id = location_id;
	}

	
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getNumber() {
		return number;
	}


	public int getLocation_id() {
		return location_id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}


	/**
	 * TODO
	 * 
	 * @return
	 */
	public LinkedList<Building> getNearBuildings() {
		/*
		 * to find buildings with same locations
		 */
		return null;
	}
}
