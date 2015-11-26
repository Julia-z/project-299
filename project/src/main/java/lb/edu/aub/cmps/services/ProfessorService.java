package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.mappers.ProfessorMapper;

import org.apache.ibatis.session.SqlSession;
import lb.edu.aub.cmps.classes.Class;

public class ProfessorService implements ProfessorMapper {

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

}
