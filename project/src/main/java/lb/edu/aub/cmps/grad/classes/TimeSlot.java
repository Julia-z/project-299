package lb.edu.aub.cmps.grad.classes;
/**
 * Defines the object TimeSlot
 * 
 * @author Julia El Zini
 * @author Bilal Abi Farraj
 */

public class TimeSlot {
	private Day day;
	private int day_id;
	private String start;
	private String end;
	private static int maxHours = 19;
	private static int minHours = 8;


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

		if (day_id == 1) {
			day = Day.M;
		} else if (day_id == 2) {
			day = Day.T;
		} else if (day_id == 3) {
			day = Day.W;
		} else if (day_id == 4) {
			day = Day.R;
		} else if (day_id == 5) {
			day = Day.F;
		} else if (day_id == 6) {
			day = Day.S;
		} else if (day_id == 7) {
			day = Day.U;
		}
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		String s = (start.length() < 4) ? "0" + start : start;
		this.start = s;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		String e = (end.length() < 4) ? "0" + end : end;
		this.end = e;
	}

	public String toString() {
		return day + ": " + start.substring(0, 2) + ":" + start.substring(2, 4)
				+ " -> " + end.substring(0, 2) + ":" + end.substring(2, 4);
	}

	public boolean conflicts(TimeSlot time2) {
		boolean conflict = false;
		if (this.getDay() == time2.getDay()) {
			if (Integer.parseInt(this.getStart()) == Integer.parseInt(time2
					.getStart())) {
				conflict = true;
			} else if (Integer.parseInt(this.getStart()) > Integer
					.parseInt(time2.getStart())
					&& (Integer.parseInt(this.getStart()) < Integer
							.parseInt(time2.getEnd()))) {
				conflict = true;
			} else if (Integer.parseInt(this.getStart()) < Integer
					.parseInt(time2.getStart())
					&& (Integer.parseInt(this.getEnd()) > Integer
							.parseInt(time2.getStart()))) {

				conflict = true;
			}
		}
		return conflict;
	}

	private TimeSlot changeTimeSlot(int hMWF, int mMWF, int hTR, int mTR) {
		TimeSlot t = new TimeSlot();
		Day d = this.getDay();
		t.setDay(d);
		String newStart;
		String newEnd;
		if (d == Day.T || d == Day.R) {
			// we must add 1: 15 to it
			newStart = addtime(this.getStart(), hMWF, mMWF);
			newEnd = addtime(this.getEnd(), hMWF, mMWF);
		} else {
			newStart = addtime(this.getStart(), hTR, mTR);
			newEnd = addtime(this.getEnd(), hTR, mTR);
		}
		if (newStart == null || newEnd == null)
			return null;
		t.setStart(newStart);
		t.setEnd(newEnd);
		return t;
	}

	public TimeSlot nextTimeSlot() {
		return changeTimeSlot(1, 30, 1, 0);
	}

	public TimeSlot previousTimeSlot() {

		return changeTimeSlot(-1, -30, -1, 0);
	}

	/**
	 * 
	 * @param start
	 * @param h
	 * @param min
	 * @return null in case we can shift the time by h hours and min minutes
	 *         anymore
	 */
	private static String addtime(String start, int h, int min) {
		int h2 = Integer.parseInt(start.substring(0, 2)) + h;
		int min2 = Integer.parseInt(start.substring(2)) + min;
	//	System.out.println();
		//System.out.println(h2 + ">"+min2);
		if(min2 <0){
			min2 = 60 - Math.abs(min2);
			h2 --;
		}
		if (min2 >= 60) {
			min2 = min2 - 60;
			h2 = h2 + 1;
		}
		if (h2 > maxHours || h2 < minHours)
			return null;
		String strh = (h2 < 10) ? "0" + h2 : h2 + "";
		String strmin = (min2 < 10) ? "0" + min2 : min2 + "";

		return strh + strmin;
	}

	// t2 has to have the same day as this
	// return 1 if (this.start < t2.start)
	// -1 if (this.start > t2.start)
	// 0 otherwise
	public int compareTo(TimeSlot t2) {
		int daydiff = this.day_id - t2.day_id;
		if (daydiff < 0)
			return -1;
		else if (daydiff > 0)
			return +1;
		else {
			int start1 = Integer.parseInt(this.start);
			int start2 = Integer.parseInt(t2.start);
			if (start1 < start2)
				return -1;
			else if (start1 > start2)
				return 1;
			else
				return 0;
		}
	}

	public static void main(String[] args) {
		TimeSlot t = new TimeSlot();
		t.setDay(Day.M);
		t.setStart("1200");
		t.setEnd("1250");
		TimeSlot t2 = new TimeSlot();
		t2.setDay(Day.R);
		t2.setStart("800");
		t2.setEnd("915");
		
		TimeSlot t3 = new TimeSlot();
		t3.setDay(Day.R);
		t3.setStart("1200");
		t3.setEnd("1250");
		System.out.println(t);
		System.out.println(t.nextTimeSlot());
		//System.out.println(t.nextTimeSlot().nextTimeSlot());
	}

}
