package com.itproject.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;

import com.itproject.domain.Student;
import com.itproject.domain.enums.Sex;

public interface IStudentRepository extends IUserGenericRepository<Student, UUID> {
	
	public Optional<Student> findByNameAndSurnamesAndSexAndStartDate(String name, String surnames, Sex sex, LocalDate startDate);
	
	@Query(value = "SELECT count(*) FROM student WHERE sex = 'M'")
	public int countMan();
	
	@Query(value = "SELECT count(*) FROM student WHERE sex = 'F'")
	public int countWoman();
	
}
