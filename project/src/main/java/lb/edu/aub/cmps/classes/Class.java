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
	private Room room;
	private Time time;
	private Professor Professor;
	private String type;
	private int type_id;


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

	/**
	 * TODO
	 * @return
	 */
	public boolean scheduleClass() {
		// find the best time slot and  room and set them
		return true;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean bestScheduleClass() {
		// find the best time slot and  room and set them
		return true;
	}
	
	/**
	 * TODO
	 * @return
	 */
	public boolean secondScheduleClass() {
		// find the best time slot and  room and set them
		return true;
	}
	public Room getRoom() {
		return room;
	}

	public Time getTime() {
		return time;
	}

	public Professor getProfessor() {
		return Professor;
	}

	public String getType() {
		return type;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
	/**
	 * For the sake of testing
	 * private int class_id;
	private int course_id;
	private int class_capacity;
	private HashSet<Integer> section_numbers;
	private Room room;
	private Time time;
	private Professor Professor;
	private String type;
	private int type_id;
	 */
	public String toString(){
		return "Class id: "+class_id+", Course id: "+course_id+", Type :"+type;
	}
}