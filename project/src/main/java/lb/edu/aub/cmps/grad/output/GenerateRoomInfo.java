package lb.edu.aub.cmps.grad.output;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.RoomVisitor;

public class GenerateRoomInfo {
	public RoomVisitor visitor;
	public Map<Integer, Room> id_room;
	
	public GenerateRoomInfo(RoomVisitor visitor, Map<Integer, Room> id_room){
		this.visitor = visitor;
		this.id_room = id_room;
	}

	public void generate() throws FileNotFoundException, IOException{
		for (Integer i : id_room.keySet()) {
			Room r = id_room.get(i);
			r.accept(visitor);
		}
	}
}
