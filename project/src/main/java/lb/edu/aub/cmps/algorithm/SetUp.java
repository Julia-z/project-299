package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lb.edu.aub.cmps.classes.*;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.service.*;

public class SetUp {
	private HashSet<Building> bldgs;
	private HashSet<Class> classes;
	private HashSet<Course> courses;
	private HashSet<Department> deps;
	private HashSet<Professor> profs;
	private HashSet<Room> rooms;
	
	private HashMap<Integer, Set<Class>> dep_classes_map;
	
	
	// fills in the needed sets before starting the computation
		public SetUp() {

			bldgs = (HashSet<Building>) new BuildingService().getAllBuildings();
			classes = (HashSet<Class>) new ClassService().getAllClasses();
			courses = (HashSet<Course>) new CourseService().getAllCourses();
			deps = (HashSet<Department>) new DepartmentService()
					.getAllDepartments();
			profs = (HashSet<Professor>) new ProfessorService().getAllProfessors();
			rooms = (HashSet<Room>) new RoomService().getAllRooms();

		}
		
	public HashSet<Building> getBldgs() {
		return bldgs;
	}

	public HashSet<Class> getClasses() {
		return classes;
	}

	public HashSet<Course> getCourses() {
		return courses;
	}

	public HashSet<Department> getDeps() {
		return deps;
	}

	public HashSet<Professor> getProfs() {
		return profs;
	}

	public HashSet<Room> getRooms() {
		return rooms;
	}

	public HashMap<Integer, Set<Class>> getDep_classes_map() {
		return dep_classes_map;
	}

	

	

}
