package lb.edu.aub.cmps.classes;

import java.util.HashSet;

import lb.edu.aub.cmps.enums.Type;

/*
 *	class_capacity: All sections together
 *	section_numbers: Set of section numbers
 *	room
 *	Professor
 *	type: lecture, computer_lab, none,u_chat, music, art 
 */

public abstract class Class {
	private int class_capacity;
	private HashSet<Integer> section_numbers;
	private Room room;
	private Time time;
	private Professor Professor;
	private Type type;

	public Class(int class_capacity, HashSet<Integer> section_numbers,
			Room room, Time time, Professor Professor, Type type) {
		this.class_capacity = class_capacity;
		this.section_numbers = section_numbers;
		this.room = room;
		this.Professor = Professor;
		this.type = type;
	}

	public int getClass_capacity() {
		return class_capacity;
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

	public Room getRoom() {
		return room;
	}

	public Time getTime() {
		return time;
	}

	public Professor getProfessor() {
		return Professor;
	}

	public Type getType() {
		return type;
	}
}