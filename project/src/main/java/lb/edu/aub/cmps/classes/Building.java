package lb.edu.aub.cmps.classes;

import java.util.HashSet;
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
	public String toString(){
		return "Building Id:"+id+" Building Name: "+name+" Location Id:"+location_id;
	}
	/**
	 * Finds Buildings that have the same location as this
	 * @param bldgs
	 * @return Set of buildings with same location.
	 */
	public Set<Building> getNearBuildings(Set<Building> bldgs) {
		Set<Building> nearBuildings = new HashSet<Building>();
		for(Building building : bldgs){
			//Check if building has same location and is not the same building we want near buildings to.
            if(this.location_id == building.location_id && this.id != building.id){
            	nearBuildings.add(building);
            }
        }
		return nearBuildings;
	}

}
