package lb.edu.aub.cmps.classes;

import lb.edu.aub.cmps.enums.Day;


public class TimeSlot {
	private Day day;
	private int day_id;
	private String start;
	private String end;
	
	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	public int getDay_id() {
		return day_id;
	}
	public void setDay_id(int day_id) {
		this.day_id = day_id;
		/**
		 * TODO
		 * we can add this here for the sake of toString. Works well
		 */
		if(day_id==1){
			day= Day.M;
		}
		else if(day_id==2){
			day= Day.T;
		}
		else if(day_id==3){
			day= Day.W;
		}
		else if(day_id==4){
			day= Day.R;
		}
		else if(day_id==5){
			day= Day.F;
		}
	}

	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

	public String toString(){
		return day+": "+start.substring(0, 2) +":"+ start.substring(2,4) +" -> "+ end.substring(0, 2) +":"+ end.substring(2, 4);
	}
	/*
	public static void main(String[] args){
		TimeSlot t = new TimeSlot(Day.M, "1100", "1150");
		System.out.println(t.toString());
	}*/
}
