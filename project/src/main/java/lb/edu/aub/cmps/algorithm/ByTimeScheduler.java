package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;

public class ByTimeScheduler extends Scheduler implements IScheduler {

	private Map<Department, Set<Class>> classes_by_dep;
	private int num_of_all_classes;

	public ByTimeScheduler() {
		super();
		classes_by_dep = setup.getDeps_Classes_map();
		num_of_all_classes = setup.getNumOfClasses();
		num_of_iterations = 10;

	}

	public Map<Class, String> schedule() {
		Map<Class, String> notSched = new HashMap<Class, String>();

		int remaining_classes = num_of_all_classes;
		int j = 0;
		int size = classes_by_dep.keySet().size();

		Iterator<Class>[] its = new Iterator[size];
		for (Department d : classes_by_dep.keySet()) {
			its[j] = classes_by_dep.get(d).iterator();
			j++;
		}
		boolean floor_turn = false;

		while (remaining_classes >= 0) {
			int k = 0;
			for (Department d : setup.getDeps_Classes_map().keySet()) {
				double classes_to_sched = (d.getNum_of_classes() / num_of_iterations);
				classes_to_sched = (floor_turn) ? Math.floor(classes_to_sched)
						: Math.ceil(classes_to_sched);
				floor_turn = !floor_turn;

				Iterator<Class> it = its[k];
				for (int i = 0; i < classes_to_sched; i++) {
					if (it.hasNext()) {
						Class c_to_sched = it.next();
						
					}
				}
			}
		}

		return notSched;

	}

}
