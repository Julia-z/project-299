package lb.edu.aub.cmps.services;

import java.util.Set;

import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.mappers.ProfessorMapper;

import org.apache.ibatis.session.SqlSession;

public class ProfessorService implements ProfessorMapper {

	public Set<Professor> getAllProfessors() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
			Set<Professor> profs = pm.getAllProfessors();
			return profs;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
