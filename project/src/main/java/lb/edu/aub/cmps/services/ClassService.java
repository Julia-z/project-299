package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Accessory;
import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;
import lb.edu.aub.cmps.mappers.ClassMapper;

import org.apache.ibatis.session.SqlSession;

public class ClassService implements ClassMapper{
	/**
	 * Gets all classes and puts them in a set
	 */
	public Set<Class> getAllClasses() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> allClasses= cm.getAllClasses();
			for(Class c: allClasses){
				c.setProfessor(cm.getProfessor(c.getClass_id()));
				TimeSlot[] timeSlots= cm.getClassTimes(c.getClass_id());
				Time classTime= new Time(timeSlots);
				c.setTime(classTime);
				c.setRoom(cm.getClassroom(c.getClass_id())); 
			}
			return allClasses;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * TODO mapper not implemented
	 * Gets the sections of a certain class
	 */
	public Set<Integer> getSectionsInClass(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getSectionsInClass(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Creates an array of time slots of a certain class
	 * TODO must be assigned to class's Time in setup
	 */
	public TimeSlot[] getClassTimes(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getClassTimes(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Gets class's professor
	 */
	public Professor getProfessor(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getProfessor(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Creates a set of all accessories in a certain class
	 */
	public Set<Accessory> getAccessoriesInClass(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getAccessoriesInClass(id);
		} finally {
			sqlSession.close();
		}
	}
	
	/**
	 * Gets class's room
	 */
	public Room getClassroom(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getClassroom(id);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * TODO mapper still not written, needing a class Type enum cannot work
	 */
	public String getType(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getType(id);
		} finally {
			sqlSession.close();
		}
	}
	
	
}
