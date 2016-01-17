package lb.edu.aub.cmps.grad.algorithm;

import java.io.IOException;
import java.util.Collections;
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
import lb.edu.aub.cmps.grad.classes.DepartmentWeightComparator;
import lb.edu.aub.cmps.grad.classes.MyLogger;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.Section;
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
	private TreeMap<Department, Set<Class>> big_lectures;

	// for the second run
	private Set<Class> time_fixed_classes2;
	private Set<Class> loc_fixed_classes2;

	private Set<Class> grad_classes2;
	private Set<Class> labs2;

	private TreeMap<Department, Set<Class>> lower_lect_by_dep2;
	private TreeMap<Department, Set<Class>> upper_lect_by_dep2;
	private TreeMap<Department, Set<Class>> big_lectures2;

	private TreeMap<Department, Set<Class>> lower_rec_by_dep2;
	private TreeMap<Department, Set<Class>> upper_rec_by_dep2;

	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	/**constructor**/
	public EnhancedScheduler() throws SecurityException, IOException {
		super();
		System.out.println("Constructor");

		time_fixed_classes = setup.getTime_fixed_classes();
		loc_fixed_classes = setup.getLoc_fixed_classes();
		grad_classes = setup.getGrad_classes();
		labs = setup.getlabs();
		lower_lect_by_dep = setup.getLower_Lec_by_dep();
		upper_lect_by_dep = setup.getUpper_Lec_by_dep();
		lower_rec_by_dep = setup.getLower_rec_by_dep();
		upper_rec_by_dep = setup.getUpper_rec_by_dep();
		big_lectures = setup.getBig_lectures();

		// second run
		time_fixed_classes2 = new HashSet<Class>();
		loc_fixed_classes2 = new HashSet<Class>();
		grad_classes2 = new HashSet<Class>();
		labs2 = new HashSet<Class>();
		lower_lect_by_dep2 = new TreeMap<Department, Set<Class>>();
		upper_lect_by_dep2 = new TreeMap<Department, Set<Class>>();
		lower_rec_by_dep2 = new TreeMap<Department, Set<Class>>();
		upper_rec_by_dep2 = new TreeMap<Department, Set<Class>>();
		big_lectures2 = new TreeMap<Department, Set<Class>>();

		not_scheduled = new HashMap<Class, String>();
	}

	public Set<Class> scheduleFirstSets(Set<Class> tosched) {
		Set<Class> not = new HashSet<Class>();
		for (Class c : tosched) {
			int best = setup.bestScheduleClass(c);
			System.out.printf("%-5d %-3d\n", c.getClass_id(), best);
			if (best < 0)
				not.add(c);
			else{
				scheduled_map.get(setup.getDepartment(c)).add(c);
				c.setGiven_room(c.getRequestedRoom());
				c.setGiven_time(c.getRequestedTime());
				c.setIsMet(true);
			}
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
					c.setGiven_time(c.getRequestedTime());
					c.setIsMet(true);
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
		TreeMap<Department, Set<Class>> not = new TreeMap<Department, Set<Class>>(new DepartmentWeightComparator());
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
		for (Department d : tosched.keySet()) {

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
					} else{
						scheduled_map.get(d).add(c);
						c.setGiven_room(c.getRequestedRoom());
						c.setGiven_time(c.getRequestedTime());
						c.setIsMet(true);
					}
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
			
			if(!done) {
				not_scheduled.put(c, msg + c.getRequestedTime());
			}
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
					else
						scheduled_map.get(d).add(c);


				}// end if there are more classes
			}// end for all classes for this iteration
		}// end for department
		return not;
	}
	
	public void scheduleSecondGrad(){
		scheduleSecondSets(grad_classes2, "No more rooms at time t ");
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
	
	
	public void scheduleFirstBigLectures(){
		big_lectures2 = scheduleFirstMaps(big_lectures);
	}
	
	public void scheduleSecondBigLectures(){
		HashMap<Class, String> not = new HashMap<Class, String>();
		// iterators
		@SuppressWarnings("unchecked")
		Iterator<Class>[] its = new Iterator[big_lectures2.keySet().size()];
		int i = 0;

		for (Department d : big_lectures2.keySet()) {
			its[i] = big_lectures2.get(d).iterator();
			i++;
		}
		int k = 0;
		for (Department d : big_lectures2.keySet()) {

			double classes_to_sched = (d.getNum_of_classes() / num_of_iterations);
			classes_to_sched = Math.ceil(classes_to_sched);
			Iterator<Class> it = its[k];
			k++;
			for (i = 0; i < classes_to_sched + 1; i++) {
				if (it.hasNext()) {
					Class c = it.next();
					
					//try to change the room
					Room[] rooms = setup.getLectureRoomsByPriority(d.getId());
					boolean done = false;
					for(Room r: rooms){
						if(r.is_available(c.getRequestedTime().getTimeSlots()) && r.getRoom_capacity() >= c.getClass_capacity()&&r.hasAccessory(c.getAccessoriesIds())){
							setup.reserve(c.getProfessors(), r, c.getRequestedTime(), c);
							done = true;
							break;
						}
					}
					if(done) scheduled_map.get(d).add(c);
					
					else not.put(c, "No big lectures rooms available at " + c.getRequestedTime().toString());

				}// end if there are more classes
			}// end for all classes for this iteration
		}// end for department

	}
	
	private void firstRun(){
		System.out.println("Scheduling big lectures....");
		scheduleFirstBigLectures();	
		
		System.out.println("Scheduling labs....");
		labs2 = scheduleFirstSets(labs);
		
		System.out.println("Scheduling time fixed classes....");
		time_fixed_classes2 = scheduleFirstSets(time_fixed_classes);
		
		System.out.println("Scheduling location fixed classes....");
		loc_fixed_classes2 = scheduleFirstSets(loc_fixed_classes);
		
		System.out.println("Scheduling grad....");
		scheduleFirstGrad();	
		
		System.out.println("Scheduling lower lectures....");
		lower_lect_by_dep2 = scheduleFirstMaps(lower_lect_by_dep);
		
		System.out.println("Scheduling upper lectures....");
		upper_lect_by_dep2 = scheduleFirstMaps(upper_lect_by_dep);
		
		System.out.println("Scheduling lower rec....");
		lower_rec_by_dep2 = scheduleFirstMaps(lower_rec_by_dep);
		
		System.out.println("Scheduling upper rec....");
		upper_rec_by_dep2 = scheduleFirstMaps(upper_rec_by_dep);
		
	}
	private void secondRun(){
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
	}
	@Override
	public Map<Class, String> schedule() {

		//first run
		System.out.println("First Run....");
		firstRun();
		//second run
		System.out.println("Second Run...");
		secondRun();
		return not_scheduled;
	}

	private boolean conflictsOtherLectureSameSection(Class c, Time t){
		Course course = setup.getId_course().get(c.getCourse_id());
		for(Class c2: course.getClasses())
			if( c2.getGivenTime() != null && t.conflicts(c2.getGivenTime())) 
				return false;
		return true;
	}
	
	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new EnhancedScheduler();
		s.schedule();
	}

	@Override
	public Map<Department, Set<Course>> getDepCoursesMap() {
		Set<Class> classes = new HashSet<Class>();
		for(Set<Class> set: scheduled_map.values())
			for(Class c: set)
				classes.add(c);
		for(Class c: not_scheduled.keySet())
			classes.add(c);
		
		for (Class c : classes) {
			int course_id = c.getCourse_id();
			c.setCourse_name(setup.getId_course().get(course_id).getCourse_name());
			Course course = setup.getId_course().get(course_id);
			course.addClass(c);
		}
		Map<Department, Set<Course>> map = new TreeMap<Department, Set<Course>>(Collections.reverseOrder(new DepartmentWeightComparator()));
		for(Integer dep_id: setup.getId_dep().keySet()){
			map.put(setup.getId_dep().get(dep_id), new HashSet<Course>());
		}
		for(Integer course_id: setup.getId_course().keySet()){
			Course c = setup.getId_course().get(course_id);
			for(Integer i: c.getSectionNbrs()){
				Section s = new Section(c.getDepartment(), setup.getId_dep().get(c.getDepartment()).getName(), i, c.getCourse_name(), c.getCourse_id());
				if(c.getClasses()!=null){
					for(Class cl : c.getClasses()){
						
						if(cl.getSection_number().contains(i)){
							s.addClass(cl);
						}
					}
					c.addSection(s);
				}
			}
			map.get(setup.getId_dep().get(c.getDepartment())).add(c);
		}
		
		return map;
	}
}
