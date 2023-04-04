package com.example.apimakerdemoteacher.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimakerdemoteacher.models.Student;
import com.example.apimakerdemoteacher.repositories.StudentRepository;

//Request Mapping tells SB that to access any of the endpoints defined in a controller, it needs to be preceded by whatever
//is in the path parameter. In this case, we would need to send a get request to localhost:8080/api/students to activate that code

//Controller tells SB that this class will activate code based on how the user interacts with our website / where they go on website
//The 'Rest' part of RestController specifies that we're just sending back String / JSON bodies rather than web pages

@RestController
@RequestMapping("/api/v2")
public class StudentControllerV2 {

	// Lets make some endpoints using the repository object that springboot made for
	// us with the interface we made inject dependencies into a Spring-managed bean.
	// It's a type of dependency injection that allows you to eliminate boilerplate
	// code that is required to manually wire dependencies together.
	@Autowired
	StudentRepository studentRepository;

	
//	We enforce the query parameter in this case
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getStudents(@RequestParam(value = "state", required = true) String state) {
		if (state.equals(null)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>((List<Student>) studentRepository.findByStateOfResidence(state), HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Optional<Student>> getStudentById(@PathVariable(value = "id") Long id) {
		Optional<Student> student = studentRepository.findById(id);
		if (!student.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(studentRepository.findById(id), HttpStatus.OK);
	}

	/*
	 * Example Post Request Body: (Id is handled for us by Spring because of
	 * the @GeneratedValue tag) 
	 * { 
		 * "firstName":"Ethan", 
		 * "lastName":"Amato",
		 * "stateOfResidence":"NewJersey", 
		 * "occupation":"Teaching Fellow", 
		 * "lovesClass": true 
		 * }
	 */

//	The BindingResult parameter captures any errors or issues that arise during the 
//	validation process. In this case, if there are any validation errors, the method 
//	returns a view name of "addStudentForm". If there are no errors, the method saves
//	the student data to the database and redirects to the "students" page.
	@PostMapping("/students")
	public ResponseEntity<Void> createUser(@RequestBody Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		studentRepository.save(new Student(student.getId(), student.getFirstName(), student.getLastName(),
				student.getStateOfResidence(), student.getOccupation(), student.isLovesClass()));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
