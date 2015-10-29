package lb.edu.aub.cmps.grad;

import java.util.HashSet;

import enums.Type;

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
		this.setClass_capacity(class_capacity);
		this.setSection_number(section_numbers);
		this.setRoom(room);
		this.instructor = instructor;
		this.type = type;
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

	public void setSection_number(HashSet<Integer> section_number) {
		this.section_numbers = section_number;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Instructor getInstructor() {
		return instructor;
	}


	public Type getType() {
		return type;
	}

}