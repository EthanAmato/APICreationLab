package com.example.apimakerdemoteacher.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.apimakerdemoteacher.models.Student;

// In SB, we need to tell SB that this class is a Repository - responsible for interacting with a database directly
// Inside of repositories, we specify a series of methods that we want our project to rely on in order to retrieve
// data from our database

// We extend the CrudRepository
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
//		The Magic of Springboot is that it actually writes all of the code for these methods on 
//		its own using the method names as a guidebook of how the method should operate

	@Override
	List<Student> findAll();

	Optional<Student> getStudentById(Long id);

	Optional<List<Student>> getStudentsByStateOfResidence(String state);

	List<Student> findByStateOfResidence(String state);

	/*
	 * // Comes with the following operations by default: // <S extends T> S save(S
	 * entity); // <S extends T> Iterable<S> saveAll(Iterable<S> entities); //
	 * Optional<T> findById(ID id); // boolean existsById(ID id); // Iterable<T>
	 * findAll(); // Iterable<T> findAllById(Iterable<ID> ids); // long count(); //
	 * void deleteById(ID id); // void delete(T entity); // void
	 * deleteAllById(Iterable<? extends ID> ids); // void deleteAll(Iterable<?
	 * extends T> entities); // void deleteAll();
	 */
}
