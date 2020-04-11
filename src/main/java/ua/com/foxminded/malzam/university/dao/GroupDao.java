package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import ua.com.foxminded.malzam.university.model.Group;

public class GroupDao {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
    private static final String DB_USER = "user_university";
    private static final String DB_PASSWORD = "1234";

    public void addRows(Set<Group> groups) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement()) {

            for (Group group : groups) {
                String groupName = group.getGroupName();
                String sql = "INSERT INTO groups (group_name) VALUES ('" + groupName + "')";
                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("Writing Groups to the table failed...");
            System.out.println(ex);
        }
    }

    public Set<Group> searchGroups(int sumStudents) {
        Set<Group> groups = new HashSet<>();
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT groups.group_name, count(students.group_id) AS sum_students");
        sql.add("FROM students INNER JOIN groups ON (students.group_id = groups.group_id)");
        sql.add("GROUP BY group_name");
        sql.add("HAVING count(students.group_id) < " + sumStudents);
        sql.add("ORDER BY sum_students");

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql.toString())) {

            while (rs.next()) {
                String groupName = rs.getString("group_name");
                groups.add(new Group(groupName));
            }
        } catch (Exception ex) {
            System.out.println("Group Search failed...");
            System.out.println(ex);
        }
        return groups;
    }
}
