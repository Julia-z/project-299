package lb.edu.aub.cmps.classes;

import java.util.Set;

public class Building {
	private int id;
	private String name;
	private int location_id;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
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

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	/**
	 * TODO returns the bldgs with the same location
	 * 
	 * @return
	 */
	public Set<Building> getNearBuildings(Set<Building> bldgs) {
		/*
		 * to find buildings with same locations
		 */
		return null;
	}

}
