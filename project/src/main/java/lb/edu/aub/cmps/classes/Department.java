package lb.edu.aub.cmps.classes;

import java.util.Set;

/**
 * Defines the object Department
 * @author Julia El Zini
 * @author Bilal Abi Farraj
 */
public class Department {
	private int id;
	private String name;
	private Set<Course> courses_offered;
	private int building_id;
	private int num_of_classes;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Course> getCourses_offered() {
		return courses_offered;
	}

	public void setCourses_offered(Set<Course> courses_offered) {
		this.courses_offered = courses_offered;
	}

	public int getBuilding_id() {
		return building_id;
	}


	public String toString(){
		return "id: "+id+" name: "+name+", num of classes: "+ num_of_classes;
	}
	
	public void addCourse(Course c){
		courses_offered.add(c);
		num_of_classes += c.getNum_of_classes();
	}
	
	public int getNum_of_classes(){
		return num_of_classes;
	}
	
	
}