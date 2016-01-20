package lb.edu.aub.cmps.grad.classes;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * implements the visitor pattern for the room object
 * @author Julia
 *
 */
public interface RoomVisitable {

	public void accept(RoomVisitor visitor) throws FileNotFoundException, IOException;
}
