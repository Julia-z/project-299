package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import lb.edu.aub.cmps.classes.Accessory;
import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;
import lb.edu.aub.cmps.services.AccessoryService;
import lb.edu.aub.cmps.services.BuildingService;
import lb.edu.aub.cmps.services.ClassService;
import lb.edu.aub.cmps.services.CourseService;
import lb.edu.aub.cmps.services.DepartmentService;
import lb.edu.aub.cmps.services.ProfessorService;
import lb.edu.aub.cmps.services.RoomService;

// calls on all the services and initialize all the data members
public class SetUp {
	private HashSet<Building> bldgs; //done
	private HashSet<Class> classes; //still needs many fields
	private HashSet<Course> courses; //still needs the classes
	private HashSet<Department> deps;//DONE
	private HashSet<Professor> profs;//DONE
	private HashSet<Room> rooms;//still needs the type
	private HashSet<Accessory> accessories;//done

	private HashMap<Building, Set<Room>> bldg_rooms;//done

	// fills in the needed sets before starting the computation
	public SetUp() {

		/**
		 * I assume here that all the sets contain full objects i.e. : all data
		 * members are not null
		 */

		bldgs = (HashSet<Building>) new BuildingService().getAllBuildings();
		classes = (HashSet<Class>) new ClassService().getAllClasses();
		courses = (HashSet<Course>) new CourseService().getAllCourses();
		deps = (HashSet<Department>) new DepartmentService()
				.getAllDepartments();
		profs = (HashSet<Professor>) new ProfessorService().getAllProfessors();
		rooms = (HashSet<Room>) new RoomService().getAllRooms();
		accessories = (HashSet<Accessory>) new AccessoryService()
				.getAllAccessories();

		//populate the bldg_rooms map
		bldg_rooms = new HashMap<Building, Set<Room>>();
		for (Room r : rooms) {
			Building b = getBuildingById(r.getBuilding_id());
			Set<Room> rooms = bldg_rooms.get(b);
			if (rooms == null)
				rooms = new HashSet<Room>();
			rooms.add(r);
			bldg_rooms.put(b, rooms);
		}
		
		//initialize the unavailable for every professor
		for(Professor p: profs) p.initializeUnavailable();
		
		//initialize the reserved for room
		for(Room r: rooms) r.initializeReserved();
		
		/**
		 * TODO build HashSet<Room, Set<Accessory>) to assign accessories for
		 * the room Same for every section build HashSet<Department,
		 * Set<Courses>) to assign courses given by departments
		 */

	}

	/*
	 * public static void main(String[] args) { SetUp s = new SetUp();
	 * TimeSlot[] times = new TimeSlot[3]; times[0] = new TimeSlot();
	 * times[0].setDay(Day.M); times[0].setStart("0500");
	 * times[0].setEnd("0600"); times[1] = new TimeSlot();
	 * times[1].setDay(Day.T); times[1].setStart("0700");
	 * times[1].setEnd("0730"); times[2] = new TimeSlot();
	 * times[2].setDay(Day.F); times[2].setStart("0731");
	 * times[2].setEnd("0830"); System.out.println(s.rooms); Room myRoom = new
	 * Room(); myRoom.setId(2); myRoom.setNumber("322");
	 * myRoom.setRoom_capacity(30); myRoom.setBuilding_id(3);
	 * s.rooms.add(myRoom); Room myRoom2 = new Room(); myRoom2.setId(3);
	 * myRoom2.setNumber("3211111"); myRoom2.setRoom_capacity(10);
	 * myRoom2.setBuilding_id(10); s.rooms.add(myRoom2);
	 * System.out.println("BUILDINGS"+s.bldgs); for (Room r : s.rooms) {
	 * System.out
	 * .println("Similar Available ROOM for "+r+":\n "+s.getSimilar_available_rooms
	 * (r, times)); }
	 * 
	 * }
	 */

	// getteres
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


	public HashSet<Accessory> getAccessories() {
		return accessories;
	}

