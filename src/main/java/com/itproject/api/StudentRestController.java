package com.itproject.api;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.itproject.application.StudentController;
import com.itproject.application.dto.ExerciseDTO;
import com.itproject.application.dto.StudentAbsenceDTO;
import com.itproject.application.dto.StudentDTO;
import com.itproject.domain.enums.ItineraryName;
import com.itproject.domain.enums.Sex;
import com.itproject.utilities.InvalidParamException;
import com.itproject.utilities.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentRestController
{

    @Autowired
    private StudentController controller;

    // Object to Json converter
    private String toJson(Object object)
    {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(object);
    }

    // Returns a list of the students in Database
    // Call from the web browser http://localhost:8080/students/list
    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public String listStudents() throws NotFoundException
    {
        List<StudentDTO> students = controller.listStudents();
        return toJson(students);
    }

    // Returns the student with id = studentId
    // Call from the web browser http://localhost:8080/students/${studentId}
    @GetMapping(value = "/{studentId}", produces = "application/json;charset=UTF-8")
    public String getStudent(@PathVariable UUID studentId) throws NotFoundException
    {
        StudentDTO student = controller.getStudentDTO(studentId);
        return toJson(student);
    }

    // Returns a list of the student's exercises
    // Call from the web browser http://localhost:8080/students/${studentId}/exercises
    @GetMapping(value = "/{studentId}/exercises", produces = "application/json;charset=UTF-8")
    public String getStudentExercises(@PathVariable UUID studentId) throws NotFoundException
    {
        StudentDTO student = controller.getStudentDTO(studentId);
        List<ExerciseDTO> exercises = controller.getStudentExercises(student);
        return toJson(exercises);
    }

    // Returns a list of the student's absences
    // Call from the web browser http://localhost:8080/students/${studentId}/absences
    @GetMapping(value = "/{studentId}/absences", produces = "application/json;charset=UTF-8")
    public String getStudentAbsences(@PathVariable UUID studentId) throws NotFoundException
    {
        StudentDTO student = controller.getStudentDTO(studentId);
        List<StudentAbsenceDTO> absences = controller.getStudentAbsences(student);
        return toJson(absences);
    }

    // Updates the student with id = studentId
    // Call from a Rest client http://localhost:8080/students/${studentId}
    @PutMapping(value = "/{studentId}", produces = "application/json;charset=UTF-8")
    public String UpdateStudent(@PathVariable UUID studentId, @RequestBody String jStudent)
            throws NotFoundException, InvalidParamException
    {
        StudentDTO studentToUpdate = new Gson().fromJson(jStudent, StudentDTO.class);
        StudentDTO student = controller.updateStudent(studentId, studentToUpdate);
        return toJson(student);
    }

    // Returns percentages by sex
    // Call from the web browser http://localhost:8080/students/sexpercentages
    @GetMapping(value = "/sexpercentages", produces = "application/json;charset=UTF-8")
    public String getPercentagesBySex()
    {
        Map<Sex, Double> percentages = controller.getPercentagesBySex();
        return toJson(percentages);
    }

    // Returns a list of the students with 8 or more absences
    // Call from the web browser http://localhost:8080/students/list/eightormoreabsences
    @GetMapping(value = "/list/eightormoreabsences", produces = "application/json;charset=UTF-8")
    public String listStudentsWithEightOrMoreAbsences() throws NotFoundException
    {
        List<StudentDTO> students = controller.listStudentsWithEightOrMoreAbsences();
        return toJson(students);
    }

    // Returns number by itinerary
    // Call from the web browser http://localhost:8080/students/itineraryNumber
    @GetMapping(value = "/itineraryNumber", produces = "application/json;charset=UTF-8")
    public String getNumberStudentsByItineraryName()
    {
        Map<ItineraryName, Long> numbersByItinerary = controller.getNumberStudentsByItineraryName();
        return toJson(numbersByItinerary);
    }

}
