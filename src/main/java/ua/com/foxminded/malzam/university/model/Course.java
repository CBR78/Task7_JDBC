package ua.com.foxminded.malzam.university.model;

import java.util.Objects;

public class Course {
    private final String courseName;
    private final String courseDescription;
    private int courseId;

    public Course(String courseName, String courseDescription) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
    }

    public Course(String courseName, String courseDescription, int courseId) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseDescription, courseName, courseId);
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
        return Objects.equals(courseDescription, other.courseDescription)
                && Objects.equals(courseName, other.courseName) && courseId == other.courseId;
    }

    @Override
    public String toString() {
        return "Course [courseName=" + courseName + ", courseDescription=" + courseDescription
                + ", courseId=" + courseId + "]";
    }
}
