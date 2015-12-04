package lb.edu.aub.cmps.test;

import lb.edu.aub.cmps.grad.services.BuildingService;

public class ServiceTest {

	public static void main(String[] args){
		BuildingService bs = new BuildingService();
		System.out.println(bs.getAllBuildings().size());
	}

}
