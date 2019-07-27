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
import org.springframework.test.context.junit4.SpringRunner;

import com.itproject.application.*;
import com.itproject.domain.Student;
import com.itproject.domain.User;
import com.itproject.domain.enums.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RepositoryTests {
	
	@Autowired
	private UserController userController;
	@Autowired
	private StudentController studentController;
	private User user;
	private Student student;
	
	@Before
	public void setUp() {
		user = new User("username", "password", "name", "surnames", UserRole.TEACHER);
		student = new Student("username", "password", "name", "surnames", UserRole.TEACHER,
				"mail", Sex.M, Conclusion.ELIGIBLE, LocalDate.of(2019,01,01), LocalDate.of(2019,04,01));
	}
	
	@Test
	public void injectedComponentsAreNotNull(){
		assertThat(userController).isNotNull();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void userNotExists() throws Exception {
		UUID id = UUID.randomUUID();
		userController.findById(id);
	}
	
	@Test
	public void saveNewUser() throws Exception {
		userController.save(user);
		User newUser = userController.findById(user.getId());
		assertTrue(newUser.equals(user));
	}
	
	@Test
	public void saveNewStudent() throws Exception {
		studentController.save(student);
		Student newStudent = studentController.findById(student.getId());
		assertTrue(newStudent.equals(student));
	}
	
}
