package lb.edu.aub.cmps.grad.algorithm;

import java.util.Collection;
import java.util.HashMap;
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

/**
 * Abstract class implementing IScheduler. Has an instance of SetUp i.e. fetches
 * requests from the database. Has an abstract method schedule() Where all the
 * scheduling should be done according to the class that extends it
 * 
 * @author Julia El Zini
 */
public abstract class Scheduler implements IScheduler {
	protected SetUp setup;
	protected int num_of_iterations;
	protected int count_all_courses;
	protected int num_of_all_classes;
	protected Iterator<Class>[] its;
	protected TreeMap<Department, Set<Course>> requests_by_dep; // sorted by
																// weight
																// since it
																// knows
																// how to sort
																// the
																// departments

	protected TreeMap<Department, Set<Class>> classes_by_dep;
	protected Map<Department, Set<Class>> scheduled_map;
	protected Map<Class, String> not_scheduled;

	protected double overallStat;
	protected Map<Department, Double> statByDep;

	public TreeMap<Department, Set<Course>> getRequests() {
		return requests_by_dep;
	}

	/**
	 * constructor that class instantiate the setup to fetch data from database
	 * 
	 * @author Julia El Zini
	 */
	@SuppressWarnings("unchecked")
	public Scheduler(String term) {
		setup = new SetUp(Integer.parseInt(term));
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
	public Map<Department, Set<Class>> getScheduled() {
		return scheduled_map;
	}

	public Map<Integer, Room> getIdRoomMap() {
		return setup.getId_RoomMap();
	}

	public SetUp getSetup() {
		return setup;
	}

	/**
	 * @return a map of Department as key and the set of courses offered by the
	 *         department as value Needed for the output
	 * @author Julia El Zini
	 */
	public abstract Map<Department, Set<Course>> getDepCoursesMap();

	public Collection<Room> getRooms() {
		return setup.getId_RoomMap().values();
	}

	/**
	 * abstract method where the scheduling should be done
	 */
	public abstract Map<Class, String> schedule();

	public Map<Class, String> getNotScheduled() {
		return not_scheduled;
	}

	/**
	 * @author Julia
	 * @return a map from a department to a double denoting the percentage of
	 *         requests that were satisfied
	 */
	public Map<Department, Double> getStatisticsByDepartment() {

		statByDep = new HashMap<Department, Double>();
		for (Department d : scheduled_map.keySet()) {
			if (d.getNum_of_classes() != 0) { // only for the departments that
												// actually have classes
				double met = 0;
				for (Class c : scheduled_map.get(d)) {
					if (c.getIsMet())
						met++;
				}
				statByDep.put(d, met / d.getNum_of_classes());
				overallStat += met;
			}
		}
		overallStat = overallStat * 1.0 / num_of_all_classes;
		return statByDep;
	}

	/**
	 * @return a double denoting the overall percentage of requests that were
	 *         satisfied
	 * @author Julia
	 */

	public double getOverallStatistics() {
		return overallStat;
	}
}
