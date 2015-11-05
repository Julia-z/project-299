package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.LinkedList;

import lb.edu.aub.cmps.enums.Day;

public class Professor {
	private int id;
	private String name;
	private LinkedList<Class> classes;
	private HashSet<TimeSlot> unavailable;
	/*TESTING
	 * public static void main(String[] args){
		TimeSlot[] times = new TimeSlot[3];
		times[0] = new TimeSlot();
		times[0].setDay(Day.M);
		times[0].setStart("1203");
		times[0].setEnd("1300");
		times[1] = new TimeSlot();
		times[1].setDay(Day.T);
		times[1].setStart("0700");
		times[1].setEnd("0730");
		times[2] = new TimeSlot();
		times[2].setDay(Day.F);
		times[2].setStart("0731");
		times[2].setEnd("0830");
		HashSet<TimeSlot> wish = new HashSet<TimeSlot>();
		wish.add(times[0]);
		wish.add(times[2]);
		wish.add(times[1]);
		Professor myProfessor = new Professor();
		myProfessor.id = 1;
		myProfessor.name = "jaber";
		myProfessor.unavailable = wish;

		TimeSlot[] times3 = new TimeSlot[3];
		times3[0] = new TimeSlot();
		times3[0].setDay(Day.M);
		times3[0].setStart("0900");
		times3[0].setEnd("1000");
		times3[1] = new TimeSlot();
		times3[1].setDay(Day.W);
		times3[1].setStart("1700");
		times3[1].setEnd("1730");
		times3[2] = new TimeSlot();
		times3[2].setDay(Day.F);
		times3[2].setStart("1931");
		times3[2].setEnd("1930");
		Time myTime = new Time(times3);
		System.out.println(myProfessor.isAvailable(myTime));
	}*/
	

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