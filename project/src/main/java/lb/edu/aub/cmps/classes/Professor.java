package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.LinkedList;

import lb.edu.aub.cmps.enums.Day;

public class Professor {
	private int id;
	private String name;
	private LinkedList<Class> classes;
	private HashSet<TimeSlot> unavailable;

	

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
		for(int i=0; i <c.getTime().getTimeSlots().length; i++){
			unavailable.add(c.getTime().getTimeSlots()[i]);
		}
		
	}

	public HashSet<TimeSlot> getUnavailable() {
		return unavailable;
	}
	
	public void addUnavailable(Time t){
		for(int i=0; i <t.getTimeSlots().length; i++){
			unavailable.add(t.getTimeSlots()[i]);
		}
	}
	/**
	 * Checks if Professor is Available during Slots
	 * @param slots
	 * @return true if Professor is available 
	 * false if professor already has a class during that time
	 */
	public boolean isAvailable(Time slots){
		boolean available = true;
		for(TimeSlot unavailableTimes : this.unavailable){
            for(int i=0; i<slots.getTimeSlots().length; i++){
            	if(unavailableTimes.conflicts(slots.getTimeSlots()[i]))
            		available = false;
            }
        }
		return available;
	}
	
	
}