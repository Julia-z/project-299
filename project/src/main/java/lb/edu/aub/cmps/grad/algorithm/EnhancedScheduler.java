package lb.edu.aub.cmps.grad.algorithm;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Department;
import lb.edu.aub.cmps.grad.classes.MyLogger;

public class EnhancedScheduler extends Scheduler {

	private TreeMap<Department, Set<Class>> time_fixed_classes_by_dep;
	private TreeMap<Department, Set<Class>> loc_fixed_classes_by_dep;
	
	private TreeMap<Department, Set<Class>> grad_classes_by_dep;

	
	private TreeMap<Department, Set<Class>> labs_by_dep;
	
	private TreeMap<Department, Set<Class>> lower_lect_by_dep;
	private TreeMap<Department, Set<Class>> upper_lect_by_dep;
	
	private TreeMap<Department, Set<Class>> lower_rec_by_dep;
	private TreeMap<Department, Set<Class>> upper_rec_by_dep;
	
	private MyLogger loggerWrapper = MyLogger.getInstance();
	Logger log = loggerWrapper.getLogger();

	public EnhancedScheduler()throws SecurityException, IOException {
		super();
		
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
		
		return notSched;
	}

}
