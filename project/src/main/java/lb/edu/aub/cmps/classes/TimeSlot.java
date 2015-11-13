package lb.edu.aub.cmps.classes;

public class TimeSlot {
	private Day day;
	private int day_id;
	private String start;
	private String end;

	/**
	 * commented so that the mapper can function normally
	 */
/*
	TimeSlot(Day day, String start, String end) {
		this.day = day;
		this.start = start;
		this.end = end;
	}
*/
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
		 * TODO we can add this here for the sake of toString. Works well
		 */
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
					&& (Integer.parseInt(this.getStart()) <= Integer
							.parseInt(time2.getEnd()))) {
				conflict = true;
			} else if (Integer.parseInt(this.getStart()) < Integer
					.parseInt(time2.getStart())
					&& (Integer.parseInt(this.getEnd()) >= Integer
							.parseInt(time2.getStart()))) {

				conflict = true;
			}
		}
		return conflict;
	}

	public TimeSlot nextTimeSlot() {
		TimeSlot t = new TimeSlot();
		Day d = this.getDay();
		t.setDay(d);
		String newStart;
		String newEnd;
		if (d == Day.T || d == Day.R) {
			// we must add 1: 15 to it
			newStart = addtime(this.getStart(), 1, 30);
			newEnd = addtime(this.getEnd(), 1, 30);
		} else {
			newStart = addtime(this.getStart(), 1, 00);
			newEnd = addtime(this.getEnd(), 1, 00);
		}
		t.setStart(newStart);
		t.setEnd(newEnd);
		return t;
	}

	private static String addtime(String start, int h, int min) {
		int h2 = Integer.parseInt(start.substring(0, 2)) + h;
		int min2 = Integer.parseInt(start.substring(2)) + min;

		if (min2 >= 60) {
			min2 = min2 - 60;
			h2 = h2 + 1;
		}
		String strh = (h2 < 10) ? "0" + h2 : h2 + "";
		String strmin = (min2 < 10) ? "0" + min2 : min2 + "";

		return strh + strmin;
	}
	/*
	 * public static void main(String[] args) { TimeSlot t = new TimeSlot();
	 * t.setDay(Day.W); t.setStart("1000"); t.setEnd("1050");
	 * System.out.println(t); System.out.println(t.nextTimeSlot());
	 * System.out.println(t.nextTimeSlot().nextTimeSlot()); }
	 */
}
