package lb.edu.aub.cmps.grad.classes;

import java.io.IOException;

public interface ClassVisitable {

	public void accept(ClassVisitor visitor) throws SecurityException, IOException;

}
