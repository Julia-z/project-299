package lb.edu.aub.cmps.grad.classes;
import java.util.Comparator;

import lb.edu.aub.cmps.grad.classes.Section;

/**
 * compares two sections according to their section number 
 * if the section number is equal the difference if the sections ids
 * (fetched from the database will be returned) to ensure uniqueness
 * @author Julia
 */
public class SectionComparator implements Comparator<Section> {



	public int compare(Section s, Section s2) {
		int diff = s.getSection_number() - s2.getSection_number();
		if(diff != 0) return diff;
		else return s.getCourse_id() - s2.getCourse_id();
	}

	
}
