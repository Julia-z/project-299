package lb.edu.aub.cmps.grad.mappers;

import java.util.Set;

import lb.edu.aub.cmps.grad.classes.Class;
import lb.edu.aub.cmps.grad.classes.Professor;

/**
 * @author Bilal Abi Farraj
 */

public interface ProfessorMapper {

	public Set<Professor> getAllProfessors();
	public Set<Class> getClassesGiven(int id);
}
