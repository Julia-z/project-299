package lb.edu.aub.cmps.grad.configuration;

public class Configuration {
	/**
	 * TODO BILAL
	 * @param depId, the id of the department corresponding to the grad course
	 * @return the id of the room in which the course should be given
	 * 
	 */
	public int getConferenceRoomId(int depId){
		return 0;
	}
	
	/**
	 * TODO BILAL
	 * @param depId, the id of the department 
	 * @return an array of buildings ids sorted by priority(descending order) 
	 * in which we can schedule classes of a given dep
	 */
	public int[] getBuildingsByPriorityForDepartment(int depId){
		return null;
	}

}
