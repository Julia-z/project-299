package lb.edu.aub.cmps.grad.services;

import java.util.HashSet;
import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.Time;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.mappers.ClassMapper;

import org.apache.ibatis.session.SqlSession;

/**
 * Uses the mappers to build a set of the classes saved in the database, with
 * all its details: Professor, Classroom, Time etc…
 * 
 * @author Bilal Abi Farraj
 */
public class ClassService implements ClassMapper {

	/**
	 * @return a set of all classes in the database with all their details
	 */
	public Set<Class> getAllClasses() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();

		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> allClasses = cm.getAllClasses();
			for (Class c : allClasses) {
				int id = c.getClass_id();
				c.setCanChangeRoom(!c.canChangeRoom());
				c.setCanChangeTime(!c.canChangeTime());
				TimeSlot[] times = cm.getClassTimes(id);
				c.setTime(new Time(times));

				Professor p = cm.getProfessor(id);
				if (p != null)
					p.initializeUnavailable();
				/***
				 * TODO BILAL
				 * get multi profs
				 */
				Set<Professor> ps = new HashSet<Professor>();
				ps.add(p);
				c.setProfessors(ps);
				Room r = cm.getClassroom(id);
				if (r != null)
					r.initializeReserved();
				c.setRoom(r);

				Set<Integer> acc = cm.getAccessoriesInClass(id);
				c.setAccessoriesIds(acc);

				Set<Integer> section_numbers = cm.getSectionsInClass(id);
				c.setSection_number(section_numbers);

			}

			/**
			 * In case we wanna loop over all professors and such
			 * ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
			 * Set<Professor> allProfessors= pm.getAllProfessors(); RoomMapper
			 * rm = sqlSession.getMapper(RoomMapper.class); Set<Room> allRooms=
			 * rm.getAllRooms();
			 * 
			 * for(Professor p: allProfessors){ if(p.getC) }
			 **/
			return allClasses;
		} finally {
			sqlSession.close();
		}
	}

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
	 * @return class times of a certain class
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
	 * @return class's prozfessor
	 */
	public Professor getProfessor(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Professor prof = cm.getProfessor(id);
			return prof;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * @return a set of all accessories in a certain class
	 */
	public Set<Integer> getAccessoriesInClass(int id) {
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
	 * @return class's room
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
	 * @return the type of the class
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

	/**
	 * Updates the room of class c in the database based on the new given room
	 */
	public void updateLecture_Classroom(Class c) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
			classMapper.updateLecture_Classroom(c);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Updates the time of class c in the database based on the new given time
	 */
	public void updateLecture_Time(Class c) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);
			classMapper.updateLecture_Time(c);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getLowerCampusLectures() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getLowerCampusLectures();
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getLabs() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getLabs();
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getUpperCampusLectures() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getUpperCampusLectures();
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getRecitations() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getRecitations();
		} finally {
			sqlSession.close();
		}
	}

}
