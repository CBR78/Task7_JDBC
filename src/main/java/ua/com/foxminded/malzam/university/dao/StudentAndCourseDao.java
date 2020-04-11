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
import ua.com.foxminded.malzam.university.model.StudentAndCourse;

public class StudentAndCourseDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";

    public void addRows(Set<StudentAndCourse> studentsAndCourses) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            for (StudentAndCourse studentAndCourse : studentsAndCourses) {
                int studentId = studentAndCourse.getStudentId();
                int courseId = studentAndCourse.getCourseId();
                String sql = "INSERT INTO students_and_courses (student_id, course_id) VALUES ("
                        + studentId + ", " + courseId + ")";
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("Writing StudentsAndCourses to the table failed...");
            System.out.println(ex);
        }
    }

    public Set<Student> showCourseStudents(String courseName) {
        Set<Student> students = new HashSet<>();
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT first_name, last_name, course_name, course_description");
        sql.add("FROM students_and_courses");
        sql.add("INNER JOIN students ON (students_and_courses.student_id = students.student_id)");
        sql.add("INNER JOIN courses ON (students_and_courses.course_id = courses.course_id)");
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
            System.out.println("Group Search failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Set<Course> showStudentCourses(int studentId) {
        Set<Course> courses = new HashSet<>();
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT first_name, last_name, course_name, course_description");
        sql.add("FROM students_and_courses");
        sql.add("INNER JOIN students ON (students_and_courses.student_id = students.student_id)");
        sql.add("INNER JOIN courses ON (students_and_courses.course_id = courses.course_id)");
        sql.add("WHERE student_id = '" + studentId + "'");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql.toString())) {

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("course_description");
                courses.add(new Course(courseName, courseDescription));
            }
        } catch (Exception ex) {
            System.out.println("Group Search failed...");
            System.out.println(ex);
        }
        return courses;
    }

    public void removestudentAndCourse(int studentId, int course_id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM students_and_courses WHERE student_id = " + studentId
                    + " course_id = " + course_id;
            statement.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Removing student failed...");
            System.out.println(ex);
        }
    }
}
