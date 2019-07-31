package com.itproject.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itproject.domain.Student;
import com.itproject.domain.enums.Sex;
import com.itproject.application.dto.StudentDTO;
import com.itproject.persistence.IStudentRepository;
import com.itproject.utilities.InvalidParamException;
import com.itproject.utilities.NotFoundException;

@Controller
public class StudentController {
	
	@Autowired
	private IStudentRepository repository;
	
	public Student save(Student student) throws InvalidParamException {
		if (student == null)
			throw new InvalidParamException();
		try {
			return repository.save(student);
		} catch (Exception e) {
			throw new InvalidParamException();
		}
	}
	
	public Student getStudent(String name, String surnames, Sex sex, LocalDate startDate) throws NotFoundException {
		return repository.findByNameAndSurnamesAndSexAndStartDate(name, surnames, sex, startDate).orElseThrow(() 
				-> new NotFoundException());
	}
	
	public Student getStudent(UUID id) throws NotFoundException {
		return repository.findById(id).orElseThrow(() -> new NotFoundException());
	}
	
	public StudentDTO getStudentDTO(UUID id) throws NotFoundException {
		return new StudentDTO(getStudent(id));
	}
	
	public List<StudentDTO> listStudents() throws NotFoundException {
		Iterable<Student> studentList = repository.findAll();
		List<StudentDTO> studentDTOList = new ArrayList<>();
		
		for (Student student : studentList) {
			studentDTOList.add(new StudentDTO(student));
		}
		
		return studentDTOList;
	}
	
	public StudentDTO updateStudent(UUID studentId, StudentDTO studentToUpdate)
			throws NotFoundException, InvalidParamException {
		Student student = getStudent(studentId);
		
		if (!(studentToUpdate.getDesk() == 0)) {
			student.setDesk(studentToUpdate.getDesk());
		}
		
		Student updatedStudent = save(student);
		return new StudentDTO(updatedStudent);
	}
	
	public Map<Sex, Long> getPercentagesBySex() {
		Map<Sex, Long> percentages = new HashMap<>();
		Long manPercentage = (repository.countMan() / repository.count()) * 100;
		Long womanPercentage = (repository.countWoman() / repository.count()) * 100;
		percentages.put(Sex.M, manPercentage);
		percentages.put(Sex.F, womanPercentage);
		return percentages;
	}
	
}
