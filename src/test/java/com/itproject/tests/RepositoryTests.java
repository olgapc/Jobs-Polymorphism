package com.itproject.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.UUID;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.itproject.application.*;
import com.itproject.domain.Student;
import com.itproject.domain.User;
import com.itproject.domain.enums.*;

@RunWith(SpringRunner.class)
@DataJpaTest
// Annotation that allows tests to work with the real DB
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
/* With this annotation, actions done in tests don't roll back
 * You must delete the new user/student manually to avoid inconsistencies
*/
public class RepositoryTests {
	
	@Autowired
	private UserController userController;
	@Autowired
	private StudentController studentController;
	private User user;
	private Student student;
	
	// Creates a new user and a new student before each test
	@Before
	public void setUp() {
		user = new User("portaaviones", "nimitz", "Jake", "Petrulla Doncel", UserRole.TEACHER);
		student = new Student("soniasfl", "ssfl", "Sonia", "Sanchez-Fortun Lopez", UserRole.STUDENT,
				"soniasfl@gmail.com", Sex.F, Conclusion.ELIGIBLE, LocalDate.of(2019,4,2), LocalDate.of(2019,8,2));
	}
	
	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(userController).isNotNull();
		assertThat(studentController).isNotNull();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void userNotExists() throws Exception {
		UUID id = UUID.randomUUID();
		userController.findById(id);
	}
	
	// Saves the new user in DB, retrieves it from DB and checks if they are equals
	@Test
	public void saveNewUser() throws Exception {
		userController.save(user);
		User newUser = userController.findById(user.getId());
		assertTrue(newUser.equals(user));
	}
	
	// Saves the new student in DB, retrieves it from DB and checks if they are equals
	@Test
	public void saveNewStudent() throws Exception {
		studentController.save(student);
		Student newStudent = studentController.findById(student.getId());
		assertTrue(newStudent.equals(student));
	}
	
}
