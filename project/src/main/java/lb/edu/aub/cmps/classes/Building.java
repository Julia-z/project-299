package lb.edu.aub.cmps.classes;

/**
 * 
 * @author Bilal Abi Farraj
 * Defines the object Building.
 */
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

	public String toString() {
		return "(Building Id:" + id + " Building Name: " + name
				+ " Location Id:" + location_id + ")";
	}

}
