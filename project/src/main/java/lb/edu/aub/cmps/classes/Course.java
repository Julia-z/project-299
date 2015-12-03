package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Defines the object Course
 * @author Julia El Zini
 * @author Bilal Abi Farraj
 * 
 */

public class Course {
	private int course_id;
	private int department_id;
	private String course_name;
	private int category_id;
	private Set<Integer> sections_numbers;
	private Set<Class> classes;
	private Set<Section> sections;
	private int num_of_classes;

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

	public Set<Integer> getSectionNbrs() {
		return sections_numbers;
	}
	
	public void setSectionNbrs(Set<Integer> sections_nbrs){
		this.sections_numbers = sections_nbrs;
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

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
		num_of_classes = classes.size();
	}
	public void addClass(Class cl){
		if(classes == null) classes = new HashSet<Class>();
		classes.add(cl);
		num_of_classes++;
	}
	public int getNum_of_classes(){
		return num_of_classes;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
	public void addSection(Section s){
		if(sections == null){
			sections = new TreeSet<Section>(new SectionComparator());
		}
		sections.add(s);
	}
}