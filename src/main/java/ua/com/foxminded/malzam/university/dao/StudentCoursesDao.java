package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Student;
import ua.com.foxminded.malzam.university.model.StudentCourses;

public class StudentCoursesDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";
    private static final String SQL_ADD_ROWS = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
    private static final String SQL_DELETE_ROW = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
    private static final String SQL_SHOW_STUDENTS_BU_COURSE_NAME = "SELECT first_name, last_name FROM student_courses INNER JOIN students ON (student_courses.student_id = students.student_id) INNER JOIN courses ON (student_courses.course_id = courses.course_id) WHERE course_name = '?'";
    private static final String SQL_SHOW_COURSES_BU_STUDENT_ID = "SELECT course_name, course_description, courses.course_id FROM student_courses INNER JOIN courses ON (student_courses.course_id = courses.course_id) WHERE student_id = ?";

    public void addOneRow(int studentId, int courseId) {
        Set<StudentCourses> studentCourses = new HashSet<>();
        studentCourses.add(new StudentCourses(studentId, courseId));
        addRows(studentCourses);
    }

    public void addRows(Set<StudentCourses> studentCourses) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ROWS)) {

            for (StudentCourses studentCourse : studentCourses) {
                int studentId = studentCourse.getStudentId();
                int courseId = studentCourse.getCourseId();
                preparedStatement.setInt(1, studentId);
                preparedStatement.setInt(2, courseId);
                preparedStatement.execute();
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.addRows failed...");
            System.out.println(ex);
        }
    }

    public Set<Student> showStudentsByCourseName(String courseName) {
        Set<Student> students = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection
                        .prepareStatement(SQL_SHOW_STUDENTS_BU_COURSE_NAME)) {

            preparedStatement.setString(1, courseName);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    students.add(new Student(firstName, lastName));
                }
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.showStudentsByCourseName failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Set<Course> showCoursesByStudentId(int studentId) {
        Set<Course> courses = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection
                        .prepareStatement(SQL_SHOW_COURSES_BU_STUDENT_ID)) {

            preparedStatement.setInt(1, studentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String courseName = rs.getString("course_name");
                    String courseDescription = rs.getString("course_description");
                    int courseId = rs.getInt("course_id");
                    courses.add(new Course(courseName, courseDescription, courseId));
                }
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.showCoursesByStudentId failed...");
            System.out.println(ex);
        }
        return courses;
    }

    public void deleteRow(int studentId, int courseId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ROW)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.deleteOneRow failed...");
            System.out.println(ex);
        }
    }
}
