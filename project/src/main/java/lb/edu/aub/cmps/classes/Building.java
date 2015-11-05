package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.Set;

public class Building {
	private int id;
	private String name;
	private int location_id;
	
	/*public static void main(String[] args){
		Building first = new Building();
		first.id = 1;
		first.name = "Nicely";
		first.location_id = 1;
		Building second = new Building();
		second.id = 2;
		second.name = "Bliss";
		second.location_id = 1;
		Building third = new Building();
		third.id = 3;
		third.name = "Fisk";
		third.location_id = 1;
		Building fourth = new Building();
		fourth.id = 4;
		fourth.name = "Phys";
		fourth.location_id = 2;
		HashSet<Building> buildings = new HashSet<Building>();
		buildings.add(first);
		buildings.add(second);
		buildings.add(third);
		buildings.add(fourth);
		System.out.println(first.getNearBuildings(buildings));
	}*/
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
