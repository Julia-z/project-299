package lb.edu.aub.cmps.classes;

import java.util.Comparator;

import lb.edu.aub.cmps.classes.Department;

public class DepartmentWeightComparator implements Comparator<Department>{

	public int compare(Department d1, Department d2) {
		int ret = d1.getCourses_offered().size() - d2.getCourses_offered().size();
		if(ret!=0) return ret;
		else return d1.getId() - d2.getId();
	}

}
