package lb.edu.aub.cmps.classes;


public class Course {
	private int course_id;
	private int department_id;
	private String course_name;
	private int category_id;
	private int nbr_of_sections;

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
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
	/**
	 * 	private int course_id;
	private int department_id;
	private String course_name;
	private int category_id;
	private int nbr_of_sections;
	 */
	public String toString(){
		return "course id: "+course_id+", course name: "+course_name;
	}
}