package lb.edu.aub.cmps.classes;
import java.util.LinkedList;


public class Department {
	private int id;
	private String name;
	private LinkedList<Course> courses_offered;
	private Building building;
	private int priority;
	
	public Department(int id, String name, LinkedList<Course> courses_offered, Building building, int priority){
		this.id= id;
		this.name= name;
		this.courses_offered= courses_offered;
		this.building= building;
		this.priority= priority;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LinkedList<Course> getCourses_offered() {
		return courses_offered;
	}

	public Building getBuilding() {
		return building;
	}

	public int getPriority() {
		return priority;
	}
}