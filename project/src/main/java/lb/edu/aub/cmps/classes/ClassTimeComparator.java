package lb.edu.aub.cmps.classes;

import java.util.Comparator;

public class ClassTimeComparator implements Comparator<Class> {



	public int compare(Class c1, Class c2) {
		//System.out.println(c1.getRequestedTime()+"?????????"+c2.getRequestedTime());
		int diff =  c1.getRequestedTime().compareTo(c2.getRequestedTime());
		if(diff != 0) return diff;
		else{
			int diff2 = c1.getClass_id() - c2.getClass_id();
			if(diff2 < 0) return -1;
			else if(diff2 == 0) return 0;
			else return 1;
		}
	}

	

}
