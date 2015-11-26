package lb.edu.aub.cmps.classes;

import java.io.PrintStream;

import lb.edu.aub.cmps.algorithm.BasicScheduler;
import lb.edu.aub.cmps.algorithm.IScheduler;
import lb.edu.aub.cmps.algorithm.SetUp;

public class PrintTimeClassInRoomVisitor implements RoomVisitor {

	

	/**
	 * TODO
	 */
	public void visit(Room r) {
		PrintStream out = new PrintStream(System.out);
		for(TimeSlot t: r.getReserved().keySet() ){
			out.println(t + r.getReserved().get(t));
		}
	}
	
	public static void main(String[] args){
		BasicScheduler sched = new BasicScheduler();
		sched.schedule();
		SetUp s = sched.setup();
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");
		System.out.println("_________________________________________________________________________________");

		for(Room r: s.getRooms()){
			System.out.println(r.getNumber());
			r.accept(new PrintTimeClassInRoomVisitor());
		}
	}

}
