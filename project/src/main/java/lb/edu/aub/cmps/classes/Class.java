package lb.edu.aub.cmps.classes;

import java.util.HashSet;

import lb.edu.aub.cmps.enums.Type;


/*
 *	class_capacity: All sections together
 *	section_numbers: Set of section numbers
 *	room
 *	instructor
 *	type: lecture, computer_lab, none,u_chat, music, art 
 */

public abstract class Class {
	private int class_capacity;
	private HashSet<Integer> section_numbers;
	private Room room;
	private Time time;
	private Instructor instructor;
	private Type type;
	
	public Class(int class_capacity, HashSet<Integer> section_numbers, Room room, Time time, Instructor instructor, Type type){
		this.class_capacity = class_capacity;
		this.section_numbers = section_numbers;
		this.room = room;
		this.instructor = instructor;
		this.type = type;
	}



	public HashSet<Integer> getSection_number() {
		return section_numbers;
	}


	public boolean scheduleClass(){
		//find a time slot and a room and set them
		return true;
	}



	public Room getRoom() {
		return room;
	}



	public Time getTime() {
		return time;
	}



	public Instructor getInstructor() {
		return instructor;
	}

}