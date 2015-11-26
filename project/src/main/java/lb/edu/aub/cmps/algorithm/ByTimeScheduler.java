package lb.edu.aub.cmps.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Room;

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
						int best = setup.bestScheduleClass(c_to_sched);
						//in case it didn't succeed to meet the request
						if(best!=1){
							//if it is a professor conflict we need to change the time slot
							if(best == -1){
								//change time 
							}
							//in case it is an unavailable room we need to search for a room
							else if(best == -2){
								//change room
								Set<Room> rooms = setup.get_all_rooms_in_same_building(c_to_sched.getRequestedRoom(), c_to_sched.getRequestedTime().getTimeSlots());
								if(rooms == null){
									rooms = setup.get_all_rooms_in_all_near_buildings(c_to_sched.getRequestedRoom(), c_to_sched.getRequestedTime().getTimeSlots());
								}
							}
						}
					}
				}
			}
		}

		return notSched;

	}

}
