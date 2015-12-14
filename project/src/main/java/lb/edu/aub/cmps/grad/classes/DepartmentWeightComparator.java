package lb.edu.aub.cmps.grad.classes;

import java.util.Comparator;

import lb.edu.aub.cmps.grad.classes.Department;

/**
 * Implements Comparator. 
 * compares two departments given their weights 
 * the weight is proportinal to the number of classes they offer
 * if the two departments give the same number of classes they will be compared according to their ids 
 * (fetched from the database) to ensure uniqueness so that all the departments will appear in the scheduling even if they have the same weight
 * @author Julia El Zini
 * @author Bilal Abi Farraj
 */
public class DepartmentWeightComparator implements Comparator<Department>{

	public int compare(Department d1, Department d2) {
		int ret = d1.getNum_of_classes() - d2.getNum_of_classes();
		if(ret!=0) return ret;
		else return d1.getId() - d2.getId();
	}

}
