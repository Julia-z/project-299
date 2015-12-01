package lb.edu.aub.cmps.classes;

import java.util.HashSet;
import java.util.Set;

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
	private String course_name;
	private int class_capacity;
	private HashSet<Integer> section_numbers;
	private Room req_room;
	private Room given_room;
	private Time req_time;
	private Time given_time;
	private Professor Professor;
	private String type;
	private boolean isMet;
	private Set<Integer> accessories_ids;
	/**
	 * TODO read them from the db
	 */
	private boolean canChangeTime;
	private boolean canChangeRoom;

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
		this.req_time = classTime;
	}

	public void setRoom(Room classroom) {
		this.req_room = classroom;
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
	
	public boolean getIsMet(){
		return isMet;
	}
	public void setIsMet(boolean b){
		this.isMet = b;
	}

	public Room getGiven_room() {
		return given_room;
	}

	public void setGiven_room(Room given_room) {
		this.given_room = given_room;
	}

	

	public void setGiven_time(Time given_time) {
		this.given_time = given_time;
	}
	public void setCanChangeTime(boolean canChangeTime){
		this.canChangeTime = canChangeTime;
	}
	
	public void setCanChangeRoom(boolean canChangeRoom){
		this.canChangeRoom = canChangeRoom;
	}
	public void addAccessoryId(int id){
		if(accessories_ids == null) accessories_ids = new HashSet<Integer>();
		accessories_ids.add(id);
	}
	
	public Set<Integer> getAccessoriesIds(){
		return accessories_ids;
	}
	
	public boolean canChangeTime(){
		return canChangeTime;
	}
	
	public boolean canChangeRoom(){
		return canChangeRoom;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	public void setAccessoriesIds(Set<Integer> ids){
		this.accessories_ids = ids;
	}

}