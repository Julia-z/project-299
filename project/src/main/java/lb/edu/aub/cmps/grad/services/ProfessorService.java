package lb.edu.aub.cmps.grad.services;

import java.util.Set;


import org.apache.ibatis.session.SqlSession;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Professor;
import lb.edu.aub.cmps.grad.mappers.ProfessorMapper;

/**
 * Uses the mappers to build a set of the professors saved in the database, with
 * all the classes each one is giving.
 * 
 * @author Bilal Abi Farraj
 */
public class ProfessorService implements ProfessorMapper {

	/**
	 * @return a set of all professors in the database with their details
	 */
	public Set<Professor> getAllProfessors() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
			Set<Professor> profs = pm.getAllProfessors();
			for(Professor prof: profs){
				prof.setClasses(pm.getClassesGiven(prof.getId()));
			}
			return profs;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

	/**
	 * @return the classes given by a certain professor
	 */
	public Set<Class> getClassesGiven(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
			Set<Class> classes = pm.getClassesGiven(id);
			return classes;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

	public Set<Professor> getProfessorsByClass(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {
			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
			return  pm.getProfessorsByClass(id);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
