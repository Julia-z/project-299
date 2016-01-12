package lb.edu.aub.cmps.grad.output;

import java.io.IOException;
import java.util.Collection;

import lb.edu.aub.cmps.grad.algorithm.ByTimeScheduler;
import lb.edu.aub.cmps.grad.algorithm.Scheduler;
import lb.edu.aub.cmps.grad.classes.Room;

public class AvailableRoomsByTimeJulia {
	Collection<Room> rooms;
	
	public AvailableRoomsByTimeJulia(Collection<Room> rooms){
		this.rooms = rooms;
		
		//u can add anything here
	}
	
	public void generate(){
		//here u generate the excel file
		System.out.println("hello");
		for(Room r: rooms){
			System.out.println(r.getNumber());
			System.out.println(r.getReserved());
		}
	}

	public static void main(String[] args) throws SecurityException, IOException{
		System.out.println("starting...");
		Scheduler s = new ByTimeScheduler();
		System.out.println("scheduler created...");
		s.schedule();
		System.out.println("all classes scheduled...");

		AvailableRoomsByTimeJulia available_rooms = new AvailableRoomsByTimeJulia(s.getRooms());
		available_rooms.generate();
	}
}
