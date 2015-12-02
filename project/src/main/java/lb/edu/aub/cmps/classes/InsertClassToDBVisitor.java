package lb.edu.aub.cmps.classes;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
public class InsertClassToDBVisitor implements ClassVisitor {


	/**
	 * TODO insert code here to insert to the database ie call on the service
	 */
	public void visit(Class c) {

		
	}
	
	public static void main(String[] args) throws SecurityException, IOException{
		ClassVisitor visitor = new InsertClassToDBVisitor();
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		Set<Class> classes = new HashSet<Class>();
		Map<Department, Set<Class>> dep_classes = s.getScheduled();
		for(Department d: dep_classes.keySet()){
			classes.addAll(dep_classes.get(d));
		}
		for (Class cl : classes) {
			cl.accept(visitor);
		}
	}

}
