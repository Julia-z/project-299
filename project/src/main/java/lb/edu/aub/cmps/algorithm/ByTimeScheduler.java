package lb.edu.aub.cmps.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;

public class ByTimeScheduler extends Scheduler implements IScheduler {

	private Map<Department, Set<Class>> classes_by_dep;
	private int num_of_all_classes;

	public ByTimeScheduler() throws SecurityException, IOException {
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

		@SuppressWarnings("unchecked")
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
						if(best != 1){
							boolean scheduled = false;
							if(best != -1){//the prof is unavailable at the time => change time
								//if we cant 
								if(!c_to_sched.canChangeTime())notSched.put(c_to_sched, "Professor conflicts time. The class is marked not to change the time");
								else{
									//we can change the time
									scheduled = setup.changeTime(c_to_sched);
									if(!scheduled) notSched.put(c_to_sched, "Professor Conflict. Failed to change the time");

								}
							}
								
						}
						
						
						
						
						//in case it didn't succeed to meet the request
						if(best!=1){
							boolean scheduled = false;
							//if it is a professor conflict we need to change the time slot
							if(best == -1){
								//if the class is marked not to change time
								if(!c_to_sched.canChangeTime()) notSched.put(c_to_sched, "Professor conflicts time. The class is marked not to change the time");
								
								else{	
									//change time 
									scheduled = setup.changeTime(c_to_sched);
									if(!scheduled) notSched.put(c_to_sched, "Professor Conflict. Failed to change the time");
								}
							}
							//in case it is an unavailable room we need to search for a room
							else if(best == -2){
								//if the class is marked not to change the room
								if(!c_to_sched.canChangeRoom()){
									scheduled = setup.changeTime(c_to_sched);
									if(!scheduled) notSched.put(c_to_sched, "The room is unavialable at any time! the class is marked not to change the room");
									
								}
								else{
									//we can change room
									scheduled = setup.changeRoom(c_to_sched);
									//if no rooms try to change time
									if(!scheduled){
										if(!c_to_sched.canChangeTime()) notSched.put(c_to_sched, "The room is unavailable at the given time and the time can't be changed");
										else{
											scheduled = setup.changeTime(c_to_sched);
											if(!scheduled) notSched.put(c_to_sched, "all rooms are not available at all the times");
										}
									}
									//if after that it is not scheduled add it to not scheduled set
									if(!scheduled) notSched.put(c_to_sched, "");
								}
							}
						}
						remaining_classes --;
					}
				}
				k++;
			}
		}
		return notSched;
	}

}
