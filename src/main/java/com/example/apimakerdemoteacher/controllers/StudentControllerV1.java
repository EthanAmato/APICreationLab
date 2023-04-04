package com.example.apimakerdemoteacher.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api/v1")
public class StudentControllerV1 {

	// Lets make some endpoints using the repository object that springboot made for
	// us with the interface we made inject dependencies into a Spring-managed bean.
	// It's a type of dependency injection that allows you to eliminate boilerplate
	// code that is required to manually wire dependencies together. 
	@Autowired
	StudentRepository studentRepository;

	//Optional Query Parameters (e.g. /api/v1/students?state=Virginia)
	@GetMapping("/students")
	public List<Student> getStudents(@RequestParam(value = "state", required = false) String state) {
		if (state != null) {
			System.out.println(state);
			return (List<Student>) studentRepository.findByStateOfResidence(state);
		}

		return (List<Student>) studentRepository.findAll();
	}

	@GetMapping("/students/{id}")
	public Optional<Student> getStudentById(@PathVariable(value = "id") Long id) {
		return studentRepository.findById(id);
	}

	
/*
Example Post Request Body: (Id is handled for us by Spring because of the @GeneratedValue tag)
{
    "firstName":"Ethan",
    "lastName":"Amato",
    "stateOfResidence":"NewJersey",
    "occupation":"Teaching Fellow",
    "lovesClass": true
}
*/
	
	@PostMapping("/students")
	public void createStudent(@RequestBody Student user) {
		System.out.println(user);
		studentRepository.save(user);
	}

	
}
