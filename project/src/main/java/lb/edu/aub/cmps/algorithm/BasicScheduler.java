package lb.edu.aub.cmps.algorithm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.MyLogger;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Section;

public class BasicScheduler extends Scheduler implements IScheduler {

	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	public BasicScheduler() throws SecurityException, IOException{
		super();
		log.info("BasicScheduler created.");
	}
	/**
	 * 
	 * @return set of classes that couldn't be scheduled NULL in case of success
	 */
	public Map<Class, String> schedule() {
		log.info("BasicScheduler initiated.... Schedule method running");
		Map<Class, String> not_sched = new HashMap<Class, String>();
		int remaining_classes = count_all_courses;
		int j = 0;
		int size = requests_by_dep.keySet().size();
		@SuppressWarnings("unchecked")
		Iterator<Course>[] its = new Iterator[size];
		for(Department d: requests_by_dep.keySet()){
			its[j] = requests_by_dep.get(d).iterator();
			j++;
		}
		boolean floor_turn = false;
		while (remaining_classes > 0) {
			int k = 0;
			for (Department dep : requests_by_dep.keySet()) {
				log.info("Scheduling for the"+ dep.getName() +" department");
				
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
							System.out.println("class time is "+to_sched.getRequestedTime());
							if (setup.bestScheduleClass(to_sched) != 1){
								System.out.println("NOT SCHEDULED BEST...........................................................................................");
								if (!setup.changeRoom(to_sched)){
									if(!setup.changeTime(to_sched)){
										not_sched.put(to_sched, "Reason");
										System.out.println("added to not scheduled");
									}
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
		
		log.info("Scheduling done!");
		System.out.println("ENDD" + not_sched);
		
		for(Room r: setup.getRooms()){
			System.out.println(r.getReserved());
		}
		
		return not_sched;
	}
	
	public SetUp setup(){
		return setup;
	}
	@Override
	public Map<Department, Set<Section>> getDep_Sections() {
		// TODO Auto-generated method stub
		return null;
	}

}
