package ua.com.foxminded.malzam.university.model;

import java.util.Objects;

public class Group {
    private final String groupName;
    
    public Group(String groupName) {
        this.groupName = groupName;        
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName);
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
        return Objects.equals(groupName, other.groupName);
    }

    @Override
    public String toString() {
        return "Group [groupName=" + groupName + "]";
    }
}