	// DONE
	/**
	 * Finds Buildings that have the same location as this DONE
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

	// DONE
	private Building getBuildingById(int id) {
		for (Building b : bldgs)
			if (b.getId() == id)
				return b;
		return null;
	}

	/**
	 * TODO 
	 * done by yasmin
	 * @return a room object that is available during the passed time slot
	 * @return NULL if no rooms are left during a certain time slot
	 * 
	 *         the choice of the room SHOULD respect the capacity i.e. the room
	 *         returned should be of capacity >= to the capacity of the passed
	 *         room
	 * 
	 *         should first check for available rooms in the same bldg if there
	 *         no rooms left check for the other buildings that have the same
	 *         location else check in other buildings
	 * 
	 *         You can change the params to getSimilar_available_rooms(Room
	 *         room, Time time)
	 */
	public Room getSimilar_available_rooms(Room room, TimeSlot[] times) {
		Set<Room> sameBuilding = get_all_rooms_in_same_building(room, times);
		Set<Room> nearBuilding = get_all_rooms_in_all_near_buildings(room,
				times);
		Set<Room> allRooms = get_all_rooms(room, times);
		if (!sameBuilding.isEmpty()) {
			for (Room r : sameBuilding) {
				if (Math.abs(r.getRoom_capacity() - room.getRoom_capacity()) < 5) {
					return r;
				} else if (Math.abs(r.getRoom_capacity()
						- room.getRoom_capacity()) < 10) {
					return r;
				}
			}

		} else if (!nearBuilding.isEmpty()) {
			for (Room r : nearBuilding) {
				if (Math.abs(r.getRoom_capacity() - room.getRoom_capacity()) < 5) {
					return r;
				} else if (Math.abs(r.getRoom_capacity()
						- room.getRoom_capacity()) < 10) {
					return r;
				}
			}
		} else if (!allRooms.isEmpty()) {
			for (Room r : allRooms) {
				if (Math.abs(r.getRoom_capacity() - room.getRoom_capacity()) < 5) {
					return r;
				} else if (Math.abs(r.getRoom_capacity()
						- room.getRoom_capacity()) < 10) {
					return r;
				}
			}
		}
		if (!sameBuilding.isEmpty()) {
			for (Room r : sameBuilding) {
				return r;
			}

		} else if (!nearBuilding.isEmpty()) {
			for (Room r : nearBuilding) {
				return r;
			}
		} else if (!allRooms.isEmpty()) {
			for (Room r : allRooms) {
				return r;
			}
		}

		return null;
	}

	//DONE
	/**
	 * DONE BY yasmin return all the rooms in the same building SHOULD respect
	 * the capacity and the type of the room
	 */
	public Set<Room> get_all_rooms_in_same_building(Room room, TimeSlot[] times) {
		Set<Room> sameBuilding = new HashSet<Room>();
		Building b = getBuildingById(room.getBuilding_id());
		for (Room r : bldg_rooms.get(b)) {
			if (r.getId() != room.getId() // not the same room
					&& r.getRoom_capacity() >= room.getRoom_capacity() // greater
																		// capacity
					&& r.getType() == room.getType()// same type
					&& room.is_available(times))// available
				sameBuilding.add(r);
		}
		return sameBuilding;
	}

	//DONE
	/**
	 * DONE yasmin return all rooms in near buildings SHOULD respect capacity
	 * and type SHOULD NOT return the rooms in the same building as the passes
	 * room
	 */
	public Set<Room> get_all_rooms_in_all_near_buildings(Room room,
			TimeSlot[] times) {
		Set<Room> nearBuildings = new HashSet<Room>();
		Set<Building> nearbs = getNearBuildings(getBuildingById(room
				.getBuilding_id()));
		for (Building b : nearbs) {
			for (Room r : bldg_rooms.get(b)) {
				if (r.getId() != room.getId() // not the same room
						&& r.getType() == room.getType() // same type
						&& r.getRoom_capacity() >= room.getRoom_capacity() // greater
																			// capacity
						&& room.is_available(times))// and available
					nearBuildings.add(r);
			}
		}
		return nearBuildings;
	}

