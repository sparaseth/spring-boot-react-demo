package com.skptech.demo.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import com.skptech.demo.model.Gender;
import com.skptech.demo.model.Student;
import com.skptech.demo.model.StudentCourse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Student> getAllStudents() {
        String sql = "" +
                "SELECT " +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " gender " +
                "FROM student";

        return jdbcTemplate.query(sql, studentRowMapper());
    }

    public int saveStudent(UUID studentId, Student student) {
        String insertSql = "" +
                "INSERT INTO student (" +
                " student_id, " +
                " first_name, " +
                " last_name, " +
                " email, " +
                " gender) " +
                "VALUES (?, ?, ?, ?, ?::gender)";
        return jdbcTemplate.update(insertSql,
                studentId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase());
    }

    public boolean isEmailTakenAlready(String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM student " +
                " WHERE email = ?" +
                ")";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> resultSet.getBoolean(1), email);
    }

    public List<StudentCourse> getAllStudentCourses(UUID studentId) {
        String sql = "" +
                "SELECT " +
                " student.student_id, " +
                " course.course_id, " +
                " course.name, " +
                " course.description," +
                " course.department," +
                " course.teacher_name," +
                " student_course.start_date, " +
                " student_course.end_date, " +
                " student_course.grade " +
                "FROM student " +
                "JOIN student_course USING (student_id) " +
                "JOIN course         USING (course_id) " +
                "WHERE student.student_id = ?";
        return jdbcTemplate.query(sql, studentCoursesRowMapper(), studentId);
    }

    public int updateEmail(UUID studentId, String email) {
        String sql = "" +
                "UPDATE student " +
                "SET email = ? " +
                "WHERE student_id = ?";
        return jdbcTemplate.update(sql, email, studentId);
    }

    public int updateFirstName(UUID studentId, String firstName) {
        String sql = "" +
                "UPDATE student " +
                "SET first_name = ? " +
                "WHERE student_id = ?";
        return jdbcTemplate.update(sql, firstName, studentId);
    }

    public int updateLastName(UUID studentId, String lastName) {
        String sql = "" +
                "UPDATE student " +
                "SET last_name = ? " +
                "WHERE student_id = ?";
        return jdbcTemplate.update(sql, lastName, studentId);
    }

    public boolean checkEmailExists(UUID studentId, String email) {
        String sql = "" +
                "SELECT EXISTS ( " +
                "   SELECT 1 " +
                "   FROM student " +
                "   WHERE student_id <> ? " +
                "    AND email = ? " +
                ")";
        final Boolean aBoolean = jdbcTemplate.queryForObject(
                sql,
                (resultSet, columnIndex) -> resultSet.getBoolean(1), studentId, email
        );
        return aBoolean;
    }

    public int deleteStudentById(UUID studentId) {
        String sql = "" +
                "DELETE FROM student " +
                "WHERE student_id = ?";
        return jdbcTemplate.update(sql, studentId);
    }

    private RowMapper<StudentCourse> studentCoursesRowMapper() {
        return (resultSet, i) -> {
            StudentCourse studentCourse = new StudentCourse();
            studentCourse.setStudentId(UUID.fromString(resultSet.getString("student_id")));
            studentCourse.setCourseId(UUID.fromString(resultSet.getString("course_id")));
            studentCourse.setName(resultSet.getString("name"));
            studentCourse.setDescription(resultSet.getString("description"));
            studentCourse.setDepartment(resultSet.getString("department"));
            studentCourse.setTeacherName(resultSet.getString("teacher_name"));
            studentCourse.setStartDate(resultSet.getDate("start_date").toLocalDate());
            studentCourse.setEndDate(resultSet.getDate("end_date").toLocalDate());
            studentCourse.setGrade(Optional.ofNullable(resultSet.getString("grade"))
                    .map(Integer::parseInt)
                    .orElse(null));
            return studentCourse;
        };
    }

    private RowMapper<Student> studentRowMapper() {
        return (resultSet, i) -> {
            Student student = new Student();
            student.setStudentId(UUID.fromString(resultSet.getString("student_id")));
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setEmail(resultSet.getString("email"));
            student.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
            return student;
        };
    }

}
