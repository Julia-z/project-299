package lb.edu.aub.cmps.classes;

import java.util.Comparator;

public class ClassTimeComparator implements Comparator<Class> {



	public int compare(Class c1, Class c2) {
		//TODO if they have the same time we shouldn't return ZERO
		return c1.getGivenTime().compareTo(c2.getGivenTime());
	}

	

}
