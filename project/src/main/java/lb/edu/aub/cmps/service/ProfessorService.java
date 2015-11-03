package lb.edu.aub.cmps.service;

import java.util.List;

import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.mappers.ProfessorMapper;

import org.apache.ibatis.session.SqlSession;

public class ProfessorService implements ProfessorMapper {

	public List<Professor> getAllProfessors() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory()
				.openSession();
		try {

			ProfessorMapper pm = sqlSession.getMapper(ProfessorMapper.class);
			List<Professor> profs = pm.getAllProfessors();
			return profs;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sqlSession.close();
		}
		return null;
	}

}
