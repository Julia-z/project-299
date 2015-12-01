package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Class;
import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Room;
import lb.edu.aub.cmps.classes.Time;
import lb.edu.aub.cmps.classes.TimeSlot;
import lb.edu.aub.cmps.mappers.ClassMapper;

import org.apache.ibatis.session.SqlSession;

public class ClassService implements ClassMapper {
	/**
	 * Gets all classes and puts them in a set
	 */
	public Set<Class> getAllClasses() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();

		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> allClasses = cm.getAllClasses();
			for (Class c : allClasses) {
				int id = c.getClass_id();
				TimeSlot[] times = cm.getClassTimes(id);
				c.setTime(new Time(times));

				Professor p = cm.getProfessor(id);
				if(p!=null)	p.initializeUnavailable();
				c.setProfessor(p);
				Room r = cm.getClassroom(id);
				if(r!=null)	r.initializeReserved();
				c.setRoom(r);
				
				Set<Integer> acc = cm.getAccessoriesInClass(id);
				c.setAccessoriesIds(acc);

				/*System.out.println("Class id: " + c.getClass_id()
						+ ", Course id: " + c.getCourse_id() + ", Type :"
						+ c.getType() + ", Time: " + c.getTime().toString());*/
			}

			/**
			 * In case we wanna loop over all professors and such ProfessorMapper
			 * pm = sqlSession.getMapper(ProfessorMapper.class); Set<Professor>
			 * allProfessors= pm.getAllProfessors(); RoomMapper rm =
			 * sqlSession.getMapper(RoomMapper.class); Set<Room> allRooms=
			 * rm.getAllRooms();
			 * 
			 * for(Professor p: allProfessors){ if(p.getC) }
			 **/
			return allClasses;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * TODO mapper not implemented Gets the sections of a certain class
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
			Professor prof = cm.getProfessor(id);
			return prof;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * Creates a set of all accessories in a certain class
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
