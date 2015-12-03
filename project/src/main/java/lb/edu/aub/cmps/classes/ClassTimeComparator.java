package lb.edu.aub.cmps.classes;

import java.util.Comparator;

/**
 * Implements Comparator. Compares the times of 2 given Classes
 * @author Julia El Zini
 */

public class ClassTimeComparator implements Comparator<Class> {

	public int compare(Class c1, Class c2) {
		int diff = c1.getRequestedTime().compareTo(c2.getRequestedTime());
		if (diff != 0)
			return diff;
		else {
			int diff2 = c1.getClass_id() - c2.getClass_id();
			if (diff2 < 0)
				return -1;
			else if (diff2 == 0)
				return 0;
			else
				return 1;
		}
	}
}
