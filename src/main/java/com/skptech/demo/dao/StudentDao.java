package com.skptech.demo.dao;

import com.skptech.demo.model.Student;
import com.skptech.demo.model.StudentCourse;

import java.util.List;
import java.util.UUID;

public interface StudentDao {

    List<Student> getAllStudents();

    int saveStudent(UUID studentId, Student student);

    boolean isEmailTakenAlready(String email);

    List<StudentCourse> getAllStudentCourses(UUID studentId);

    int updateEmail(UUID studentId, String email);

    int updateFirstName(UUID studentId, String firstName);

    int updateLastName(UUID studentId, String lastName);

    boolean checkEmailExists(UUID studentId, String email);

    int deleteStudentById(UUID studentId);

}
