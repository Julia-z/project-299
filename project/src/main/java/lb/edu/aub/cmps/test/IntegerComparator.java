package lb.edu.aub.cmps.test;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

	public int compare(Integer i, Integer j) {

		return j - i;
	}

}
