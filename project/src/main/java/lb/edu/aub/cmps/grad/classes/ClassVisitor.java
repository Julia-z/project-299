package lb.edu.aub.cmps.grad.classes;

import java.io.IOException;

public interface ClassVisitor {

	public void visit(Class c) throws SecurityException, IOException;
	
}
