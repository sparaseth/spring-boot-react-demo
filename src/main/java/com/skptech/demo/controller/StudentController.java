package com.skptech.demo.controller;

import lombok.AllArgsConstructor;
import com.skptech.demo.model.Student;
import com.skptech.demo.model.StudentCourse;
import com.skptech.demo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("{studentId}/courses")
    public List<StudentCourse> getAllStudentCourses(@PathVariable("studentId") UUID studentId) {
        return studentService.getAllStudentCourses(studentId);
    }

    @PostMapping
    public void addNewStudent(@RequestBody @Valid Student student) {
        studentService.saveStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") UUID studentId, @RequestBody Student student) {
        studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") UUID studentId) {
        studentService.deleteStudent(studentId);
    }
}
