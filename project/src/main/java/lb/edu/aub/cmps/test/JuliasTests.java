package lb.edu.aub.cmps.test;

import java.util.Arrays;
import java.util.TreeSet;


public class JuliasTests {

	public static void main(String[] args) {
		/**
		 * SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
		for(Class c: cm.getAllClasses()){
			System.out.println(c);
			Professor p = cm.getProfessor(c.getClass_id());
			System.out.println(p);
			Room r = cm.getClassroom(c.getClass_id());
			System.out.println(r.getNumber());
		}
		
		*/
		TreeSet<Integer> set = new TreeSet<Integer>(new IntegerComparator());
		set.add(50);
		set.add(500);
		System.out.println(Arrays.toString(set.toArray()));
/*
		for (Class c : cm.getAllClasses()) {
			// System.out.println("PROF: " + cm.getProfessor(c.getClass_id()));
			c.setProfessor(cm.getProfessor(c.getClass_id()));
			TimeSlot[] timeSlots = cm.getClassTimes(c.getClass_id());
			Time classTime = new Time(timeSlots);
			c.setTime(classTime);
			c.setRoom(cm.getClassroom(c.getClass_id()));
		}*/
	}
}
