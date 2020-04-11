package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Course;

public class CourseDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";

    public void addRows(Set<Course> courses) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            for (Course course : courses) {
                String courseName = course.getCourseName();
                String courseDescription = course.getCourseDescription();
                String sql = "INSERT INTO courses (course_name, course_description) VALUES ('"
                        + courseName + "', '" + courseDescription + "')";
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("Writing Сourses to the table failed...");
            System.out.println(ex);
        }
    }

    public Set<Course> showAllRows() {
        Set<Course> courses = new HashSet<>();
        String sql = "SELECT course_name, course_description FROM courses";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("course_description");
                courses.add(new Course(courseName, courseDescription));
            }

        } catch (Exception ex) {
            System.out.println("Showing all Сourses failed...");
            System.out.println(ex);
        }
        return courses;
    }
}
