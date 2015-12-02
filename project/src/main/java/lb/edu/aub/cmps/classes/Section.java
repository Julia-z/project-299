package lb.edu.aub.cmps.classes;
import java.util.HashSet;
import java.util.Set;


public class Section {

	private int dep_id;
	private String dep_name;
	private int section_number;
	private String course_name;
	private int course_id;
	private Set<Class> lectures;
	
	public Section(int dep_id, String dep_name, int section_number,
			String course_name, int course_id) {
		super();
		this.dep_id = dep_id;
		this.dep_name = dep_name;
		this.section_number = section_number;
		this.course_name = course_name;
		this.course_id = course_id;
		this.lectures = new HashSet<Class>();
	}

}
