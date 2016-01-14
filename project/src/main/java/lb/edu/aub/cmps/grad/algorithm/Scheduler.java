package lb.edu.aub.cmps.grad.algorithm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.ClassTimeComparator;
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
	protected int num_of_all_classes;
	protected Iterator<Class>[] its;
	protected TreeMap<Department, Set<Course>> requests_by_dep; // sorted by weight
																// since it
																// knows
																// how to sort
																// the
																// departments

	protected TreeMap<Department, Set<Class>> classes_by_dep;
	protected Map<Department, Set<Class>> scheduled_map;
	protected Map<Class, String> not_scheduled;
	
	public TreeMap<Department, Set<Course>> getRequests(){
		return requests_by_dep;
	}
	/**
	 * constructor that class instantiate the setup to fetch data from database
	 * @author Julia El Zini
	 */
	@SuppressWarnings("unchecked")
	public Scheduler(){
		setup = new SetUp();
		classes_by_dep = setup.getDeps_Classes_map();

		this.num_of_iterations = 10;
		this.requests_by_dep = setup.getDeps_courses_map();

		
		for (Department d : requests_by_dep.keySet()) {
			count_all_courses += d.getCourses_offered().size();
		}
		num_of_all_classes = setup.getNumOfClasses();
		num_of_iterations = 10;
		int size = classes_by_dep.keySet().size();
		its = new Iterator[size];
		int i = 0;
		scheduled_map = new TreeMap<Department, Set<Class>>(
				new DepartmentWeightComparator());
		for (Department d : classes_by_dep.keySet()) {
			its[i] = classes_by_dep.get(d).iterator();
			i++;
			scheduled_map.put(d, new TreeSet<Class>(new ClassTimeComparator()));
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
	
	public Collection<Room> getRooms(){
		return setup.getId_RoomMap().values();
	}
	
	/**
	 * abstract method where the scheduling should be done
	 */
	public abstract Map<Class, String> schedule();

	public Map<Class, String> getNotScheduled(){
		return not_scheduled;
	}
}
