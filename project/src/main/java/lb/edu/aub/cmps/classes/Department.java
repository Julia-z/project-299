package lb.edu.aub.cmps.classes;

import java.util.Set;

public class Department {
	private int id;
	private String name;
	private Set<Course> courses_offered;
	private int building_id;
	private int priority;

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

	public void setPriority(int p){
		this.priority = p;
	}
	public int getPriority() {
		return priority;
	}
	
	public String toString(){
		return "id: "+id+" name: "+name+", courses offered: "+ courses_offered.toString();
	}
}