package ua.com.foxminded.malzam.university.model;

import java.util.Objects;

public class StudentAndCourse {

    private final int studentId;
    private final int courseId;

    public StudentAndCourse(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentAndCourse other = (StudentAndCourse) obj;
        return courseId == other.courseId && studentId == other.studentId;
    }

    @Override
    public String toString() {
        return "StudentAndCours [studentId=" + studentId + ", courseId=" + courseId + "]";
    }
}
