package lb.edu.aub.cmps.grad.algorithm;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import lb.edu.aub.cmps.grad.classes.Building;
import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.ClassTimeComparator;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.DepartmentWeightComparator;
import lb.edu.aub.cmps.grad.classes.MyLogger;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.Time;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.services.AccessoryService;
import lb.edu.aub.cmps.grad.services.BuildingService;
import lb.edu.aub.cmps.grad.services.ClassService;
import lb.edu.aub.cmps.grad.services.CourseService;
import lb.edu.aub.cmps.grad.services.DepartmentService;
import lb.edu.aub.cmps.grad.services.ProfessorService;
import lb.edu.aub.cmps.grad.services.RoomService;

/**
 * A class that retrieves all the requested classes from the database specified
 * in the jdbc.properties file in src/main/resources.
 * 
 * The getters provide all the necessary data types filled with data from the
 * database for the scheduler to work.
 * 
 * The class is also provided with some methods used in the scheduling:
 * get_all_rooms_in_all_near_buildings() get_all_rooms_in_all_near_buildings()
 * best_schedule_class() otherAvailableRoom() otherAvailableTime()
 * 
 * @author Julia El Zini
 * @author Yasmin Kadah
 */

public class SetUp {
	private HashSet<Building> bldgs;
	private HashSet<Class> classes;
	private HashSet<Department> deps;
	private HashSet<Course> courses;
	private HashSet<Professor> profs;
	private HashSet<Room> rooms;
	private HashSet<Integer> accessories;

	private HashMap<Integer, Building> id_bldg;
	private HashMap<Integer, Class> id_class;
	private HashMap<Integer, Department> id_dep;
	private HashMap<Integer, Course> id_course;
	private HashMap<Integer, Professor> id_prof;
	private HashMap<Integer, Room> id_room;

	private HashMap<Building, Set<Integer>> bldg_rooms;

	private TreeMap<Department, Set<Course>> deps_courses_map;
	private TreeMap<Department, Set<Class>> deps_classes_map;
	private MyLogger loggerWrapper;

