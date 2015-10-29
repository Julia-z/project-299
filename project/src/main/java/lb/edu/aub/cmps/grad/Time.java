package lb.edu.aub.cmps.grad;

public class Time {
	//starting time
	private int startHour, startMinutes;
	private int[] days; //of length 5; if days[i] !=0 then the class is given for days[i] minutes
	
	public Time(int startHour, int startMinutes, int[] days, int duration){
		this.startHour= startHour;
		this.startMinutes= startMinutes;
		this.days= days;
	}

	public void addstartHours(int added){
		int result= startHour+added;
		if(result<= 12){
			this.startHour= result;
		}
		else{
			this.startHour= result-12;
		}
	}
	
	public void addstartMinutes(int added){
		int result= startMinutes+added;
		if(result<= 59){
			this.startMinutes= result;
		}
		else{
			int newstartHours= (result/60);
			addstartHours(newstartHours);
			this.startMinutes= result-(newstartHours*60);
		}
	}
	
	public int getstartHour() {
		return startHour;
	}

	public int getstartMinutes() {
		return startMinutes;
	}
	
	public int[] getDays() {
		return days;
	}


	public String toString(){
		if(startMinutes<10){
			return startHour+":0"+startMinutes;
		}
		return startHour+":"+startMinutes;
	}
	
	public boolean conflicts(Time time2){
		/**
		 * TODO
		 */
		return false;
	}
}