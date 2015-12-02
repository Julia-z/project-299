package lb.edu.aub.cmps.algorithm;

import java.util.Map;
import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Department;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Section;

public interface IScheduler {

	public Map<Class, String> schedule();
	public Map<Department, Set<Class>> getScheduled();
	public Map<Integer, Room> getIdRoomMap();
	public Map<Department, Set<Section>> getDep_Sections();

}
