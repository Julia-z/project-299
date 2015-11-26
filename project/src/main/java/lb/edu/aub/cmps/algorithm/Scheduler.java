package lb.edu.aub.cmps.algorithm;

import java.util.Set;
import java.util.TreeMap;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;

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

	public TreeMap<Department, Set<Course>> getRequests(){
		return requests_by_dep;
	}
	/**
	 * constructor
	 * @param num_of_iterations
	 */
	public Scheduler() {
		setup = new SetUp();
		this.num_of_iterations = 10;
		this.requests_by_dep = setup.getDeps_courses_map();

		
		for (Department d : requests_by_dep.keySet()) {
			count_all_courses += d.getCourses_offered().size();
		}
	}
	
	public abstract Set<Class> schedule();
}
