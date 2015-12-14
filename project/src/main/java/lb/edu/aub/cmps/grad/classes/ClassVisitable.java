package lb.edu.aub.cmps.grad.classes;

import java.io.IOException;

/**
 * 
 * @author Julia
 * implementing visitor pattern
 */
public interface ClassVisitable {

	public void accept(ClassVisitor visitor) throws SecurityException, IOException;

}
