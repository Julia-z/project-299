package lb.edu.aub.cmps.grad.configuration;

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
		return ds.getGradClass(depId).getId();
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

		int nbr = ds.getPreferedBuildingsCount(depId);
		int[] buildingIds = new int[nbr];
		for (int i = 1; i <= nbr; i++) {
			buildingIds[i - 1] = ds.getBuildingByPriority(depId, i).getId();
		}
		
		return buildingIds;
	}

}
