package com.example.student_management.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private Integer rollNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}