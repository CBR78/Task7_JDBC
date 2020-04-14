package ua.com.foxminded.malzam.university.model;

import java.util.Objects;

public class Course {
    private int id;
    private final String name;
    private final String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        return Objects.equals(description, other.description)
                && Objects.equals(name, other.name) && id == other.id;
    }

    @Override
    public String toString() {
        return "Course [courseName=" + name + ", courseDescription=" + description
                + ", courseId=" + id + "]";
    }
}
