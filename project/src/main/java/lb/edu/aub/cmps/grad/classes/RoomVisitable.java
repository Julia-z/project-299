package lb.edu.aub.cmps.grad.classes;

/**
 * implements the visitor pattern for the room object
 * @author Julia
 *
 */
public interface RoomVisitable {

	public void accept(RoomVisitor visitor);
}
