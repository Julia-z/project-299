package lb.edu.aub.cmps.algorithm;

import java.util.Comparator;

import lb.edu.aub.cmps.classes.Department;

public class DepartmentWeightComparator implements Comparator<Department>{

	public int compare(Department d1, Department d2){
		return d1.getCourses_offered().size() - d2.getCourses_offered().size(); 
	}
}
