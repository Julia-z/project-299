package lb.edu.aub.cmps.grad.classes;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import lb.edu.aub.cmps.grad.algorithm.EnhancedScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.output.GetTimeSlots;

public class RoomsScheduleVisitor implements RoomVisitor {

	private TimeSlot[] mwf;
	private TimeSlot[] tr;
	/**
	 * here you do kel l ossas li badak yeha bi awal l excel
	 */
	public RoomsScheduleVisitor(TimeSlot[] mwf, TimeSlot[] tr){
		this.mwf = Arrays.copyOf(mwf, mwf.length);
		this.tr = Arrays.copyOf(tr,  tr.length);
		
		//initialize the excel sheet and bla bla bla 
		//u can see yasmin's class PrintTimeClassInRoomVisitor and sorry for the long name :p
	}
	public void visit(Room r) {
		System.out.println("\n" + r.getNumber()+"\n________________");
		// TODO Auto-generated method stub
		for(int i = 0; i< 3; i++){
		//here u can refer to the above time slots at the class level
			for(TimeSlot t: mwf){
				System.out.printf("%-20s", t);
				Class c = r.getClassDuringTimeSlot(t);
				if( c== null){
					//no class at this time slot
					//do something
				}
				else{
					String course = c.getCourse_name();
					//do something
					System.out.print(course);
				}
				System.out.println();
			}
		}
		
		for(int i = 0; i< 2; i++){
			for(TimeSlot t: tr){
				//same as above
			}
		}
		
	}
	
	public static void main(String[] args) throws SecurityException, IOException{
		TimeSlot[] mwf = GetTimeSlots.GetMWF();
		TimeSlot[] tr = GetTimeSlots.GetTR();
		
		
		Scheduler s = new EnhancedScheduler();
		s.schedule();
		
		RoomVisitor visitor = new RoomsScheduleVisitor(mwf, tr);
		Map<Integer, Room> id_room = s.getIdRoomMap();
		for (Integer i : id_room.keySet()) {
			Room r = id_room.get(i);
			r.accept(visitor);
		}	
	}

}
