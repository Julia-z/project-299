package lb.edu.aub.cmps.grad.configuration;

import lb.edu.aub.cmps.grad.classes.Building;
import lb.edu.aub.cmps.grad.services.DepartmentService;

public class Configuration {
	/**
	 * @author Bilal Abi Farraj
	 * 
	 * @param depId
	 *            , the id of the department corresponding to the grad course
	 * @return the id of the room in which the course should be given
	 * 
	 */
	public int getConferenceRoomId(int depId) {
		DepartmentService ds = new DepartmentService();
		return ds.getGradConferenceRoom(depId).getId();
	}

	/**
	 * @author Bilal Abi Farraj
	 * 
	 * @param depId
	 *            , the id of the department
	 * @return an array of buildings ids sorted by priority(descending order) in
	 *         which we can schedule classes of a given dep
	 */
	public int[] getBuildingsByPriorityForDepartment(int depId) {
		DepartmentService ds = new DepartmentService();
		Building[] bs= ds.getBuildingByPriority(depId);
		
		int size= bs.length;
		int[] buildingIds= new int[size];
		
		for(int i=0; i<size; i++){
			buildingIds[i]= bs[i].getId();
		}
		
		return buildingIds;
	}

}
