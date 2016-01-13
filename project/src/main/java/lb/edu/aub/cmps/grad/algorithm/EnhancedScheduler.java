package lb.edu.aub.cmps.grad.algorithm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.MyLogger;
import lb.edu.aub.cmps.grad.classes.Professor;

public class EnhancedScheduler extends Scheduler {

	private  Set<Class>time_fixed_classes;
	private  Set<Class> loc_fixed_classes;
	
	private Set<Class> grad_classes;

	
	private  Set<Class> labs;
	
	private TreeMap<Department, Set<Class>> lower_lect_by_dep;
	private TreeMap<Department, Set<Class>> upper_lect_by_dep;
	
	private TreeMap<Department, Set<Class>> lower_rec_by_dep;
	private TreeMap<Department, Set<Class>> upper_rec_by_dep;
	
	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	public EnhancedScheduler()throws SecurityException, IOException {
		super();
		time_fixed_classes = setup.getTime_fixed_classes();
		loc_fixed_classes = setup.getLoc_fixed_classes();
		grad_classes = setup.getGrad_classes();
		labs = setup.getlabs();
		lower_lect_by_dep = setup.getLower_Lec_by_dep();
		upper_lect_by_dep = setup.getUpper_Lec_by_dep();
		lower_rec_by_dep = setup.getLower_rec_by_dep();
		upper_rec_by_dep = setup.getUpper_rec_by_dep();
		
		
	}
	public Map<Department, Double> getStatisticsByDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getOverallStatistics() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	//schedule the labs at first
	//schedule the lectures
	//schedule the recitations
	
	
	//for the lectures
	
	//-1 in case of unavailable prof -2 in case of    unavailable room
	public Map<Class, String> schedule() {
		// TODO Auto-generated method stub
		
		/**
		 * ALGORITHM
		 * try to meet all the requests at first
		 * 		labs 
		 * 		lower campus lectures
		 * 		upper campus lectures
		 * 		recitations
		 * after all the requests that could be met are met
		 * go to the not-met requests and try to meet them
		 * 		labs -> reddun to zeina
		 * 		lower campus lectures -> change room lower campus
		 * 		
		 * 
		 */
		Map<Class, String> notSched = new HashMap<Class, String>();

		for(Class cl: time_fixed_classes){
			int best = setup.bestScheduleClass(cl);
			if(best == -1){
				int profs = cl.getProfessors().size();
				Iterator<Professor> it = cl.getProfessors().iterator();
				String names = it.next().getName();
				for(int i = 0; i< profs - 2; i++) names += ", " + it.next().getName();
				if(it.hasNext()) names += " and " + it.next().getName();
				String msg = (profs == 1)? "The professor "+names+" is unavailable at the fixed time": 
					"one or more of the professors "+ names+" is/are unavailable";
				notSched.put(cl, msg);
			}
			else{
				notSched.put(cl, "The room "+cl.getRequestedRoom().getNumber()+" is unavailable");
			}
		}
		for(Class cl: loc_fixed_classes){
			int best = setup.bestScheduleClass(cl);
			if(best == -1){
				int profs = cl.getProfessors().size();
				Iterator<Professor> it = cl.getProfessors().iterator();
				String names = it.next().getName();
				for(int i = 0; i< profs - 2; i++) names += ", " + it.next().getName();
				if(it.hasNext()) names += " and " + it.next().getName();
				String msg = (profs == 1)? "The professor "+names+" is unavailable at the fixed time": 
					"one or more of the professors "+ names+" is/are unavailable";
				notSched.put(cl, msg);
			}
			else{
				notSched.put(cl, "The room "+cl.getRequestedRoom().getNumber()+" is unavailable");
			}
		}
		for(Class cl: labs){
			int best = setup.bestScheduleClass(cl);
			if(best == -1){
				int profs = cl.getProfessors().size();
				Iterator<Professor> it = cl.getProfessors().iterator();
				String names = it.next().getName();
				for(int i = 0; i< profs - 2; i++) names += ", " + it.next().getName();
				if(it.hasNext()) names += " and " + it.next().getName();
				String msg = (profs == 1)? "The professor "+names+" is unavailable at the fixed time": 
					"one or more of the professors "+ names+" is/are unavailable";
				notSched.put(cl, msg);
			}
			else{
				notSched.put(cl, "The room "+cl.getRequestedRoom().getNumber()+" is unavailable");
			}
		}
		for(Class c : grad_classes){
			
		}
		return notSched;
	}

}
