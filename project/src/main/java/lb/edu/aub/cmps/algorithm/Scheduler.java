package lb.edu.aub.cmps.algorithm;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Section;

public abstract class Scheduler implements IScheduler{
	protected SetUp setup;
	protected int num_of_iterations;
	protected int count_all_courses;
	protected TreeMap<Department, Set<Course>> requests_by_dep; // sorted by weight
																// since it
																// knows
																// how to sort
																// the
																// departments

	protected TreeMap<Department, TreeSet<Class>> classes_req_by_dep;
	protected Map<Department, Set<Class>> scheduled_map;

	
	public TreeMap<Department, Set<Course>> getRequests(){
		return requests_by_dep;
	}
	/**
	 * constructor
	 * @param num_of_iterations
	 * @throws IOException 
	 * @throws SecurityException 
	 */
	public Scheduler() throws SecurityException, IOException {
		setup = new SetUp();
		this.num_of_iterations = 10;
		this.requests_by_dep = setup.getDeps_courses_map();

		
		for (Department d : requests_by_dep.keySet()) {
			count_all_courses += d.getCourses_offered().size();
		}
	}
	public Map<Department, Set<Class>> getScheduled(){
		return scheduled_map;
	}
	public Map<Integer, Room> getIdRoomMap(){
		return setup.getId_RoomMap();
	}
	public abstract Map<Department, Set<Section>> getDep_Sections();
	public abstract Map<Class, String> schedule();
}
