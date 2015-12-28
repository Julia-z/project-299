package lb.edu.aub.cmps.grad.algorithm;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.DepartmentWeightComparator;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.Section;

/**
 * Abstract class implementing IScheduler.
 * Has an instance of SetUp i.e. fetches requests from the database.
 * Has an abstract method schedule() Where all the scheduling should be done 
 * according to the class that extends it
 * 
 * @author Julia El Zini
 */
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
	 * constructor that class instantiate the setup to fetch data from database
	 * @author Julia El Zini
	 */
	public Scheduler(){
		setup = new SetUp();
		this.num_of_iterations = 10;
		this.requests_by_dep = setup.getDeps_courses_map();

		
		for (Department d : requests_by_dep.keySet()) {
			count_all_courses += d.getCourses_offered().size();
		}
	}
	
	/**
	 * @return the schedule: a map between the departments and their
	 *         corresponding set of classes
	 */
	public Map<Department, Set<Class>> getScheduled(){
		return scheduled_map;
	}
	public Map<Integer, Room> getIdRoomMap(){
		return setup.getId_RoomMap();
	}
	public SetUp getSetup(){
		return setup;
	}
	
	/**
	 * @return a map of Department as key and the set of courses offered by the department as value
	 *  Needed for the output
	 *  @author Julia El Zini
	 */
	public Map<Department, Set<Course>> getDepCoursesMap(){
		Map<Department, Set<Course>> map = new TreeMap<Department, Set<Course>>(Collections.reverseOrder(new DepartmentWeightComparator()));
		for(Integer dep_id: setup.getId_dep().keySet()){
			map.put(setup.getId_dep().get(dep_id), new HashSet<Course>());
		}
		for(Integer course_id: setup.getId_course().keySet()){
			Course c = setup.getId_course().get(course_id);
			for(Integer i: c.getSectionNbrs()){
				Section s = new Section(c.getDepartment(), setup.getId_dep().get(c.getDepartment()).getName(), i, c.getCourse_name(), c.getCourse_id());
				if(c.getClasses()!=null){
					for(Class cl : c.getClasses()){
						if(cl.getSection_number().contains(i)) s.addClass(cl);
					}
					c.addSection(s);
				}
			}
			map.get(setup.getId_dep().get(c.getDepartment())).add(c);
		}
		return map;
	}
	
	/**
	 * abstract method where the scheduling should be done
	 */
	public abstract Map<Class, String> schedule();
}
