package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Group;

public class GroupDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";
    private static final String SQL_ADD_ROWS = "INSERT INTO groups (group_name) VALUES ('?')";
    private static final String SQL_SHOW_GROUPS_BU_STUDENT_COUNT = "SELECT group_name FROM students INNER JOIN groups ON (students.group_id = groups.group_id) GROUP BY group_name HAVING count(students.group_id) < ?";

    public void addRows(Set<Group> groups) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ROWS)) {

            for (Group group : groups) {
                String groupName = group.getGroupName();
                preparedStatement.setString(1, groupName);
                preparedStatement.execute();
            }
        } catch (Exception ex) {
            System.out.println("GroupDao.addSetRows failed...");
            System.out.println(ex);
        }
    }

    public Set<Group> showGroupsByStudentCount(int sumStudents) { // - preparedStatement
        Set<Group> groups = new HashSet<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                PreparedStatement preparedStatement = connection
                        .prepareStatement(SQL_SHOW_GROUPS_BU_STUDENT_COUNT)) {

            preparedStatement.setInt(1, sumStudents);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String groupName = rs.getString("group_name");
                    groups.add(new Group(groupName));
                }
            }
        } catch (Exception ex) {
            System.out.println("GroupDao.findGroupsByStudentCount failed...");
            System.out.println(ex);
        }
        return groups;
    }
}
