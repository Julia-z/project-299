package lb.edu.aub.cmps.grad.algorithm;

import java.io.IOException;
import java.util.HashMap;
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
import lb.edu.aub.cmps.grad.classes.Course;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.MyLogger;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.Time;

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

	public void scheduleSecondSets(Set<Class> tosched, String msg){
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
			if(!done) not_scheduled.put(c, msg + c.getRequestedTime().toString());
		}
		
	}

	public HashMap<Class, String> scheduleSecondMaps(TreeMap<Department, Set<Class>> tosched, String msg){
		HashMap<Class, String> not = new HashMap<Class, String>();
		// iterators
		@SuppressWarnings("unchecked")
		Iterator<Class>[] its = new Iterator[tosched.keySet().size()];
		int i = 0;

		for (Department d : tosched.keySet()) {
			its[i] = tosched.get(d).iterator();
			i++;
		}
		int k = 0;
		for (Department d : tosched.keySet()) {

			double classes_to_sched = (d.getNum_of_classes() / num_of_iterations);
			classes_to_sched = Math.ceil(classes_to_sched);
			Iterator<Class> it = its[k];
			k++;
			for (i = 0; i < classes_to_sched + 1; i++) {
				if (it.hasNext()) {
					Class c = it.next();
					
					//try to change the room
					Building[] bldgs = setup.getBuildingsByPriorityForDepartment(setup.getDep_id(c));
					boolean done = false;		
					for(int j = 0; j< bldgs.length; j++){
						if(done) break;
						for(Room room2: setup.getRoomsInBuilding(bldgs[j])){
							if(room2.is_available(c.getRequestedTime().getTimeSlots()) 
									&& room2.hasAccessory(c.getAccessoriesIds())
									&& room2.getRoom_capacity() >= c.getClass_capacity()){
								setup.reserve(c.getProfessors(), room2, c.getRequestedTime(), c);
								done = true;
								break;
							}
						}
					}
					if(!done) not.put(c, msg + c.getRequestedTime().toString());

				}// end if there are more classes
			}// end for all classes for this iteration
		}// end for department
		return not;
	}
	
	/**TODO**/
	public void scheduleSecondGrad(){

	}
	
	public void scheduleSecondRecitations(TreeMap<Department, Set<Class>> tosched){
		Set<Class> not = scheduleSecondMaps(lower_rec_by_dep, "").keySet();
		
		//try to sched all the recitations in the set not by changing the time
		//make sure the time doesn't conflict other 
		for(Class r: not){
			Time t = r.getRequestedTime().nextTime();
			boolean done = false;
			while(!done){
				while(conflictsOtherLectureSameSection(r, t))
					t = t.nextTime();
				if(t == null)
					not_scheduled.put(r, "all the next time slots conflict other lectures in the same section");

				else{
					//t not null and t doesn't conflict any other lecture for the same course
					//get a room
					Room room = setup.getAvailableRoomDuringTime(t, r.getClass_capacity());
					if(room != null) {
						setup.reserve(r.getProfessors(), room, t, r);
						done = true;
					}
					else
						t = t.nextTime();
				}
			}
		}
		
	}
	
	
	/**TODO**/
	public void scheduleFirstBigLectures(){
		
	}
	
	/**TODO**/
	public void scheduleSecondBigLectures(){
		
	}
	
	@Override
	public Map<Class, String> schedule() {

		//first run
		scheduleFirstBigLectures();
		scheduleFirstGrad();

		labs2 = scheduleFirstSets(labs);
		time_fixed_classes2 = scheduleFirstSets(time_fixed_classes);
		loc_fixed_classes2 = scheduleFirstSets(loc_fixed_classes);
		labs2 = scheduleFirstSets(labs);
		
		lower_lect_by_dep2 = scheduleFirstMaps(lower_lect_by_dep);
		upper_lect_by_dep2 = scheduleFirstMaps(upper_lect_by_dep);
		lower_rec_by_dep2 = scheduleFirstMaps(lower_rec_by_dep);
		upper_rec_by_dep2 = scheduleFirstMaps(upper_rec_by_dep);
		
		//second run
		scheduleSecondGrad();
		scheduleSecondBigLectures();

		scheduleSecondSets(time_fixed_classes2, "The class is marked not to change the time "
				+"and there are no suitable rooms at the time ");

		//the location fixed classes can't change the room => mark them as not scheduled
		for(Class c: loc_fixed_classes2) not_scheduled.put(c, "the room can't be changed and it is occupied!");
		for(Class c: labs2) not_scheduled.put(c, "lab conflict at the time " + c.getRequestedTime().toString());
		scheduleSecondMaps(lower_lect_by_dep2, "No available suitable rooms for lower campus");
		scheduleSecondMaps(upper_lect_by_dep2, "No available suitable rooms for upper campus");
		scheduleSecondRecitations(lower_rec_by_dep2);
		scheduleSecondRecitations(upper_rec_by_dep2);
		
		return not_scheduled;
	}

	public boolean conflictsOtherLectureSameSection(Class c, Time t){
		Course course = setup.getId_course().get(c.getCourse_id());
		for(Class c2: course.getClasses())
			if( c2.getGivenTime() != null && t.conflicts(c2.getGivenTime())) 
				return false;
		return true;
	}
}
