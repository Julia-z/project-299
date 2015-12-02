package lb.edu.aub.cmps.output;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.algorithm.Scheduler;
import lb.edu.aub.cmps.classes.Course;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Section;
import lb.edu.aub.cmps.classes.Class
;public class ExportSectionsToExcel {


	/**
	 * TODO YASMIN
	 * @param dep_sections
	 */
	public static void export(Map<Department, Set<Course>> map){
		//here you can refer to course.getSections() that returns a set of sections
		for(Department d: map.keySet()){
			System.out.println(d.getName());
			System.out.println("__________________________________");
			for(Course course: map.get(d)){
				System.out.println("\t-"+course.getCourse_name());
				if(course.getSections()!=null){
					for(Section s: course.getSections()){
						System.out.println("\t\tSection "+s.getSection_number());
						for(Class c: s.getClasses()){
							System.out.println("\t\t"+c.getGivenTime());
						}
					}
				}
			}
		}
	}

	/**
	 * thats how u call on it
	 * @param args
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SecurityException, IOException{
		Scheduler s = new ByTimeScheduler();
		s.schedule();
		Map<Department, Set<Course>> map = s.getDepCoursesMap();
		export(map);
	}

}
