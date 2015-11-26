package lb.edu.aub.cmps.mappers;

import java.util.Set;

import lb.edu.aub.cmps.classes.Professor;
import lb.edu.aub.cmps.classes.Class;

public interface ProfessorMapper {

	public Set<Professor> getAllProfessors();
	public Set<Class> getClassesGiven(int id);
}
