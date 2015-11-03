package lb.edu.aub.cmps.classes;

import java.util.LinkedList;

public class Course {
	private int department_id;
	private String course_name;
	private int category_id;
	private int nbr_of_sections;
	private LinkedList<Class> classes;



	public Course(int department_id, String course_name, int category_id,
			int nbr_of_sections) {
		super();
		this.department_id = department_id;
		this.course_name = course_name;
		this.category_id = category_id;
		this.nbr_of_sections = nbr_of_sections;
		this.classes = new LinkedList<Class>();
	}

	public int getDepartment() {
		return department_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public int getCategory_id() {
		return category_id;
	}

	public int getNbr_of_sections() {
		return nbr_of_sections;
	}

	public LinkedList<Class> getClasses() {
		return classes;
	}
	
	public void addClass(Class c){
		this.classes.add(c);
	}
}