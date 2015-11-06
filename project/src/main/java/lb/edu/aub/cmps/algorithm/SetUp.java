package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import lb.edu.aub.cmps.classes.*;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.services.*;

public class SetUp {
	private HashSet<Building> bldgs;
	private HashSet<Class> classes;
	private HashSet<Course> courses;
	private HashSet<Department> deps;
	private HashSet<Professor> profs;
	private HashSet<Room> rooms;
	private HashSet<Accessory> accessories;

	private HashMap<Integer, Set<Class>> dep_classes_map;

	/**
	 * TODO
	 */
	private HashMap<Building, Set<Room>> bldg_rooms;

	// fills in the needed sets before starting the computation
	public SetUp() {

		bldgs = (HashSet<Building>) new BuildingService().getAllBuildings();
		classes = (HashSet<Class>) new ClassService().getAllClasses();
		courses = (HashSet<Course>) new CourseService().getAllCourses();
		deps = (HashSet<Department>) new DepartmentService()
				.getAllDepartments();
		profs = (HashSet<Professor>) new ProfessorService().getAllProfessors();
		rooms = (HashSet<Room>) new RoomService().getAllRooms();
		accessories = (HashSet<Accessory>) new AccessoryService()
				.getAllAccessories();

		/**
		 * TODO build HashSet<Room, Set<Accessory>) to assign accessories for
		 * the room Same for every section build HashSet<Department,
		 * Set<Courses>) to assign courses given by departments
		 */

		/**
		 * TODO populate the dep_rooms map
		 */
		populateBldgs_rooms();
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

	public HashSet<Accessory> getAccessories() {
		return accessories;
	}

	/***
	 * added methods
	 */
	/**
	 * Finds Buildings that have the same location as this
	 * 
	 * @param bldgs
	 * @return Set of buildings with same location.
	 */
	public Set<Building> getNearBuildings(Building b) {
		Set<Building> nearBuildings = new HashSet<Building>();
		for (Building building : bldgs) {
			// Check if building has same location and is not the same building
			// we want near buildings to.
			if (b.getLocation_id() == building.getLocation_id()
					&& b.getId() != building.getId()) {
				nearBuildings.add(building);
			}
		}
		return nearBuildings;
	}

	public Building getBuildingById(int id) {
		for (Building b : bldgs)
			if (b.getId() == id)
				return b;
		return null;
	}

	/**
	 * private methods needed to populate a map of building as a key and a set of rooms as a value
	 */
	private void populateBldgs_rooms() {
		bldg_rooms = new HashMap<Building, Set<Room>>();
		for (Room r : rooms) {
			Building b = getBuildingById(r.getBuilding_id());
			Set<Room> rooms = bldg_rooms.get(b);
			if (rooms == null)
				rooms = new HashSet<Room>();
			rooms.add(r);
			bldg_rooms.put(b, rooms);
		}
	}
}
