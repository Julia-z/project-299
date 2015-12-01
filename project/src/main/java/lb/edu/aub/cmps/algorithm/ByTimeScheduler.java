package lb.edu.aub.cmps.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.ClassTimeComparator;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.DepartmentWeightComparator;
import lb.edu.aub.cmps.classes.MyLogger;

public class ByTimeScheduler extends Scheduler implements IScheduler {

	private Map<Department, Set<Class>> classes_by_dep;
	private int num_of_all_classes;

	private Iterator<Class>[] its;
	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	@SuppressWarnings("unchecked")
	public ByTimeScheduler() throws SecurityException, IOException {
		super();
		classes_by_dep = setup.getDeps_Classes_map();
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
		log.info("ByTimeScheduler created.");
	}

	public Map<Class, String> schedule() {
		log.info("BasicScheduler initiated.... Schedule method running");
		Map<Class, String> notSched = new HashMap<Class, String>();

		int remaining_classes = num_of_all_classes;

		boolean floor_turn = false;

		while (remaining_classes > 0) {
			remaining_classes--;
			// System.out.println("*Remaining classes: " + remaining_classes);
			int k = 0;
			for (Department d : setup.getDeps_Classes_map().keySet()) {
				log.info("Working on the " + d.getName()
						+ " department.");
				double classes_to_sched = (d.getNum_of_classes() / num_of_iterations);
				classes_to_sched = (floor_turn) ? Math.floor(classes_to_sched)
						: Math.ceil(classes_to_sched);
				// System.out.println("-Department: " + d.getName() + " >> "
				// + classes_to_sched);

				floor_turn = !floor_turn;
				Iterator<Class> it = its[k];
				k++;
				for (int i = 0; i < classes_to_sched; i++) {
					if (it.hasNext()) {
						Class c_to_sched = it.next();
						// System.out.println(c_to_sched);

						int best = setup.bestScheduleClass(c_to_sched);
						// System.out.println("best is: " + best);
						System.out.println(c_to_sched + " > "+best);
						boolean scheduled = true;

						// request met
						if (best == 1) {
							scheduled_map.get(d).add(c_to_sched);
						}

						// the request cannot be met
						// unavailable professor
						else if (best == -1) {
							log.info("Unavailable professor, trying to change time...");
							scheduled = false;
							// the professor is unavailable at the
							// time => change time

							if (!c_to_sched.canChangeTime()) {
								notSched.put(c_to_sched,
										"Professor conflicts time. The class is marked not to change the time");
								log.info("Professor conflicts time. The class " +c_to_sched.getClass_id() +" is marked not to change the time");

							} else { // we can change the time
								scheduled = setup.changeTime(c_to_sched);
								if (!scheduled) {
									// TODO if not scheduling keep changing the
									// room for the new time
									notSched.put(c_to_sched,
											"Professor Conflict. Failed to change the time");

									log.info("Professor Conflict. Failed to change the time");
									scheduled = false;
								}
								else{
									log.info("Issue resolved");
									scheduled_map.get(d).add(c_to_sched);
								}
							}
						}

						// unavailable room
						else if (best == -2) {
							// in case it is an unavailable room we need to
							// search for a room else if(best == -2){ //if the
							// class is
							// marked not to change the room
							System.out.println("UNAVAILABLE ROOOOOOOOOOOOOOOOOOM" + c_to_sched.getRequestedRoom());
							log.info("Unavailable room, trying to change time...");
							if (!c_to_sched.canChangeRoom()) {
								scheduled = setup.changeTime(c_to_sched);
								if (!scheduled) {
									notSched.put(
											c_to_sched,
											"The room is unavialable at any time! the class is marked not to change the room");
									log.severe("The room is unavialable at any time! the class is marked not to change the room");
									scheduled = false;
								}
								else{
									scheduled_map.get(d).add(c_to_sched);
									System.out.println("finallyyyyyyyyyyyyyyyyyyyyyyyyyyy");
								}
							}
							else { // we can change room scheduled =
								System.out.println(">>>>>>>>>>" + setup.changeRoom(c_to_sched)); // if no rooms try
								System.out.println(">>>>>>>>>>>>>>>>>>>" + c_to_sched.getGiven_room());
								
								// tochange time
								if (!scheduled) {
									if (!c_to_sched.canChangeTime()) {
										notSched.put(c_to_sched,
												"The room is unavailable at the given time and the time can't be changed");
										log.info("The room is unavailable at the given time and the time can't be changed");
										scheduled = false;

									} else {
										scheduled = setup
												.changeTime(c_to_sched);
										if (!scheduled) {
											notSched.put(c_to_sched,
													"all rooms are not available at all times");
											log.info("All rooms are not available at all times");
											scheduled = false;

										}
									}
								}

							}
							if (!scheduled)
								notSched.put(c_to_sched, "");
							else{
								scheduled_map.get(d).add(c_to_sched);
								System.out.println(c_to_sched.getClass_id() + " fetttttttttttttttttttttttttttttttttt"+c_to_sched.getRequestedRoom().getNumber()+" ????? "+c_to_sched.getGiven_room().getNumber());
								
							}
						}
					}
				}
				log.info("Done working on the " + d.getName()
						+ " department.");
			}
		}
		log.info("Scheduling done!");
		return notSched;
	}

	public Map<Department, Set<Class>> getScheduled() {
		return scheduled_map;
	}
	
	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		System.out.println("__________________________________________________________________________");

		/*
		//System.out.println("The classes that are not scheduled are: " + notSched.keySet().size());
		for(Department d: s.getScheduled().keySet()){
			Set<Class> classes = s.getScheduled().get(d);
			for(Class c: classes){
				System.out.printf("%-4d in room: %-14s at time: %-30s\n", c.getClass_id(), c.getGiven_room().getNumber(), c.getGiven_time());
			}
		}*/
		System.out.println("__________________________________________________________________________");
		for(Set<Class> c: s.getScheduled().values()){
			for(Class cl: c){
				if(!cl.getRequestedRoom().getNumber().equals(cl.getGiven_room().getNumber()))
					System.out.println(cl.getClass_id() +": "+cl.getRequestedRoom().getNumber()+"->"+cl.getGiven_room().getNumber());
			}
		}
	}
	

}
