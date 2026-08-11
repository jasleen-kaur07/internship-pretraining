package com.example.student_management.service;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentByName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    public Student getStudentByRollNumber(Integer rollNumber) {
        return studentRepository.findByRollNumber(rollNumber)
                .orElse(null);
    }

    public Student addStudent(StudentDTO dto) {
        Student student = new Student();

        student.setRollNumber(dto.getRollNumber());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());

        return studentRepository.save(student);
    }

    public Student updateStudent(Integer rollNumber, StudentDTO dto) {
        Student student = studentRepository.findByRollNumber(rollNumber)
                .orElseThrow();

        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setDateOfBirth(dto.getDateOfBirth());

        return studentRepository.save(student);
    }

    public long getStudentCount() {
        return studentRepository.count();
    }

    public void deleteStudent(Integer rollNumber) {
        studentRepository.deleteByRollNumber(rollNumber);
    }

    public void deleteStudentsByLastName(String lastName) {
        studentRepository.deleteByLastName(lastName);
    }
}