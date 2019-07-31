package com.itproject.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import com.itproject.domain.Student;
import com.itproject.domain.enums.Sex;

public interface IStudentRepository extends IUserGenericRepository<Student, UUID> {
	
	public Optional<Student> findByNameAndSurnamesAndSexAndStartDate(String name, String surnames, Sex sex, LocalDate startDate);
	
}
