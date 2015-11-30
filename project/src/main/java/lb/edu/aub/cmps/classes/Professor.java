package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.Set;

public class Professor {
	private int id;
	private String name;
	private Set<Class> classes;
	private HashSet<TimeSlot> unavailable;


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Class> getClasses() {
		return classes;
	}

	public void setClasses(Set<Class> classes) {
		this.classes = classes;
	}

	public void addClass(Class c) {
		classes.add(c);
		for (int i = 0; i < c.getGivenTime().getTimeSlots().length; i++) {
			unavailable.add(c.getGivenTime().getTimeSlots()[i]);
		}

	}

	public HashSet<TimeSlot> getUnavailable() {
		return unavailable;
	}

	public void addUnavailable(Time t) {
		for (int i = 0; i < t.getTimeSlots().length; i++) {
			unavailable.add(t.getTimeSlots()[i]);
		}
	}

	/**
	 * Checks if Professor is Available during Slots
	 * 
	 * @param slots
	 * @return true if Professor is available false if professor already has a
	 *         class during that time
	 */
	public boolean isAvailable(Time slots) {
		boolean available = true;
		for (TimeSlot unavailableTimes : this.unavailable) {
			for (int i = 0; i < slots.getTimeSlots().length; i++) {
				if (unavailableTimes.conflicts(slots.getTimeSlots()[i]))
					available = false;
			}
		}
		return available;
	}
	
	public void initializeUnavailable(){
		this.classes = new HashSet<Class>();
		this.unavailable = new HashSet<TimeSlot>();
	}

	public String toString() {
		return "id: " + id + ", name: " + name + ", classes: " + classes + "UNAVAILABLE: "+unavailable;
	}
}