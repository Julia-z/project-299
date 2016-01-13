package lb.edu.aub.cmps.grad.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.ClassTimeComparator;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.DepartmentWeightComparator;
import lb.edu.aub.cmps.grad.classes.MyLogger;

/**
 * This class is the first implemetation of the ISchedler
 * It tries to satisfy the request of the department
 * if the request couldn't be satisfied it tries to change the room or the time 
 * respectign the location of the department (for the room chnage)
 * and the type of the class (if its time/rooom should be fixed)
 * 
 * @author Julia El Zini
 */
public class ByTimeScheduler extends Scheduler {

	private int num_of_all_classes;

	private double overallStat;
	private Map<Department, Double> statByDep;
	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	/**
	 * Constructor. Sets the needed fields for the schedule method
	 * 
	 * @throws SecurityException for the database connection
	 * @throws IOException for the ouptput file
	 */
	@SuppressWarnings("unchecked")
	public ByTimeScheduler() throws SecurityException, IOException {
		super();
		classes_by_dep = setup.getDeps_Classes_map();
		
		log.info("ByTimeScheduler created.");
	}

	/**
	 * Runs the byTime scheduling algorithm and returns the unscheduled classes and a string denoting the reason
	 */
	public Map<Class, String> schedule() {
		log.info("BasicScheduler initiated.... Schedule method running");
		Map<Class, String> notSched = new HashMap<Class, String>();

		int remaining_classes = num_of_all_classes;

		boolean floor_turn = false;
		while (remaining_classes > 0) {
			int k = 0;
			for (Department d : setup.getDeps_Classes_map().keySet()) {
				log.info("Working on the " + d.getName()
						+ " department.");
				double classes_to_sched = (d.getNum_of_classes() / num_of_iterations);
				classes_to_sched = (floor_turn) ? Math.floor(classes_to_sched)
						: Math.ceil(classes_to_sched);
				
				floor_turn = !floor_turn;
				Iterator<Class> it = its[k];
				k++;
				
				for (int i = 0; i < classes_to_sched + 1; i++) {
					if (it.hasNext()) {
						Class c_to_sched = it.next();
						System.out.println(c_to_sched.getClass_id());
						remaining_classes--;

						int best = setup.bestScheduleClass(c_to_sched);
						boolean scheduled = false;
						
						if(best == 1){
							System.out.println("met..");
							scheduled = true;
							c_to_sched.setIsMet(true);
							scheduled_map.get(d).add(c_to_sched);
						}
						//not net
						else{
							//prof unavailable
							if(best == -1){
								System.out.println("the prof is unavailable...");
								//cant change time
								if(! c_to_sched.canChangeTime()) {
									scheduled = false;
									notSched.put(c_to_sched, "The prof is unavailable. but we can't change the time of the class");
								}
								else{
									//we can change the time
									scheduled = setup.changeTime(c_to_sched);
									if(scheduled) 
										scheduled_map.get(d).add(c_to_sched);
									else{
										//the room is unavailable at all the times
										if(!c_to_sched.canChangeRoom()){
											notSched.put(c_to_sched, "Tried to move the class in the same room. but the room is reserved all the day and it can't be changed");

										}
										else{
											scheduled = setup.changeTimeAndRoom(c_to_sched);
											if(!scheduled) notSched.put(c_to_sched, "NO more options");
											else scheduled_map.get(d).add(c_to_sched);
										}
									}
								}
							}
							//the room is unavailable
							else if(best == -2){
								System.out.println("the room is unavailable...");
								if(!c_to_sched.canChangeRoom()){
									notSched.put(c_to_sched, "The room is unavailable and the class is marked not to change the room");
								}
								else{
									System.out.println("we can chage room");

									scheduled = setup.changeRoom(c_to_sched);
									System.out.println("the room is changed");
									if(scheduled){
										scheduled_map.get(d).add(c_to_sched);
									}
									else{
										//we need to change the time
										if(!c_to_sched.canChangeTime()){
											notSched.put(c_to_sched, "all the rooms are busy at the requested time and the class is marked not to change the time");
										}
										else{
											scheduled = setup.changeTimeAndRoom(c_to_sched);
											if(!scheduled){
												notSched.put(c_to_sched, "No more options");
											}
											else{
												scheduled_map.get(d).add(c_to_sched);
											}
										}
									}
								}
							}
						}
						
						// request metd
						
						/*
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
									//  if not scheduling keep changing the
									// room for the new time
									scheduled = setup.changeTimeAndRoom(c_to_sched);
									if(!scheduled){
										notSched.put(c_to_sched,
												"Professor Conflict. Failed to change the time");
	
										log.info("Professor Conflict. Failed to change the time");
									}
									else{
										scheduled_map.get(d).add(c_to_sched);
									}
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
								}
							}
							else { // we can change room scheduled =
								scheduled = setup.changeRoom(c_to_sched);
								
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
								
							}
						}*/
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

	/**
	 * @author Julia
	 * @return a map from a department to a double denoting the percentage of requests that were satisfied
	 */
	public Map<Department, Double> getStatisticsByDepartment() {
		
		statByDep = new HashMap<Department, Double>();
		for(Department d: scheduled_map.keySet()){
			if(d.getNum_of_classes() != 0){ //only for the departments that actually have classes
				double met = 0;
				for(Class c: scheduled_map.get(d)){
					if(c.getIsMet()) met++;
				}
				statByDep.put(d, met / d.getNum_of_classes());
				overallStat += met;
			}
		}
		overallStat = overallStat*1.0 / num_of_all_classes;
		return statByDep;
	}
	/**
	 * @return a double denoting the overall percentage of requests that were satisfied
	 * @author Julia
	 */

	public double getOverallStatistics() {
		return overallStat;
	}

	
}
