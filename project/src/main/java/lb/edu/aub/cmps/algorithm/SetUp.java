package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Room;

public class SetUp {

	HashSet<Course> courses;
	HashSet<Room> allrooms;
	HashMap<Department, Set<Class>> requests;
	//we might need more fields

	//connection to database
	//here comes the role of MyBatis
	public SetUp(){
		
	}
	
	public HashMap<Department, Set<Class>> getRequests(){
		return requests;
	}
}
