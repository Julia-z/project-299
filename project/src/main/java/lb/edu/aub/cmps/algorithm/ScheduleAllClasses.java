package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import lb.edu.aub.cmps.classes.Class;

public class ScheduleAllClasses {
	SetUp setup;

	private int num_of_iterations;
	private int count_all_classes;
	// department id -> set of class
	private HashMap<Integer, Set<Class>> requests_by_dep;
	private Set<Class> all_classes;
	private TreeMap<Integer, Double> weights;

	// weight -> set of class

	public ScheduleAllClasses(HashMap<Integer, Set<Class>> requests_by_dep,
			int size, int num_of_iterations) {
		setup = new SetUp();
		this.num_of_iterations = num_of_iterations;
		this.requests_by_dep = requests_by_dep;

		/**
		 * TODO what if a weight is duplicated!!
		 */
		weights = new TreeMap<Integer, Double>();
		all_classes = new HashSet<Class>();
		// normalize and get the weights
		for (Integer d : requests_by_dep.keySet()) {
			double counter = requests_by_dep.get(d).size();
			weights.put(d, counter);
			count_all_classes += counter;
			all_classes.addAll(requests_by_dep.get(d));
		}

		// normalize
		for (Integer d : weights.keySet()) {
			weights.put(d, weights.get(d) / count_all_classes);
		}

	}

	/**
	 * 
	 * @return set of classes that couldn't be scheduled NULL in case of success
	 */
	public Set<Class> schedule() {
		Set<Class> not_sched = new HashSet<Class>();
		/** TODO **/
		// this boolean tells if there is more classes
		// boolean b = true;
		// for number of iterations
		while (!all_classes.isEmpty()) {
			for (Integer d : requests_by_dep.keySet()) {
				// get the num of courses to schedule at this iteration
				int num_of_classes = (int) Math.ceil(weights.get(d)
						* num_of_iterations);
				Iterator<Class> it = ((HashSet<Class>) requests_by_dep.get(d))
						.iterator();
				for (int i = 0; i < num_of_classes; i++) {
					Class to_sched = it.next();
					// try to meet the request
					if (!setup.bestScheduleClass(to_sched))
						if (!setup.secondScheduleClass(to_sched))
							not_sched.add(to_sched);
					requests_by_dep.get(d).remove(to_sched);
					all_classes.remove(to_sched);
					requests_by_dep.put(d, requests_by_dep.get(d));
				}
			}
		}
		// take Ni of classes from the ith department
		// try to schedule the class as requested
		// in case it didn't work schedule it in the second best way

		// if there is no way to schedule it add it to the set of classes you
		// need to return
		return not_sched;
	}

}
