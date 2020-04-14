package ua.com.foxminded.malzam.university.model;

import java.util.Objects;

public class Student {
    private int id;
    private final String firstName;
    private final String lastName;
    private int groupId;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(int studentId, String firstName, String lastName, int groupId) {
        this.id = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, groupId, lastName, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(firstName, other.firstName) && groupId == other.groupId
                && Objects.equals(lastName, other.lastName) && id == other.id;
    }

    @Override
    public String toString() {
        return "Student [firstName=" + firstName + ", lastName=" + lastName + ", studentId=" + id
                + ", groupId=" + groupId + "]";
    }
}
