package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Student;

public class StudentDao implements BaseDao<Student> {
    private static final String SQL_INSERT = "INSERT INTO students (first_name, last_name, group_id) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM students WHERE student_id = ?";
    private static final String SQL_INSERT_TO_COURSE = "INSERT INTO student_courses (student_id, course_id) VALUES (?, ?)";
    private static final String SQL_DELETE_FROM_COURSE = "DELETE FROM student_courses WHERE student_id = ? AND course_id = ?";
    private static final String SQL_FIND_ALL = "SELECT student_id, first_name, last_name, group_id FROM students";
    private static final String SQL_FIND_BY_COURSE_NAME = "SELECT student_id, first_name, last_name, group_id FROM student_courses INNER JOIN students ON (student_courses.student_id = students.student_id) INNER JOIN courses ON (student_courses.course_id = courses.course_id) WHERE course_name = ?";
    private static final String SQL_FIND_BY_ID = "SELECT student_id, first_name, last_name, group_id FROM students WHERE student_id = ? LIMIT 1";

    public void insert(Student student) {
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_INSERT)) {
            preStatement.setString(1, student.getFirstName());
            preStatement.setString(2, student.getLastName());
            preStatement.setInt(3, student.getGroupId());
            preStatement.execute();
        } catch (Exception ex) {
            System.out.println("StudentDao.insert failed...");
            System.out.println(ex);
        }
    }

    public void delete(int studentId) {
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_DELETE)) {
            preStatement.setInt(1, studentId);
            preStatement.execute();
        } catch (Exception ex) {
            System.out.println("StudentDao.delete failed...");
            System.out.println(ex);
        }
    }

    public void insertToCourse(int studentId, int courseId) {
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_INSERT_TO_COURSE)) {
            preStatement.setInt(1, studentId);
            preStatement.setInt(2, courseId);
            preStatement.execute();
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.insert failed...");
            System.out.println(ex);
        }
    }

    public void deleteFromCourse(int studentId, int courseId) {
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_DELETE_FROM_COURSE)) {
            preStatement.setInt(1, studentId);
            preStatement.setInt(2, courseId);
            preStatement.execute();
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.delete failed...");
            System.out.println(ex);
        }
    }

    public Set<Student> findAll() {
        Set<Student> students = new HashSet<>();
        
        try (Connection connection = DBConnection.getInstance();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL_FIND_ALL)) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int groupId = rs.getInt("group_id");
                students.add(new Student(studentId, firstName, lastName, groupId));
            }
        } catch (Exception ex) {
            System.out.println("StudentDao.findAll failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Set<Student> findByCourseName(String courseName) {
        Set<Student> students = new HashSet<>();
        
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_FIND_BY_COURSE_NAME)) {
            preStatement.setString(1, courseName);
            try (ResultSet rs = preStatement.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int groupId = rs.getInt("group_id");
                    students.add(new Student(studentId, firstName, lastName, groupId));
                }
            }
        } catch (Exception ex) {
            System.out.println("StudentCoursesDao.findStudentsByCourseName failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Student findById(int studentId) {
        Student student = null;
        
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            preStatement.setInt(1, studentId);
            try (ResultSet rs = preStatement.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int groupId = rs.getInt("group_id");
                    student = new Student(studentId, firstName, lastName, groupId);
                }
            }
        } catch (Exception ex) {
            System.out.println("StudentDao.findById failed...");
            System.out.println(ex);
        }
        return student;
    }
}
