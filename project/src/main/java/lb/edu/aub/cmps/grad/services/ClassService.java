package lb.edu.aub.cmps.grad.services;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.classes.Room;
import lb.edu.aub.cmps.grad.classes.Time;
import lb.edu.aub.cmps.grad.classes.TimeSlot;
import lb.edu.aub.cmps.grad.mappers.ClassMapper;

import org.apache.ibatis.annotations.Param;
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
	public Set<Class> getAllClasses(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();

		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getAllClasses(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Integer> getSectionsInClass(@Param("id") int id,
			@Param("term") int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getSectionsInClass(id, term);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * @return class times of a certain class
	 */
	public TimeSlot[] getClassTimes(@Param("id") int id, @Param("term") int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getClassTimes(id, term);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * @return class's prozfessor
	 */
	public Professor getProfessor(@Param("id") int id, @Param("term") int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Professor prof = cm.getProfessor(id, term);
			return prof;
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * @return a set of all accessories in a certain class
	 */
	public Set<Integer> getAccessoriesInClass(@Param("id") int id,
			@Param("term") int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getAccessoriesInClass(id, term);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * @return class's room
	 */
	public Room getClassroom(@Param("id") int id, @Param("term") int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getClassroom(id, term);
		} finally {
			sqlSession.close();
		}
	}

	/**
	 * @return the type of the class
	 */
	public String getType(@Param("id") int id, @Param("term") int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			return cm.getType(id, term);
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

	public Set<Class> getLowerCampusLectures(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getLowerCampusLectures(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getLabs(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getLabs(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getUpperCampusLectures(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getUpperCampusLectures(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getLowerCampusRecitations(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getLowerCampusRecitations(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getUpperCampusRecitations(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getUpperCampusRecitations(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getTime_fixed_classes(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getTime_fixed_classes(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getLoc_fixed_classes(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getLoc_fixed_classes(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getGrad_classes(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getGrad_classes(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	public Set<Class> getBig_lectures(int term) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ClassMapper cm = sqlSession.getMapper(ClassMapper.class);
			Set<Class> classes = cm.getBig_lectures(term);
			classes = fillClasses(classes, cm, term);
			return classes;
		} finally {
			sqlSession.close();
		}
	}

	private Set<Class> fillClasses(Set<Class> classes, ClassMapper cm, int term) {

		for (Class c : classes) {
			int id = c.getClass_id();
			c.setCanChangeRoom(!c.canChangeRoom());
			c.setCanChangeTime(!c.canChangeTime());
			TimeSlot[] times = cm.getClassTimes(id, term);
			c.setTime(new Time(times));

			Set<Professor> ps = new ProfessorService().getProfessorsByClass(id);
			c.setProfessors(ps);

			Room r = cm.getClassroom(id, term);
			if (r != null)
				r.initializeReserved();
			c.setRoom(r);

			Set<Integer> acc = cm.getAccessoriesInClass(id, term);
			c.setAccessoriesIds(acc);

			Set<Integer> section_numbers = cm.getSectionsInClass(id, term);
			c.setSection_number(section_numbers);

		}
		return classes;
	}
}
