package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Student;
import ua.com.foxminded.malzam.university.model.StudentCourses;

public class StudentCoursesDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";

    public void addOneRow(int studentId, int courseId) {
        Set<StudentCourses> studentCourses = new HashSet<>();
        studentCourses.add(new StudentCourses(studentId, courseId));
        addRows(studentCourses);
    }

    public void addRows(Set<StudentCourses> studentCourses) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            for (StudentCourses studentCourse : studentCourses) {
                int studentId = studentCourse.getStudentId();
                int courseId = studentCourse.getCourseId();
                String sql = "INSERT INTO student_courses (student_id, course_id) VALUES ("
                        + studentId + ", " + courseId + ")";
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.addRows failed...");
            System.out.println(ex);
        }
    }

    public Set<Student> showStudentsByCourseName(String courseName) {
        Set<Student> students = new HashSet<>();
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT first_name, last_name");
        sql.add("FROM student_courses");
        sql.add("INNER JOIN students ON (student_courses.student_id = students.student_id)");
        sql.add("INNER JOIN courses ON (student_courses.course_id = courses.course_id)");
        sql.add("WHERE course_name = '" + courseName + "'");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql.toString())) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                students.add(new Student(firstName, lastName));
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.showStudentsByCourseName failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Set<Course> showCoursesByStudentId(int studentId) {
        Set<Course> courses = new HashSet<>();
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT course_name, course_description, courses.course_id");
        sql.add("FROM student_courses");
        sql.add("INNER JOIN courses ON (student_courses.course_id = courses.course_id)");
        sql.add("WHERE student_id = " + studentId);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql.toString())) {

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("course_description");
                int courseId = rs.getInt("course_id");
                courses.add(new Course(courseName, courseDescription, courseId));
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.showCoursesByStudentId failed...");
            System.out.println(ex);
        }
        return courses;
    }

    public void deleteRow(int studentId, int courseId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM student_courses WHERE student_id = " + studentId
                    + " AND course_id = " + courseId;
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.deleteOneRow failed...");
            System.out.println(ex);
        }
    }
}
