package com.example.student_management.repository;

import com.example.student_management.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    List<Student> findByFirstName(String firstName);

    Optional<Student> findByRollNumber(Integer rollNumber);

    List<Student> findByLastName(String lastName);

    long countBy();

    void deleteByRollNumber(Integer rollNumber);

    void deleteByLastName(String lastName);
}