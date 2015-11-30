package lb.edu.aub.cmps.algorithm;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import lb.edu.aub.cmps.classes.Building;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.ClassTimeComparator;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.DepartmentWeightComparator;
import lb.edu.aub.cmps.classes.MyLogger;
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
	private HashSet<Building> bldgs;
	private HashSet<Class> classes; // still needs many fields
	private HashSet<Department> deps;
	private HashSet<Course> courses; // still needs the classes
	private HashSet<Professor> profs;
	private HashSet<Room> rooms;// still needs the type
	private HashSet<Integer> accessories;

	HashMap<Integer, Building> id_bldg;
	HashMap<Integer, Class> id_class;
	HashMap<Integer, Department> id_dep;
	HashMap<Integer, Course> id_course;
	HashMap<Integer, Professor> id_prof;
	HashMap<Integer, Room> id_room;

	HashMap<Building, Set<Integer>> bldg_rooms;

	TreeMap<Department, Set<Course>> deps_courses_map;
	TreeMap<Department, Set<Class>> deps_classes_map;
	private MyLogger loggerWrapper;

	// fills in the needed sets before starting the computation
	public SetUp() throws SecurityException, IOException {
		loggerWrapper = MyLogger.getInstance();
		Logger log = loggerWrapper.getLogger();

		// buildings
		log.info("Fetching buildings...");
		bldg_rooms = (HashMap<Building, Set<Integer>>) new HashMap<Building, Set<Integer>>();
		bldgs = (HashSet<Building>) new BuildingService().getAllBuildings();

		id_bldg = new HashMap<Integer, Building>();
		for (Building b : bldgs) {
			id_bldg.put(b.getId(), b);
			bldg_rooms.put(b, new HashSet<Integer>());
		}
		log.info("Buildings retrieved");

		// departments
		log.info("Fetching Departments...");
		deps = (HashSet<Department>) new DepartmentService()
				.getAllDepartments();
		id_dep = new HashMap<Integer, Department>();
		for (Department d : deps) {
			d.setCourses_offered(new HashSet<Course>());
			id_dep.put(d.getId(), d);
		}
		log.info("Departments retrieved");

		// professors
		log.info("Fetching Professors...");
		profs = (HashSet<Professor>) new ProfessorService().getAllProfessors();
		id_prof = new HashMap<Integer, Professor>();
		for (Professor p : profs) {
			p.initializeUnavailable();
			id_prof.put(p.getId(), p);
		}
		log.info("Professors retrieved");

		// rooms
		log.info("Fetching Rooms...");
		rooms = (HashSet<Room>) new RoomService().getAllRooms();
		id_room = new HashMap<Integer, Room>();
		for (Room r : rooms) {
			r.initializeReserved();
			id_room.put(r.getId(), r);
		}
		log.info("Rooms retrieved");

		// accessories
		log.info("Fetching Accessories...");
		accessories = (HashSet<Integer>) new AccessoryService()
				.getAllAccessories();
		log.info("Accessories retrieved");

		// classes
		log.info("Fetching Classes...");
		classes = (HashSet<Class>) new ClassService().getAllClasses();
		id_class = new HashMap<Integer, Class>();
		for (Class c : classes) {
			id_class.put(c.getClass_id(), c);
		}
		log.info("Classes retrieved");

		deps_courses_map = new TreeMap<Department, Set<Course>>(
				Collections.reverseOrder(new DepartmentWeightComparator()));
		deps_classes_map = new TreeMap<Department, Set<Class>>(
				Collections.reverseOrder(new DepartmentWeightComparator()));

		log.info("Fetching Courses...");
		courses = (HashSet<Course>) new CourseService().getAllCourses();
		id_course = new HashMap<Integer, Course>();
		for (Course c : courses) {
			id_course.put(c.getCourse_id(), c);
			int dep_id = c.getDepartment();
			Department d = id_dep.get(dep_id);
			d.addCourse(c);
			Set<Course> old = deps_courses_map.get(d);
			if (old == null)
				old = new HashSet<Course>();
			old.add(c);
			// deps_courses_map.put(d, old);
		}
		log.info("Courses retrieved");

		log.info("Mapping classes to their corresponding courses...");
		for (Class c : id_class.values()) {
			//TODO remove these two lines if the booleans are read from the database
			c.setCanChangeRoom(true);//by default
			c.setCanChangeRoom(true);//by default
			int course_id = c.getCourse_id();
			c.setCourse_name(id_course.get(course_id).getCourse_name());
			Course course = id_course.get(course_id);
			course.addClass(c);
			
			// id_dep.put(dep_id, d);
			// id_course.put(course_id, course);
		}
		for(Course course: id_course.values()){
			int dep_id = course.getDepartment();
			Department d = id_dep.get(dep_id);
			d.addCourse(course);
		}
		log.info("Mapping classes to their corresponding courses. Done");

		// ____________________________________________________________________________________

		// initialize the deps_courses_map;

		log.info("Mapping courses to departments...");
		for (Department d : deps) {
			deps_courses_map.put(d, d.getCourses_offered());
			Set<Class> classes = new TreeSet<Class>(new ClassTimeComparator());
			for (Course c : deps_courses_map.get(d)) {
				if (c.getClasses() != null)
					classes.addAll(c.getClasses());
			}
			deps_classes_map.put(d, classes);
		}
		log.info("Mapping courses to departments. Done");
	}

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

	public HashSet<Integer> getAccessories() {
		return accessories;
	}

	public int getNumOfClasses() {
		return classes.size();
	}

	// DONE
	/**
	 * Finds Buildings that have the same location as the passed bldg DONE
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

	/**
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

	// DONE
	/**
	 * DONE BY yasmin return all the rooms in the same building SHOULD respect
	 * the capacity and the type of the room
	 */
	public Set<Room> get_all_rooms_in_same_building(Room room, TimeSlot[] times) {
		Set<Room> sameBuilding = new HashSet<Room>();
		Building b = id_bldg.get(room.getBuilding_id());
		for (Integer id : bldg_rooms.get(b)) {
			Room r = id_room.get(id);
			if (r.getId() != room.getId() // not the same room
					&& r.getRoom_capacity() >= room.getRoom_capacity() // greater
																		// capacity
					&& r.getType() == room.getType()// same type
					&& room.is_available(times))// available
				sameBuilding.add(r);
		}
		return sameBuilding;
	}

	/**
	 * DONE yasmin return all rooms in near buildings SHOULD respect capacity
	 * and type SHOULD NOT return the rooms in the same building as the passes
	 * room
	 */
	public Set<Room> get_all_rooms_in_all_near_buildings(Room room,
			TimeSlot[] times) {
		Set<Room> nearBuildings = new HashSet<Room>();
		Set<Building> nearbs = getNearBuildings(id_bldg.get(room
				.getBuilding_id()));
		System.out.println("near buildings: " + nearbs);

		for (Building b : nearbs) {
			System.out.println("bld_rooms.keySet().size(): "
					+ (bldg_rooms.keySet().size()));
			for (Integer id : bldg_rooms.get(b)) {
				Room r = id_room.get(id);
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

	// DONE
	/**
	 * DONE yasmin SHOULD respect capacity and type SHOULD NOT return the rooms
	 * in the same building as the passes room neither the rooms in the nearby
	 * buildings
	 */
	public Set<Room> get_all_rooms(Room room, TimeSlot[] times) {
		Set<Room> allRooms = new HashSet<Room>();
		for (Room r : rooms) {
			if (r.getId() != room.getId() && r.getType() == room.getType()
					&& r.getRoom_capacity() >= room.getRoom_capacity()
					&& r.getBuilding(bldgs).getLocation_id() != room// not in
																	// the same
																	// bldg
							.getBuilding(bldgs).getLocation_id()
					&& r.getBuilding_id() != room.getBuilding_id()// nor in its
																	// near
																	// buildgs
					&& room.is_available(times))
				allRooms.add(r);
		}
		return allRooms;
	}

	// DONE
	/**
	 * julia returns 1 in case of success -1 in case of unavailable prof -2 in
	 * case of unavailable room
	 * 
	 * @param c
	 * @return
	 */
	public int bestScheduleClass(Class c) {
		Room r = c.getRequestedRoom();
		Time t = c.getRequestedTime();
		Professor p = c.getProfessor();

		boolean av_room = r.is_available(t.getTimeSlots());
		boolean av_prof = (p == null) ? true : p.isAvailable(t);

		if (av_room && av_prof) {
			reserve(p, r, t, c);
			c.setIsMet(true);
			return 1;
		} else if (!av_prof) {
			return -1;
		} else
			return -2;
	}

	/**
	 * julia
	 * 
	 * @return
	 */
	public boolean changeRoom(Class cl) {
		Time t = cl.getRequestedTime();
		Professor p = id_prof.get(cl.getProfessor().getId());
		/*
		 * Room r2 = getSimilar_available_rooms(r, t.getTimeSlots()); if (r2 !=
		 * null) { if (p.isAvailable(t)) { reserve(p, r, t, cl); } return true;
		 * 
		 * } else {// no rooms are left during this time slot
		 */
		// we should change the time slot
		int step = 1;
		boolean scheduled = false;
		Set<Room> rooms = get_all_rooms_in_same_building(cl.getRequestedRoom(),
				cl.getRequestedTime().getTimeSlots());
		while (!scheduled && step < 3) {
			// there are rooms => try to schedule
			if (rooms != null) {
				for (Room room2 : rooms) {
					if (room2.hasAccessory(cl.getAccessoriesIds())) {
						reserve(p, room2, t, cl);
						cl.setGiven_room(room2);
						cl.setGiven_time(t);
						scheduled = true;
						break;
					}
				}
			} else {
				if (step == 1)
					rooms = get_all_rooms_in_all_near_buildings(
							cl.getRequestedRoom(), cl.getRequestedTime()
									.getTimeSlots());
				else if (step == 2)
					rooms = get_all_rooms(cl.getRequestedRoom(), cl
							.getRequestedTime().getTimeSlots());
				step++;
			}
		}
		return scheduled;
	}

	public boolean changeTime(Class cl) {
		Time t = cl.getRequestedTime();
		Room r = cl.getRequestedRoom();
		Professor p = id_prof.get(cl.getProfessor().getId());
		boolean done = false;
		Time next = t.nextTime();
		Time prev = t.previousTime();
		if (next == null) {
			while (!done && prev != null) {
				// check the prof and the room during previous time
				if (p.isAvailable(prev) && r.is_available(prev.getTimeSlots())) {
					reserve(p, r, prev, cl);

					done = true;
					return true;
				}
				prev = prev.previousTime();
			}
		} else if (prev == null) {
			while (!done && next != null) {
				if (p.isAvailable(next) && r.is_available(next.getTimeSlots())) {
					reserve(p, r, next, cl);
					done = true;
					return true;
				}
			}
		}
		// next and prev are both not null
		else {
			while (!done) {// TODO need to add more
				// try to schedule it next
				if (next != null
						&& (p.isAvailable(next) && r.is_available(next
								.getTimeSlots()))) {
					reserve(p, r, next, cl);

					done = true;
				}
				// try to schedule it prev
				else if (prev != null
						&& (p.isAvailable(prev) && r.is_available(prev
								.getTimeSlots()))) {
					reserve(p, r, prev, cl);

					done = true;
				}

				// change the next and the prev
				if (next != null)
					next = next.nextTime();
				if (prev != null)
					prev = prev.previousTime();
				if (next == null && prev == null)
					return false;
			}
		}

		// }
		return true;
	}

	public boolean changeRoomAndTime() {
		return false;
	}

	public void reserve(Professor p, Room r, Time t, Class c) {
		if (p != null) {
			p.addClass(c);
			p.addUnavailable(c.getGivenTime());
			id_prof.put(p.getId(), p);

		}

		r.reserveRoom(t.getTimeSlots(), c);
		id_room.put(r.getId(), r);
		c.setGiven_time(t);
		c.setGiven_room(r);
	}

	public TreeMap<Department, Set<Course>> getDeps_courses_map() {
		return deps_courses_map;
	}

	public TreeMap<Department, Set<Class>> getDeps_Classes_map() {
		return deps_classes_map;
	}

	public HashMap<Building, Set<Integer>> getBldgs_rooms_map() {
		return bldg_rooms;
	}

	public static void main(String[] args) throws SecurityException,
			IOException {
		SetUp s = new SetUp();

		for (Class c : s.classes) {
			System.out.println(c.getAccessoriesIds());
		}

		/*
		 * HashMap<Integer, Department> deps = s.id_dep; HashMap<Integer,
		 * Building> bldgs = s.id_bldg; HashMap<Integer, Class> classes =
		 * s.id_class; HashMap<Integer, Course> courses = s.id_course;
		 * HashMap<Integer, Professor> profs = s.id_prof; HashMap<Integer, Room>
		 * rooms = s.id_room; int c = 0; for(Department d:
		 * s.deps_classes_map.keySet()){
		 * System.out.println(d.getId()+">"+s.deps_classes_map.get(d).size());
		 * c+= s.deps_classes_map.get(d).size(); } System.out.println(c);
		 * 
		 * //System.out.println(rooms.size());
		 */

	}
}
