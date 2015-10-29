package lb.edu.aub.cmps.classes;

import lb.edu.aub.cmps.enums.Day;

public class TimeSlot {
	Day day;
	String start;
	String end;
	
	public TimeSlot(Day day, String start, String end){
		if(start.length() < 4) start = "0"+start;
		if(end.length() < 4) end = "0"+end;
		this.start = start;
		this.end = end;
		this.day = day;
	}
	
	public String toString(){
		return day+": "+start.substring(0, 2) +":"+ start.substring(2,4) +" -> "+ end.substring(0, 2) +":"+ end.substring(2, 4);
	}
	
	public static void main(String[] args){
		TimeSlot t = new TimeSlot(Day.M, "1100", "1150");
		System.out.println(t.toString());
	}

}
