package com.itproject.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import com.itproject.domain.Student;
import com.itproject.domain.enums.Sex;

public interface IStudentRepository extends IGenericRepository<Student, UUID> {
	
	// Auto-generated query from method's name
	// Student who matches the parameters values
	public Optional<Student> findByNameAndSurnamesAndSexAndStartDate(String name, String surnames, Sex sex, LocalDate startDate);
	
	// Auto-generated query from method's name
	// Students number by sex
	public long countBySex(Sex sex);
	
	// Native Query
	// Students with 8 or more absences
	@Query(value = "SELECT * FROM user JOIN student WHERE user.id = student.id AND user.id IN "
			+ "(SELECT student.id FROM student, student_absence WHERE student.id = student_absence.student "
			+ "GROUP BY student.id HAVING count(*) > 7)", nativeQuery = true)
	Iterable<Student> findStudentsWithEightOrMoreAbsences();
	
}
