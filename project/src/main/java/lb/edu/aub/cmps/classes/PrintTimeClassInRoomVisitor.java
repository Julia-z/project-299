package lb.edu.aub.cmps.classes;

import java.io.PrintStream;

public class PrintTimeClassInRoomVisitor implements RoomVisitor {

	

	/**
	 * TODO
	 */
	public void visit(Room r) {
		PrintStream out = new PrintStream(System.out);
		for(TimeSlot t: r.getReserved() ){
			out.print("lksjf;l");
		}
	}

}
