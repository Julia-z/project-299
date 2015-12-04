package lb.edu.aub.cmps.grad.classes;
import java.util.Comparator;

import lb.edu.aub.cmps.grad.classes.Section;


public class SectionComparator implements Comparator<Section> {



	public int compare(Section s, Section s2) {
		int diff = s.getSection_number() - s2.getSection_number();
		if(diff != 0) return diff;
		else return s.getCourse_id() - s2.getCourse_id();
	}

	
}
