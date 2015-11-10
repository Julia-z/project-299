package lb.edu.aub.cmps.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;

public class ScheduleAllClasses {

	private SetUp setup;
	private int num_of_iterations;
	private int count_all_courses;
	private TreeMap<Department, Set<Course>> requests_by_dep; // sorted by weight
																// since it
																// knows
																// how to sort
																// the
																// departments

	/**
	 * constructor
	 * @param num_of_iterations
	 */
	public ScheduleAllClasses(int num_of_iterations) {
		setup = new SetUp();
		this.num_of_iterations = num_of_iterations;
		this.requests_by_dep = setup.getDeps_courses_map();

		for (Department d : requests_by_dep.keySet()) {
			count_all_courses += d.getCourses_offered().size();
		}
		/*
		 * weights = new TreeMap<Integer, Double>(); all_classes = new
		 * HashSet<Class>(); // normalize and get the weights for (Integer d :
		 * requests_by_dep.keySet()) { double counter =
		 * requests_by_dep.get(d).size(); weights.put(d, counter);
		 * count_all_classes += counter;
		 * all_classes.addAll(requests_by_dep.get(d)); }
		 */
		/*
		 * // normalize for (Integer d : weights.keySet()) { weights.put(d,
		 * weights.get(d) / count_all_classes); }
		 */
	}

	/**
	 * 
	 * @return set of classes that couldn't be scheduled NULL in case of success
	 */
	public Set<Class> schedule() {
		Set<Class> not_sched = new HashSet<Class>();
		int remaining_classes = count_all_courses;
		
		boolean floor_turn = false;
		while (remaining_classes > 0) {
			for (Department dep : requests_by_dep.keySet()) {
				// get the num of courses to schedule at this iteration
				double num_courses_to_sched = dep.getCourses_offered().size() / num_of_iterations;
				//alternatively take the floor or the ceiling
				num_courses_to_sched = (floor_turn)? Math.floor(num_courses_to_sched): Math.ceil(num_courses_to_sched);
				floor_turn = !floor_turn;
				
				Iterator<Course> it = requests_by_dep.get(dep).iterator();
				for(int i = 0; i< num_courses_to_sched; i++){
					Course course = it.next();
					for(Class to_sched: course.getClasses()){
						//try to meet the request
						if (!setup.bestScheduleClass(to_sched))
							if (!setup.secondScheduleClass(to_sched))
								not_sched.add(to_sched);
						Set<Course> newSet = requests_by_dep.get(dep);
						newSet.remove(course);
						requests_by_dep.put(dep, newSet);
					}
				}
			}
			remaining_classes--;// decrement the remaining classes to sched
		}
		// take Ni of classes from the ith department
		// try to schedule the class as requested
		// in case it didn't work schedule it in the second best way

		// if there is no way to schedule it add it to the set of classes you
		// need to return
		return not_sched;
	}

}
