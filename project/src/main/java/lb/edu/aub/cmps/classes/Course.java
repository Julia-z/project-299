package lb.edu.aub.cmps.classes;

import java.util.LinkedList;

import lb.edu.aub.cmps.enums.Category;


public class Course {
	private Department department;
	private String course_nbr;
	private Category category;
	private int nbr_of_sections;
	private LinkedList<Class> classes;

	public Course(Department department, String course_nbr, Category category,
			int nbr_of_sections, LinkedList<Class> classes) {
		this.department = department;
		this.course_nbr = course_nbr;
		this.category = category;
		this.nbr_of_sections = nbr_of_sections;
		this.classes = classes;
	}

	public Department getDepartment() {
		return department;
	}

	public String getCourse_nbr() {
		return course_nbr;
	}

	public Category getCategory() {
		return category;
	}

	public int getNbr_of_sections() {
		return nbr_of_sections;
	}
	
	public LinkedList<Class> getClasses() {
		return classes;
	}
}
