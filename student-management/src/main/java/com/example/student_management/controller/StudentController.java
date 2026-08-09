package com.example.student_management.controller;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/name/{firstName}")
    public List<Student> getStudentByName(@PathVariable String firstName) {
        return studentService.getStudentByName(firstName);
    }

    @GetMapping("/roll/{rollNumber}")
    public Student getStudentByRollNumber(@PathVariable Integer rollNumber) {
        return studentService.getStudentByRollNumber(rollNumber);
    }

    @PostMapping
    public Student addStudent(@RequestBody StudentDTO dto) {
        return studentService.addStudent(dto);
    }

    @PutMapping("/{rollNumber}")
    public Student updateStudent(
            @PathVariable Integer rollNumber,
            @RequestBody StudentDTO dto) {
        return studentService.updateStudent(rollNumber, dto);
    }

    @GetMapping("/count")
    public long getStudentCount() {
        return studentService.getStudentCount();
    }

    @DeleteMapping("/roll/{rollNumber}")
    public String deleteStudent(@PathVariable Integer rollNumber) {
        studentService.deleteStudent(rollNumber);
        return "Student deleted successfully";
    }

    @DeleteMapping("/lastname/{lastName}")
    public String deleteStudentsByLastName(@PathVariable String lastName) {
        studentService.deleteStudentsByLastName(lastName);
        return "Students deleted successfully";
    }
}