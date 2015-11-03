package lb.edu.aub.cmps.classes;

import java.util.LinkedList;

public class Professor {
	private int id;
	private String name;
	private LinkedList<Class> classes;
	private LinkedList<Time> unavailable;



	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LinkedList<Class> getClasses() {
		return classes;
	}

	public void addClass(Class c) {
		classes.add(c);
		unavailable.add(c.getTime());
	}

	public LinkedList<Time> getUnavailable() {
		return unavailable;
	}
	
	public void addUnavailable(Time t){
		this.unavailable.add(t);
	}
	/**
	 * TODO
	 * @param slots
	 * @return
	 */
	public boolean isAvailable(Time  slots){
		return false;
	}
	
	
}