package lb.edu.aub.cmps.algorithm;

import java.util.Set;
import lb.edu.aub.cmps.classes.Class;

public class Test {
	public static void main(String[] args){
		SetUp setup = new SetUp();
		Set<Class> classes = setup.getClasses();
		System.out.println(classes);
		
	}

}
