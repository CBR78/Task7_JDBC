package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Student;

public class StudentDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";

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
            System.out.println("Writing students to the table failed...");
            System.out.println(ex);
        }
    }

    public Set<Student> showAllRows() {
        Set<Student> students = new HashSet<>();
        String sql = "SELECT first_name, last_name, group_id FROM students";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                students.add(new Student(firstName, lastName));
            }

        } catch (Exception ex) {
            System.out.println("Showing all Student failed...");
            System.out.println(ex);
        }
        return students;
    }

    public Student showRow(int studentId) {
        Student student = new Student("", "");
        String sql = "SELECT first_name, last_name, group_id FROM students WHERE student_id = "
                + studentId + " FETCH";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                student = new Student(firstName, lastName);
            }

        } catch (Exception ex) {
            System.out.println("Showing all Student failed...");
            System.out.println(ex);
        }
        return student;
    }

    public void removeStudent(int studentId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM students WHERE student_id = " + studentId;
            statement.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Removing student failed...");
            System.out.println(ex);
        }
    }
}
