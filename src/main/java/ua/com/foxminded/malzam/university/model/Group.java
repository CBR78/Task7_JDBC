package ua.com.foxminded.malzam.university.model;

import java.util.Objects;

public class Group {
    private int id;
    private final String name;

    public Group(String name) {
        this.name = name;
    }

    public Group(int groupId, String name) {
        this.id = groupId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Group [groupName=" + name + "]";
    }
}
