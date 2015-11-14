package lb.edu.aub.cmps.algorithm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Room;

public class Scheduler {

	private SetUp setup;
	private int num_of_iterations;
	private int count_all_courses;
	private TreeMap<Department, Set<Course>> requests_by_dep; // sorted by weight
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
	public Scheduler(int num_of_iterations) {
		setup = new SetUp();
		this.num_of_iterations = num_of_iterations;
		this.requests_by_dep = setup.getDeps_courses_map();

		
		for (Department d : requests_by_dep.keySet()) {
			count_all_courses += d.getCourses_offered().size();
		}
	}

	/**
	 * 
	 * @return set of classes that couldn't be scheduled NULL in case of success
	 */
	public Set<Class> schedule() {
		Set<Class> not_sched = new HashSet<Class>();
		int remaining_classes = count_all_courses;
		int j = 0;
		int size = requests_by_dep.keySet().size();
		Iterator<Course>[] its = new Iterator[size];
		for(Department d: requests_by_dep.keySet()){
			its[j] = requests_by_dep.get(d).iterator();
			j++;
		}
		boolean floor_turn = false;
		while (remaining_classes > 0) {
			int k = 0;
			for (Department dep : requests_by_dep.keySet()) {
				System.out.print("\n" + dep.getName()+": ");
				// get the num of courses to schedule at this iteration
				double num_courses_to_sched = dep.getCourses_offered().size() * 1.0 / num_of_iterations;
				//alternatively take the floor or the ceiling
				num_courses_to_sched = (floor_turn)? Math.floor(num_courses_to_sched): Math.ceil(num_courses_to_sched);
				System.out.println(num_courses_to_sched);
				floor_turn = !floor_turn;
				
				Iterator<Course> it = its[k];
				for(int i = 0; i< num_courses_to_sched; i++){
					if(it.hasNext()){
						Course course = it.next();
						System.out.println("Course " + course.getCourse_name() + " with "+course.getClasses().size() + " classes.");
						for(Class to_sched: course.getClasses()){
							//try to meet the request
							System.out.println("class time is "+to_sched.getTime());
							if (!setup.bestScheduleClass(to_sched)){
								System.out.println("NOT SCHEDULED BEST...........................................................................................");
								if (!setup.secondScheduleClass(to_sched)){
									not_sched.add(to_sched);
									System.out.println("added to not scheduled");
								}
								
							}
							//Set<Course> newSet = requests_by_dep.get(dep);
							//newSet.remove(course);
							//requests_by_dep.put(dep, newSet);
						}
					}
				}
				k++;
			}
			remaining_classes--;// decrement the remaining classes to sched
		}
		System.out.println("ENDD" + not_sched);
		
		for(Room r: setup.getRooms()){
			System.out.println(r.getReserved());
		}
		return not_sched;
	}

}
