package lb.edu.aub.cmps.grad.algorithm;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import lb.edu.aub.cmps.grad.classes.Building;
import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.ClassTimeComparator;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.MyLogger;
import lb.edu.aub.cmps.grad.classes.Room;

public class EnhancedScheduler extends Scheduler {

	private Set<Class> time_fixed_classes;
	private Set<Class> loc_fixed_classes;

	private Set<Class> grad_classes;

	private Set<Class> labs;

	private TreeMap<Department, Set<Class>> lower_lect_by_dep;
	private TreeMap<Department, Set<Class>> upper_lect_by_dep;

	private TreeMap<Department, Set<Class>> lower_rec_by_dep;
	private TreeMap<Department, Set<Class>> upper_rec_by_dep;

	// for the second run
	private Set<Class> time_fixed_classes2;
	private Set<Class> loc_fixed_classes2;

	private Set<Class> grad_classes2;
	private Set<Class> labs2;

	private TreeMap<Department, Set<Class>> lower_lect_by_dep2;
	private TreeMap<Department, Set<Class>> upper_lect_by_dep2;

	private TreeMap<Department, Set<Class>> lower_rec_by_dep2;
	private TreeMap<Department, Set<Class>> upper_rec_by_dep2;

	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	public EnhancedScheduler() throws SecurityException, IOException {
		super();
		time_fixed_classes = setup.getTime_fixed_classes();
		loc_fixed_classes = setup.getLoc_fixed_classes();
		grad_classes = setup.getGrad_classes();
		labs = setup.getlabs();
		lower_lect_by_dep = setup.getLower_Lec_by_dep();
		upper_lect_by_dep = setup.getUpper_Lec_by_dep();
		lower_rec_by_dep = setup.getLower_rec_by_dep();
		upper_rec_by_dep = setup.getUpper_rec_by_dep();

		// second run
		time_fixed_classes2 = new HashSet<Class>();
		loc_fixed_classes2 = new HashSet<Class>();
		grad_classes2 = new HashSet<Class>();
		labs2 = new HashSet<Class>();
		lower_lect_by_dep2 = new TreeMap<Department, Set<Class>>();
		upper_lect_by_dep2 = new TreeMap<Department, Set<Class>>();
		lower_rec_by_dep2 = new TreeMap<Department, Set<Class>>();
		upper_rec_by_dep2 = new TreeMap<Department, Set<Class>>();

	}

	public Map<Department, Double> getStatisticsByDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getOverallStatistics() {
		// TODO Auto-generated method stub
		return 0;
	}


	public Set<Class> scheduleFirstSets(Set<Class> tosched) {
		Set<Class> not = new HashSet<Class>();
		for (Class c : tosched) {
			if (setup.bestScheduleClass(c) < 0)
				not.add(c);
			else
				scheduled_map.get(setup.getDepartment(c)).add(c);
		}
		return not;

	}

	public void scheduleFirstGrad(){
		for(Class c: grad_classes){
			Room r = setup.getConferenceRoom(setup.getDep_id(c));
			if(r != null){
				//there is a conference room 
				Room old = c.getRequestedRoom();
				c.setRoom(r);
				int best = setup.bestScheduleClass(c);
				//if scheduled
				if(best > 0) {
					c.setRoom(old);
					c.setGiven_room(r);
					scheduled_map.get(setup.getDepartment(c)).add(c);
				}
				//the class can't be scheduled
				else if (best == -1){
					//the prof is unavailable
					not_scheduled.put(c, "The professor(s) " + c.getProfessors()+ "is/are unavailable at the time: "+c.getRequestedTime());
					
				}
				else {
					//the room is unavailable
					
					//there is no requested room
					if(old == null) scheduled_map.get(setup.getDepartment(c)).add(c);
					//add it to grad2
					else grad_classes2.add(c);
					
				}
			}
		}
		
	}
		
	public TreeMap<Department, Set<Class>> scheduleFirstMaps(TreeMap<Department, Set<Class>> tosched) {
		TreeMap<Department, Set<Class>> not = new TreeMap<Department, Set<Class>>();
		// iterators
		@SuppressWarnings("unchecked")
		Iterator<Class>[] its = new Iterator[tosched.keySet().size()];
		int i = 0;

		for (Department d : tosched.keySet()) {
			its[i] = tosched.get(d).iterator();
			i++;
			not.put(d, new TreeSet<Class>(new ClassTimeComparator()));
		}
		int k = 0;
		for (Department d : lower_lect_by_dep.keySet()) {

			double classes_to_sched = (d.getNum_of_classes() / num_of_iterations);
			classes_to_sched = Math.ceil(classes_to_sched);
			Iterator<Class> it = its[k];
			k++;
			for (i = 0; i < classes_to_sched + 1; i++) {
				if (it.hasNext()) {
					Class c = it.next();
					int best = setup.bestScheduleClass(c);
					if (best < 0) {
						not.get(d).add(c);
					} else
						scheduled_map.get(d).add(c);

				}// end if there are more classes
			}// end for all classes for this iteration
		}// end for department

		return not;
	}

	/**TODO**/
	public Set<Class> scheduleSecondSets(Set<Class> tosched){
		for(Class c: tosched){
			//try to get another room
			Building[] bldgs = setup.getBuildingsByPriorityForDepartment(setup.getDep_id(c));
			boolean done = false;		
			for(int i = 0; i< bldgs.length; i++){
				if(done) break;
				for(Room room2: setup.getRoomsInBuilding(bldgs[i])){
					if(room2.is_available(c.getRequestedTime().getTimeSlots()) 
							&& room2.hasAccessory(c.getAccessoriesIds())
							&& room2.getRoom_capacity() >= c.getClass_capacity()){
						setup.reserve(c.getProfessors(), room2, c.getRequestedTime(), c);
						done = true;
						break;
					}
				}
			}
		}
		return null;
	}
	
	/**TODO**/
	public TreeMap<Department, Set<Class>> scheduleSecondMaps(TreeMap<Department, Set<Class>> tosched){
		return null;
	}
	
	/**TODO**/
	public void scheduleSecondGrad(){

	}
	
	/**TODO**/
	public void scheduleFirstBigLectures(){
		
	}
	
	/**TODO**/
	public void scheduleSecondBigLectures(){
		
	}
	
	@Override
	public Map<Class, String> schedule() {

		labs2 = scheduleFirstSets(labs);
		//first run
		time_fixed_classes2 = scheduleFirstSets(time_fixed_classes);
		loc_fixed_classes2 = scheduleFirstSets(loc_fixed_classes);
		labs2 = scheduleFirstSets(labs);
		scheduleFirstGrad();
		scheduleFirstBigLectures();
		lower_lect_by_dep2 = scheduleFirstMaps(lower_lect_by_dep);
		upper_lect_by_dep2 = scheduleFirstMaps(upper_lect_by_dep);

		lower_rec_by_dep2 = scheduleFirstMaps(lower_rec_by_dep);
		upper_rec_by_dep2 = scheduleFirstMaps(upper_rec_by_dep);
		
		//second run
		scheduleSecondSets(time_fixed_classes2);
		scheduleSecondSets(loc_fixed_classes2);
		scheduleSecondSets(labs2);
		scheduleSecondGrad();
		scheduleSecondBigLectures();
		scheduleSecondMaps(lower_lect_by_dep2);
		scheduleSecondMaps(upper_lect_by_dep2);
		scheduleSecondMaps(lower_rec_by_dep2);
		scheduleSecondMaps(upper_rec_by_dep2);
		
		return not_scheduled;
	}

}
