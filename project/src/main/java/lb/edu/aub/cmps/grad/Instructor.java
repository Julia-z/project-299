package lb.edu.aub.cmps.grad;

import java.util.LinkedList;

public class Instructor {
	private int id;
	private String name;
	private LinkedList<Class> classes;
	private LinkedList<Time> unavailable;

	public Instructor(int id, String name, LinkedList<Class> classes,
			LinkedList<Time> unavailable) {
		this.id = id;
		this.name = name;
		this.classes = classes;
		this.unavailable = unavailable;
	}

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
}