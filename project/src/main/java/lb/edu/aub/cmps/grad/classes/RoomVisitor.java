package lb.edu.aub.cmps.grad.classes;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * implements the visitor pattern 
 * @author Julia
 *
 */
public interface RoomVisitor {
	
	public void visit(Room r) throws FileNotFoundException, IOException;
}
