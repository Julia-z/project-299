package lb.edu.aub.cmps.classes;

import java.util.Set;

public class Department {
	private int id;
	private String name;
	private Set<Course> courses_offered;
	private int building_id;

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
		return "id: "+id+" name: "+name+", courses offered: "+ courses_offered.toString();
	}
	
	public void addCourse(Course c){
		courses_offered.add(c);
	}
}