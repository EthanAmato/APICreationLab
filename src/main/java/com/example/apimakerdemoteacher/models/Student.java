package com.example.apimakerdemoteacher.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Tells sb that this class is an object that should be mapped to a database
@Entity
public class Student {
//	When you want to set an ID in springboot, you need to give it the ID tag
	@Id
	// Tells SB what strategy to use for generating a new id when adding a new
	// object to DB
	// GenerationType.AUTO: This strategy allows the persistence provider to choose
	// the most appropriate strategy based on the underlying database and other
	// factors. This is the default strategy if none is specified.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	// Define a series of other properties for our model that will show up as
	// columns in our
	// database
	private String firstName;
	private String lastName;
	private String stateOfResidence;
	private String occupation;
	private boolean lovesClass;

//	Let's flesh out this class with getters, setters, a fullarg and noarg constructor, and a toString method
//	Typically, we can reduce this boilerplate with a package called lombok, but i believe it takes some additional
//	installs that would take up a bit of time, so lets do things by hand this time

	public Student(Long id, String firstName, String lastName, String stateOfResidence, String occupation,
			boolean lovesClass) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.stateOfResidence = stateOfResidence;
		this.occupation = occupation;
		this.lovesClass = lovesClass;
	}

	public Student() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStateOfResidence() {
		return stateOfResidence;
	}

	public void setStateOfResidence(String stateOfResidence) {
		this.stateOfResidence = stateOfResidence;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public boolean isLovesClass() {
		return lovesClass;
	}

	public void setLovesClass(boolean lovesClass) {
		this.lovesClass = lovesClass;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", stateOfResidence="
				+ stateOfResidence + ", occupation=" + occupation + ", lovesClass=" + lovesClass + "]";
	}

}
