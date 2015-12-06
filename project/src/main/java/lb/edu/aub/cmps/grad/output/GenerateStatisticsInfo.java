package lb.edu.aub.cmps.grad.output;

import java.io.IOException;
import java.util.Map;

import lb.edu.aub.cmps.grad.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Department;

public class GenerateStatisticsInfo {

	//the map is from dep to a double indicating the percentage of classes that were met
	//the stat is a single double indicating the overall stat
	public static void generate(Map<Department, Double> map, double stat){
		
		
		
	}
	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		Map<Department, Double> map = s.getStatisticsByDepartment();
		double stat = s.getOverallStatistics();
		generate(map, stat);
	}
}
