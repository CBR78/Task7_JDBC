package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Student;

public class StudentDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";
    private static final String SQL_SHOW_ROWS_ALL = "SELECT first_name, last_name, student_id FROM students";
    private static final String SQL_SHOW_ROW_BU_ID = "SELECT first_name, last_name, student_id FROM students WHERE student_id = ? LIMIT 1";
    private static final String SQL_DELETE_ROW = "DELETE FROM students WHERE student_id = ?";

    public void addOneRow(String firstName, String lastName) {
        Set<Student> student = new HashSet<>();
        student.add(new Student(firstName, lastName));
        addRows(student);
    }

    public void addRows(Set<Student> students) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            String sql;
            for (Student student : students) {
                String firstName = student.getFirstName();
                String lastName = student.getLastName();
                int groupId = student.getGroupId();
                if (groupId > 0) {
                    sql = "INSERT INTO students (first_name, last_name, group_id) VALUES ('" + firstName
                            + "', '" + lastName + "', " + groupId + ")";
                } else {
                    sql = "INSERT INTO students (first_name, last_name) VALUES ('" + firstName + "', '"
                            + lastName + "')";
                }
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("StudentDao.addRows failed...");
            System.out.println(ex);
        }
    }

    public Set<Student> showRowsAll() {
        Set<Student> students = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL_SHOW_ROWS_ALL)) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int studentId = rs.getInt("student_id");
                students.add(new Student(firstName, lastName, studentId));
            }
        } catch (Exception ex) {
            System.out.println("StudentDao.showAllRows failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Student showRow(int studentId) {
        Student student = new Student("", "");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SHOW_ROW_BU_ID)) {

            preparedStatement.setInt(1, studentId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    student = new Student(firstName, lastName, studentId);
                }
            }
        } catch (Exception ex) {
            System.out.println("StudentDao.showOneRow failed...");
            System.out.println(ex);
        }
        return student;
    }

    public void deleteRow(int studentId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ROW)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();
        } catch (Exception ex) {
            System.out.println("StudentDao.deleteOneRow failed...");
            System.out.println(ex);
        }
    }
}
