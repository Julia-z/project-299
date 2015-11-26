package lb.edu.aub.cmps.classes;

import java.util.HashSet;

/*
 *	class_capacity: All sections together
 *	section_numbers: Set of section numbers
 *	room
 *	Professor
 *	type: lecture, computer_lab, none,u_chat, music, art 
 */

public class Class {
	private int class_id;
	private int course_id;
	private int class_capacity;
	private HashSet<Integer> section_numbers;
	private Room req_room;
	private Room given_room;
	private Time req_time;
	private Time given_time;
	private Professor Professor;
	private String type;

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getClass_capacity() {
		return class_capacity;
	}

	public void setClass_capacity(int class_capacity) {
		this.class_capacity = class_capacity;
	}

	public HashSet<Integer> getSection_number() {
		return section_numbers;
	}


	public Professor getProfessor() {
		return Professor;
	}

	public String getType() {
		return type;
	}

	/**
	 * For the sake of testing private int class_id; private int course_id;
	 * private int class_capacity; private HashSet<Integer> section_numbers;
	 * private Room room; private Time time; private Professor Professor;
	 * private String type; private int type_id;
	 */
	public String toString() {
		return "Class id: " + class_id + ", Course id: " + course_id
				+ ", Type :" + type;//+", Room: " +room.getNumber()+", Time: "+time.toString()+", Professor: "+Professor.getName();
	}

	public void setProfessor(Professor Professor) {
		this.Professor = Professor;
	}

	public void setTime(Time classTime) {
		this.given_time = classTime;
	}

	public void setRoom(Room classroom) {
		this.given_room = classroom;
	}
	
	public Time getRequestedTime(){
		return req_time;
	}
	
	public Time getGivenTime(){
		return given_time;
	}
	
	public Room getGivenRoom(){
		return given_room;
	}
	public Room getRequestedRoom(){
		return req_room;
	}

}