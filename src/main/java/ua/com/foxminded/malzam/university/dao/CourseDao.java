package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Course;

public class CourseDao implements BaseDao<Course> {
    private static final String SQL_INSERT = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
    private static final String SQL_FIND_ALL = "SELECT course_id, course_name, course_description FROM courses";
    private static final String SQL_FIND_BY_STUDENT_ID = "SELECT course_name, course_description, courses.course_id FROM student_courses INNER JOIN courses ON (student_courses.course_id = courses.course_id) WHERE student_id = ?";

    public void insert(Course course) {
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_INSERT)) {
            preStatement.setString(1, course.getName());
            preStatement.setString(2, course.getDescription());
            preStatement.execute();
        } catch (Exception ex) {
            System.out.println("CourseDao.insert failed...");
            System.out.println(ex);
        }
    }

    public Set<Course> findAll() {
        Set<Course> courses = new HashSet<>();

        try (Connection connection = DBConnection.getInstance();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String courseDescription = rs.getString("course_description");
                courses.add(new Course(courseId, courseName, courseDescription));
            }
        } catch (Exception ex) {
            System.out.println("CourseDao.findAll failed...");
            System.out.println(ex);
        }
        return courses;
    }

    public Set<Course> findByStudentId(int studentId) {
        Set<Course> courses = new HashSet<>();

        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_FIND_BY_STUDENT_ID)) {
            preStatement.setInt(1, studentId);
            try (ResultSet rs = preStatement.executeQuery()) {
                while (rs.next()) {
                    String courseName = rs.getString("course_name");
                    String courseDescription = rs.getString("course_description");
                    int courseId = rs.getInt("course_id");
                    courses.add(new Course(courseId, courseName, courseDescription));
                }
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.findCoursesByStudentId failed...");
            System.out.println(ex);
        }
        return courses;
    }
}
