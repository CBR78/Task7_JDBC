package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Course;

public class CourseDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";
    private static final String SQL_ADD_ROWS = "INSERT INTO courses (course_name, course_description) VALUES ('?','?')";
    private static final String SQL_SHOW_ROWS_ALL = "SELECT course_name, course_description, course_id FROM courses";

    public void addRows(Set<Course> courses) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ROWS)) {

            for (Course course : courses) {
                String courseName = course.getCourseName();
                String courseDescription = course.getCourseDescription();
                preparedStatement.setString(1, courseName);
                preparedStatement.setString(2, courseDescription);
                preparedStatement.execute();
            }
        } catch (Exception ex) {
            System.out.println("CourseDao.addRows failed...");
            System.out.println(ex);
        }
    }

    public Set<Course> showRowsAll() {
        Set<Course> courses = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL_SHOW_ROWS_ALL)) {

            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("course_description");
                int courseId = rs.getInt("course_id");
                courses.add(new Course(courseName, courseDescription, courseId));
            }
        } catch (Exception ex) {
            System.out.println("CourseDao.showAllRows failed...");
            System.out.println(ex);
        }
        return courses;
    }
}
