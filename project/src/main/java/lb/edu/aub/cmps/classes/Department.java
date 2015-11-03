package lb.edu.aub.cmps.classes;

import java.util.LinkedList;

public class Department {
	private int id;
	private String name;
	private LinkedList<Course> courses_offered;
	private int building_id;
	private int priority;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LinkedList<Course> getCourses_offered() {
		return courses_offered;
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
}