package lb.edu.aub.cmps.grad.classes;

import java.io.IOException;

/**
 * implementing the visitor pattern
 * @author Julia
 *
 */
public interface ClassVisitor {

	public void visit(Class c) throws SecurityException, IOException;
	
}
