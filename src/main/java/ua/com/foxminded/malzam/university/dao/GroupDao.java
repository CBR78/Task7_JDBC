package ua.com.foxminded.malzam.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Group;

public class GroupDao implements BaseDao<Group> {
    private static final String SQL_INSERT = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String SQL_FIND_ALL = "SELECT groups.group_id, group_name FROM groups";
    private static final String SQL_FIND_BY_STUDENT_COUNT = "SELECT groups.group_id, group_name FROM students INNER JOIN groups ON (students.group_id = groups.group_id) GROUP BY group_name HAVING count(students.group_id) < ?";

    public void insert(Group group) {
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection.prepareStatement(SQL_INSERT)) {
            preStatement.setString(1, group.getName());
            preStatement.execute();
        } catch (Exception ex) {
            System.out.println("GroupDao.insert failed...");
            System.out.println(ex);
        }
    }

    public Set<Group> findAll() {
        Set<Group> group = new HashSet<>();
        
        try (Connection connection = DBConnection.getInstance();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                int groupId = rs.getInt("group_id");
                String groupName = rs.getString("group_name");
                group.add(new Group(groupId, groupName));
            }
        } catch (Exception ex) {
            System.out.println("GroupDao.findAll failed...");
            System.out.println(ex);
        }
        return group;
    }

    public Set<Group> findByStudentCount(int sumStudents) {
        Set<Group> groups = new HashSet<>();
        
        try (Connection connection = DBConnection.getInstance();
                PreparedStatement preStatement = connection
                        .prepareStatement(SQL_FIND_BY_STUDENT_COUNT)) {
            preStatement.setInt(1, sumStudents);
            try (ResultSet rs = preStatement.executeQuery()) {
                while (rs.next()) {
                    String groupName = rs.getString("group_name");
                    groups.add(new Group(groupName));
                }
            }
        } catch (Exception ex) {
            System.out.println("GroupDao.findByStudentCount failed...");
            System.out.println(ex);
        }
        return groups;
    }
}
