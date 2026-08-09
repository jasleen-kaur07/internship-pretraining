package com.example.student_management.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "profile")
public class Student {

    @Id
    private String id;

    private Integer rollNumber;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}