	/**
	 * constructor that initializes all the data types and fills them with the
	 * necessary data fetched from the database

	 * @author Julia El Zini
	 */
	public SetUp() {
		try {
			loggerWrapper = MyLogger.getInstance();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Logger log = loggerWrapper.getLogger();

		// buildings
		log.info("Fetching buildings...");
		bldg_rooms = new HashMap<Building, Set<Integer>>();
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
			bldg_rooms.get(id_bldg.get(r.getBuilding_id())).add(r.getId());
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
		setId_course(new HashMap<Integer, Course>());
		for (Course c : courses) {
			getId_course().put(c.getCourse_id(), c);
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

			int course_id = c.getCourse_id();
			c.setCourse_name(getId_course().get(course_id).getCourse_name());
			Course course = getId_course().get(course_id);
			course.addClass(c);
			Room r = c.getRequestedRoom();
			// handle the room not given case
			if (r == null) {
				Building b = id_bldg.get(id_bldg.get(id_dep.get(
						id_course.get(c.getCourse_id()).getDepartment())
						.getBuilding_id()));
				for (Integer id : bldg_rooms.get(b)) {
					Room room2 = id_room.get(id);
					if (room2 != null) {
						r = room2;
						break;
					}
				}
			}
			// id_dep.put(dep_id, d);
			// id_course.put(course_id, course);
		}
		for (Course course : getId_course().values()) {
			int dep_id = course.getDepartment();
			Department d = id_dep.get(dep_id);
			d.addCourse(course);
		}
		log.info("Mapping classes to their corresponding courses. Done");

		log.info("Mapping courses to departments...");
		for (Department d : deps) {
			deps_courses_map.put(d, d.getCourses_offered());
			Set<Class> classes = new TreeSet<Class>(new ClassTimeComparator());
			for (Course c : deps_courses_map.get(d)) {
				if (c.getClasses() != null)
					classes.addAll(c.getClasses());
			}
			deps_classes_map.put(d, classes);
			d.setNumOfClasses(classes.size());
		}
		log.info("Mapping courses to departments. Done");
	}

	/**
	 * Finds Buildings that have the same location as the passed bldg
	 * 
	 * @param b the building to search for near by
	 * @author yasmin Kadah
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
	 *         returned should be of capacity greater than or equal to the capacity of the passed
	 *         room
	 * 
	 *         should first check for available rooms in the same bldg if there
	 *         no rooms left check for the other buildings that have the same
	 *         location else check in other buildings
	 * @author Julia El Zini
	 * @author Yasmin Kadah
	 * @param room the room to search for its similar 
	 * @param times the time during which the room should be available
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

	/**
	 * @author Yasmin Kadah
	 * @return all the rooms in the same building SHOULD respect the capacity
	 *         and the type of the room
	 *  
	 * @param room the room to search for its similar 
	 * @param times the time during which the room should be available
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
					&& room.is_available(times)) {// available
				sameBuilding.add(r);
			}
		}
		return sameBuilding;
	}

	/**
	 * @author Yasmin Kadah
	 * @param room the room to search for its similar
	 * 
	 * @param times the time slots during which the room should be available
	 * @return all rooms in near buildings SHOULD respect capacity and type
	 *         SHOULD NOT return the rooms in the same building as the passes
	 *         room
	 */
	public Set<Room> get_all_rooms_in_all_near_buildings(Room room,
			TimeSlot[] times) {
		Set<Room> nearBuildings = new HashSet<Room>();
		Set<Building> nearbs = getNearBuildings(id_bldg.get(room
				.getBuilding_id()));

		for (Building b : nearbs) {
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

	/**
	 * @author Yasmin Kadah
	 * @return SHOULD respect capacity and type SHOULD NOT return the rooms in
	 *         the same building as the passes room neither the rooms in the
	 *         nearby buildings
	 * @param room the room to search for its similar
	 * @param times the time slots during which the room should be available
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

	/**
	 * @author Julia El Zini
	 * @param c the class to schedule
	 * @return 1 in case of success -1 in case of unavailable prof -2 in case of
	 *         unavailable room
	 */
	public int bestScheduleClass(Class c) {
		Room r = c.getRequestedRoom();
		r = id_room.get(r.getId());

		Time t = c.getRequestedTime();
		Set<Professor> ps = c.getProfessors();
		if (ps != null && !ps.isEmpty()){
			Set<Professor> ps2 = new HashSet<Professor>();
			for(Professor p: ps){
				if(p!=null){
					System.out.println("ID: " + p.getId());
					System.out.println("id_prof.get(p.getId()) --> " + id_prof.get(p.getId()));
					
					ps2.add(id_prof.get(p.getId()));
				}
			}
			
			ps = ps2;
		}

		boolean av_room = r.is_available(t.getTimeSlots());
		boolean av_prof = true;
		if (ps == null) av_prof = true;
		else{
			for(Professor p: ps)
				if(!p.isAvailable(t))
					av_prof = false;
		}

		if (av_room && av_prof) {
			reserve(ps, r, t, c);
			c.setIsMet(true);
			return 1;
		} else if (!av_prof) {
			return -1;
		} else
			return -2;
	}

	/**
	 * @author Julia El Zini
	 * 
	 * @param cl the class to search for available rooms for 
	 * @return another room that is similar to the room requested by cl and
	 *         which is available during cl.requestedTime
	 */
	
	
	public Room otherAvailableRoom(Class cl) {
		Room room = cl.getRequestedRoom();
		Time t = cl.getRequestedTime();
		Set<Professor> ps = cl.getProfessors();
		if (ps != null){
			Set<Professor> ps2 = new HashSet<Professor>();
			for(Professor p: ps){
				if(p!=null)
					ps2.add(id_prof.get(p.getId()));
			}
			ps = ps2;
		}
		for (Room room2 : get_all_rooms_in_same_building(room, t.getTimeSlots())) {
			if (room2.is_available(t.getTimeSlots())
					&& room2.hasAccessory(cl.getAccessoriesIds())) {
				if(ps == null) return room2;
				else{
					boolean av = true;
					for(Professor p: ps) 
						if(!p.isAvailable(t))av = false;
					if(av) return room2;
				}
			}
		}
		for (Room room2 : get_all_rooms_in_all_near_buildings(room,
				t.getTimeSlots()))
			if (room2.is_available(t.getTimeSlots())
					&& room2.hasAccessory(cl.getAccessoriesIds())) {
				if(ps == null ) return room2;
				else{
					boolean av = true;
					for(Professor p: ps) 
						if (!p.isAvailable(t)) av = false;
					if(av)return room2;
				}
				
			}
		for (Room room2 : get_all_rooms(room, t.getTimeSlots()))
			if (room2.is_available(t.getTimeSlots())
					&& room2.hasAccessory(cl.getAccessoriesIds())) {
				if(ps == null ) return room2;
				else{
					boolean av = true;
					for(Professor p: ps) 
						if (!p.isAvailable(t)) av = false;
					if(av)return room2;
				}
			}
		return null;
	}

	/**
	 * @param cl the class
	 * @return available times
	 */
	/*
	public Time otherAvailableTime(Class cl) {
		return otherAvailableTime(cl, cl.getRequestedRoom());
	}
*/
	/**
	 * @author Julia El Zini
	 * 
	 * @param cl the class 
	 * @param r the room t start the search from
	 * @return the nearest time t2 to t1 such that t1 is the requested time for
	 *         the class cl and that the room r is available during t2
	 */
	
	/*
	public Time otherAvailableTime(Class cl, Room r) {
		Time t = cl.getRequestedTime();
		r = id_room.get(cl.getRequestedRoom().getId());
		Professor p = cl.getProfessor();
		if (p != null)
			p = id_prof.get(cl.getProfessor().getId());

		boolean done = false;

		Time next = t.nextTime();
		Time prev = t.previousTime();

		if (next == null) {
			while (!done && prev != null) {
				// check the prof and the room during previous time
				if (r.is_available(prev.getTimeSlots())) {
					if ((p == null) || (p != null && p.isAvailable(next)))
						done = true;
					return prev;
				}
				prev = prev.previousTime();
			}
			return null;
		} else if (prev == null) {
			while (!done && next != null) {
				if (r.is_available(next.getTimeSlots())) {
					if ((p == null) || (p != null && p.isAvailable(prev)))
						done = true;
					return prev;
				}
				next = next.nextTime();
			}
			return null;
		}
		// next and prev are both not null
		else {
			while (!done) {
				// try to schedule it next
				if (next != null && (r.is_available(next.getTimeSlots()))) {
					if ((p == null) || (p != null && p.isAvailable(next))) {
						done = true;
						return next;
					}
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
					return null;
			}
		}
		return null;
	}
*/
	/**
	 * Changes room for the given class
	 * 
	 * @param cl the class 
	 * @return true if scheduled
	 * @author Julia El ZIni
	 */
	public boolean changeRoom(Class cl) {
		Time t = cl.getRequestedTime();
		Set<Professor> ps = cl.getProfessors();
		if (ps != null){
			Set<Professor> ps2 = new HashSet<Professor>();
			for(Professor p: ps){
				if(p!=null)
					ps2.add(id_prof.get(p.getId()));
			}
			ps = ps2;
			
		}
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
						reserve(ps, room2, t, cl);
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
			}
			step++;

		}
		return scheduled;
	}

	/**
	 * 
	 * @param cl the class for which the time should be changed
	 * @return true if the time of the class cl could be changed
	 */
	public boolean changeTime(Class cl) {
		return changeTime(cl, cl.getRequestedRoom());
	}

	/**
	 * Changes time of the class in a given room
	 * 
	 * @param cl the class for which the time should be changed
	 * @param r the room to start with
	 * @return true if scheduled
	 */
	public boolean changeTime(Class cl, Room r) {
		Time t = cl.getRequestedTime();
		r = id_room.get(cl.getRequestedRoom().getId());
		Set<Professor> ps = cl.getProfessors();
		if (ps != null){
			Set<Professor> ps2 = new HashSet<Professor>();
			for(Professor p: ps){
				ps2.add(id_prof.get(p.getId()));
			}
			ps = ps2;
		}

		boolean done = false;
		Time next = t.nextTime();
		/** this code works **/
		/*
		 * while(next != null && !done){
		 * if(r.is_available(next.getTimeSlots())){ if(p != null &&
		 * p.isAvailable(next)) { done = true; reserve(p, r, next, cl);
		 * cl.setIsMet(false); } } next = next.nextTime(); } if(!done) return
		 * false;
		 */
		Time prev = t.previousTime();

		if (next == null) {
			while (!done && prev != null) {
				// check the prof and the room during previous time
				if (r.is_available(prev.getTimeSlots())) {
					boolean av = true;
					if(ps == null || ps.isEmpty()) av = true;
					else{
						for(Professor p: ps){
							if(!p.isAvailable(prev)) av = false;
						}
					}
					if(av){
						reserve(ps, r, prev, cl);
	
						done = true;
						return true;
					}
				}
				prev = prev.previousTime();
			}
		} else if (prev == null) {
			while (!done && next != null) {
				if ( r.is_available(next.getTimeSlots())) {
					boolean av = true;
					if(ps == null || ps.isEmpty()) av = true;
					else{
						for(Professor p: ps){
							if(!p.isAvailable(next)) av = false;
						}
					}
					if(av){
						reserve(ps, r, next, cl);
						done = true;
						return true;
					}
				}
				next = next.nextTime();
			}
		}
		// next and prev are both not null
		else {
			while (!done) {
				// try to schedule it next
				if (next != null
						&& (r.is_available(next
								.getTimeSlots()))) {
					boolean av = true;
					if(ps == null || ps.isEmpty()) av = true;
					else{
						for(Professor p: ps){
							if(!p.isAvailable(next)) av = false;
						}
					}
					if(av){
						reserve(ps, r, next, cl);
	
						done = true;
					}
				}
				// try to schedule it prev
				else if (prev != null
						&& (r.is_available(prev.getTimeSlots()))) {
					boolean av = true;
					if(ps == null || ps.isEmpty()) av = true;
					else{
						for(Professor p: ps){
							if(!p.isAvailable(t)) av = false;
						}
					}
					if(av){
						reserve(ps, r, prev, cl);
						done = true;
					}
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
	/**
	 * @author Julia El Zini
	 * 
	 * @param c_to_sched the class to schedule
	 * @return true if the time and the rooom could be changed
	 */
	
	public boolean changeTimeAndRoom(Class c_to_sched) {
		Room r2 = otherAvailableRoom(c_to_sched);
		if (r2 == null)
			return false;
		else {

		}
		return false;
	}

	/**
	 * Reserves a class with a given Professor, Room and Time
	 * 
	 * @param p the professor giving the class
	 * @param r the room r granted for the class
	 * @param t the time  for the class
	 * @param c the class 
	 * @author Julia El Zini
	 */
	public void reserve(Set<Professor> ps, Room r, Time t, Class c) {
		c.setGiven_time(t);
		c.setGiven_room(r);
		if (ps != null) {
			for(Professor p: ps){
				p.addClass(c);
				p.addUnavailable(c.getGivenTime());
			}
		}
		r.reserveRoom(t.getTimeSlots(), c);
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

	public TreeMap<Department, Set<Course>> getDeps_courses_map() {
		return deps_courses_map;
	}

	public TreeMap<Department, Set<Class>> getDeps_Classes_map() {
		return deps_classes_map;
	}

	public HashMap<Building, Set<Integer>> getBldgs_rooms_map() {
		return bldg_rooms;
	}

	public Map<Integer, Room> getId_RoomMap() {
		return id_room;
	}

	public HashMap<Integer, Course> getId_course() {
		return id_course;
	}

	public void setId_course(HashMap<Integer, Course> id_course) {
		this.id_course = id_course;
	}

	public HashMap<Integer, Department> getId_dep() {
		return id_dep;
	}

	public Map<Integer, Class> getId_class() {
		return id_class;
	}
	
	//Done for the enhanced scheduler
	/**
	 * TODO Bilal
	 * @return
	 */
	public Map<Department, Set<Class>> getTime_fixed_classes_by_dep(){
		return null;
	}
	public Map<Department, Set<Class>> getLoc_fixed_classes_by_dep(){
		return null;
	}
	public Map<Department, Set<Class>> getGrad_classes_by_dep(){
		return null;
	}
	public Map<Department, Set<Class>> getLower_Lec_by_dep(){
		return null;
	}
	public Map<Department, Set<Class>> getUpper_Lec_by_dep(){
		return null;
	}
	public Map<Department, Set<Class>> getLower_rec_by_dep(){
		return null;
	}
	public Map<Department, Set<Class>> getUpper_rec_by_dep(){
		return null;
	}
	

}
