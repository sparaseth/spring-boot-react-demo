package com.skptech.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    private UUID studentId;
    private UUID courseId;
    private String name;
    private String description;
    private String department;
    private String teacherName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer grade;

}
