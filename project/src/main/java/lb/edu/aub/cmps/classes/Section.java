package lb.edu.aub.cmps.classes;
import java.util.HashSet;
import java.util.Set;


public class Section {

	private int dep_id;
	private String dep_name;
	private int section_number;
	private String course_name;
	private int course_id;
	private Set<Class> classes;
	
	public Section(int dep_id, String dep_name, int section_number,
			String course_name, int course_id) {
		super();
		this.dep_id = dep_id;
		this.dep_name = dep_name;
		this.section_number = section_number;
		this.course_name = course_name;
		this.course_id = course_id;
		this.classes = new HashSet<Class>();
	}
	public void addClass(Class c){
		classes.add(c);
	}
	public int getDep_id() {
		return dep_id;
	}
	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public int getSection_number() {
		return section_number;
	}
	public void setSection_number(int section_number) {
		this.section_number = section_number;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public Set<Class> getClasses() {
		return classes;
	}
	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

}
