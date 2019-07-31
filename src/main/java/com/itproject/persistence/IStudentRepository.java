package com.itproject.persistence;

import java.time.LocalDate;

import com.itproject.domain.Student;
import com.itproject.domain.enums.Sex;

public interface IStudentRepository extends IUserGenericRepository<Student> {
	
	//public Student findByName_Surnames_Sex_StartDate(String name, String surnames, Sex sex, LocalDate startDate);
	
	/*
	public Student save(Student student) {
		Student newStudent = repository.findByName_Surnames_Sex_StartDate(student.getName(), 
				student.getSurnames(), student.getSex(), student.getStartDate());
		if (newStudent != null) {
			return repository.save(student);
		} else {
			return (Student)Optional.empty().get();
		}
	}
	*/
	
	/*
	public Student findById(UUID id) {
		if(repository.findById(id).isPresent()) {
			return repository.findById(id).get();
		} else {
			return (Student)Optional.empty().get();
		}
	}
	 */
	
}