	//DONE
	/**
	 * DONE yasmin SHOULD respect capacity and type SHOULD NOT return the rooms
	 * in the same building as the passes room neither the rooms in the nearby
	 * buildings
	 */
	public Set<Room> get_all_rooms(Room room, TimeSlot[] times) {
		Set<Room> allRooms = new HashSet<Room>();
		for (Room r : rooms) {
			if (r.getId() != room.getId()
					&& r.getType() == room.getType()
					&& r.getRoom_capacity() >= room.getRoom_capacity()
					&& r.getBuilding(bldgs).getLocation_id() != room//not in the same bldg
							.getBuilding(bldgs).getLocation_id()
					&& r.getBuilding_id() != room.getBuilding_id()//nor in its near buildgs
					&& room.is_available(times))
				allRooms.add(r);
		}
		return allRooms;
	}

	//DONE
	/**
	 * TODO julia returns 1 in case of success -1 in case of unavailable prof -2
	 * in case of unavailable room
	 * 
	 * @param c
	 * @return
	 */
	public int bestScheduleClass2(Class c) {
		Room r = c.getRoom();
		Time t = c.getTime();
		Professor p = c.getProfessor();

		boolean av_room = r.is_available(t.getTimeSlots());
		boolean av_prof = p.isAvailable(t);

		if (av_room && av_prof) {
			r.reserveRoom(t.getTimeSlots());
			p.addUnavailable(t);
			return 1;
		} else if (!av_prof) {
			return -1;
		} else
			return -2;
	}

	/**
	 * easy just reserve the room and the prof if both are available
	 * @param cl
	 * @return
	 */
	public boolean bestScheduleClass(Class cl) {
		Room r = cl.getRoom();
		Time t = cl.getTime();
		Professor p = cl.getProfessor();
		if (p.isAvailable(t)
				&& r.is_available(t.getTimeSlots())) {
			r.reserveRoom(t.getTimeSlots());
			p.addUnavailable(t);
			return true;
		} else
			return false;
	}

	/** julia
	 * @return
	 */
	public boolean secondScheduleClass(Class cl) {
		Room r = cl.getRoom();
		Time t = cl.getTime();
		Professor p = cl.getProfessor();
		
		Room r2 = getSimilar_available_rooms(r, t.getTimeSlots());
		if (r2 != null) {
			if (p.isAvailable(t)){
				r2.reserveRoom(t.getTimeSlots());
				p.addUnavailable(t);
				cl.setRoom(r2);
			}
			return true;
			/**
			 * TODO
			 */
		} else {// no rooms are left during this time slot
			
		}
		return true;
	}

	/**
	 * TODO with bilal
	 * 
	 * @return
	 */
	public TreeMap<Department, Set<Course>> getDeps_courses_map() {
		// TreeMap<Department, Set<Course>> map = new TreeMap<Department,
		// Set<Course>>(new DepartmentWeightComparator());
		return null;
	}

	public static void main(String[] args){
		SetUp setup = new SetUp();
	/*	Set<Building> bs = setup.getBldgs();
		System.out.println("BUILDINGS");
		for(Building b : bs){
			System.out.println(b);
		}
		*/
		
		Set<Course > courses =  setup.getCourses();
		System.out.println("COURSES");
		for(Course c: courses) System.out.println(c.getNbr_of_sections());
		
//	for(Class cl: setup.getClasses()) System.out.println(cl);
	//	for(Department d: setup.deps) System.out.println(d.getBuilding_id());
		
		//for(Professor p: setup.profs)System.out.println(p.getUnavailable());
		
		//for(Room r: setup.getRooms())System.out.println(r.getAccessories());
			
	//	for(Accessory a: setup.accessories) System.out.println(a);
		
	}
}
