package com.skptech.demo.service;

import com.skptech.demo.dao.StudentDao;
import com.skptech.demo.exception.ApiRequestException;
import com.skptech.demo.model.Student;
import com.skptech.demo.model.StudentCourse;
import com.skptech.demo.validator.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentDao studentDao;
    private final EmailValidator emailValidator;

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    public void saveStudent(Student student) {
        this.saveStudent(null, student);
    }

    public List<StudentCourse> getAllStudentCourses(UUID studentId) {
        return studentDao.getAllStudentCourses(studentId);
    }

    public void updateStudent(UUID studentId, Student student) {
        updateEmail(studentId, student.getEmail());
        updateFirstName(studentId, student.getFirstName());
        updateLastName(studentId, student.getLastName());
    }

    public void deleteStudent(UUID studentId) {
        studentDao.deleteStudentById(studentId);
    }

    private void saveStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());
        if (!emailValidator.test(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + " is not valid");
        }
        if (studentDao.isEmailTakenAlready(student.getEmail())) {
            throw new ApiRequestException(student.getEmail() + " is already taken");
        }
        studentDao.saveStudent(newStudentId, student);
    }

    private void updateEmail(UUID studentId, String emailToUpdate) {
        Optional.ofNullable(emailToUpdate).ifPresent(email -> {
            boolean isEmailExists = studentDao.checkEmailExists(studentId, email);
            if (!isEmailExists) {
                studentDao.updateEmail(studentId, email);
            } else {
                throw new IllegalStateException("Email already in use: " + email);
            }
        });
    }

    private void updateFirstName(UUID studentId, String firstName) {
        Optional.ofNullable(firstName)
                .filter(s -> !StringUtils.isEmpty(s))
                .map(StringUtils::capitalize)
                .ifPresent(s -> studentDao.updateFirstName(studentId, s));
    }

    private void updateLastName(UUID studentId, String lastName) {
        Optional.ofNullable(lastName)
                .filter(s -> !StringUtils.isEmpty(s))
                .map(StringUtils::capitalize)
                .ifPresent(s -> studentDao.updateLastName(studentId, s));
    }
}
