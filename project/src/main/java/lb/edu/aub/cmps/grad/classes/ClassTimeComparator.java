package lb.edu.aub.cmps.grad.classes;

import java.util.Comparator;

/**
 * Implements Comparator. 
 * compares the two classes given their time
 * if they have the same time it returns the difference between their id
 * @author Julia El Zini
 */

public class ClassTimeComparator implements Comparator<Class> {

	public int compare(Class c1, Class c2) {
		if(!c1.canChangeTime() || !c1.canChangeRoom()) return -1;
		if(!c2.canChangeTime() || !c2.canChangeRoom()) return 1;
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
