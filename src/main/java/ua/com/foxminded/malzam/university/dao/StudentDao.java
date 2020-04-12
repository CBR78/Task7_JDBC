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
        String sql = "SELECT first_name, last_name, student_id FROM students";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

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
        String sql = "SELECT first_name, last_name, student_id FROM students WHERE student_id = "
                + studentId + " LIMIT 1";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                student = new Student(firstName, lastName, studentId);
            }

        } catch (Exception ex) {
            System.out.println("StudentDao.showOneRow failed...");
            System.out.println(ex);
        }
        return student;
    }

    public void deleteRow(int studentId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM students WHERE student_id = " + studentId;
            statement.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("StudentDao.deleteOneRow failed...");
            System.out.println(ex);
        }
    }
}
