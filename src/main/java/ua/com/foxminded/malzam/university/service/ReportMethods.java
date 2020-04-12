package ua.com.foxminded.malzam.university.service;

import java.util.Set;

import ua.com.foxminded.malzam.university.dao.CourseDao;
import ua.com.foxminded.malzam.university.dao.GroupDao;
import ua.com.foxminded.malzam.university.dao.StudentCoursesDao;
import ua.com.foxminded.malzam.university.dao.StudentDao;
import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Group;
import ua.com.foxminded.malzam.university.model.Student;

public class ReportMethods {
    private static final String ID = " Id = ";

    public void showGroupsByStudentCount(int studentCount) {
        System.out.println("Groups of " + studentCount + " or fewer students:");
        Set<Group> groups = new GroupDao().showGroupsByStudentCount(studentCount);
        for (Group group : groups) {
            System.out.println(" " + group.getGroupName());
        }
    }

    public void showCoursesAll() {
        System.out.println("\n" + "List of Course Names:");
        Set<Course> courses = new CourseDao().showRowsAll();
        for (Course course : courses) {
            System.out.println(" " + course.getCourseName());
        }
    }

    public void showCoursesAllFromId() {
        System.out.println("\n" + "List of Course Names:");
        Set<Course> courses = new CourseDao().showRowsAll();
        for (Course course : courses) {
            System.out.println(" " + course.getCourseName() + ID + course.getCourseId());
        }
    }

    public void addStudent(String firstName, String lastName) {
        new StudentDao().addOneRow(firstName, lastName);
        System.out.println(" " + "Student added.");
    }

    public void deleteStudent(int studentId) {
        new StudentDao().deleteRow(studentId);
        System.out.println(" " + "Student deleted");
    }

    public void showStudentById(int studentId) {
        Student student = new StudentDao().showRow(studentId);
        System.out.println(" " + student.getFirstName() + " " + student.getLastName() + ID
                + student.getStudentId());
    }

    public void showStudentsAll() {
        System.out.println("\n" + "List of students and id:");
        Set<Student> students = new StudentDao().showRowsAll();
        for (Student student : students) {
            System.out.println(" " + student.getFirstName() + " " + student.getLastName() + ID
                    + student.getStudentId());
        }
    }

    public void showStudentsByCourseName(String courseName) {
        Set<Student> students = new StudentCoursesDao().showStudentsByCourseName(courseName);
        for (Student student : students) {
            System.out.println(" " + student.getFirstName() + " " + student.getLastName());
        }
    }

    public void addStudentCourse(int studentId, int courseId) {
        new StudentCoursesDao().addOneRow(studentId, courseId);
        System.out.println(" " + "Student added to course");
    }

    public void deleteStudentCourse(int studentId, int courseId) {
        new StudentCoursesDao().deleteRow(studentId, courseId);
        System.out.println(" " + "Student removed from course");
    }

    public void showStudentCourses(int studentId) {
        Set<Course> courses = new StudentCoursesDao().showCoursesByStudentId(studentId);
        System.out.println("\n" + "Student:");
        showStudentById(studentId);
        System.out.println("Student courses:");
        for (Course course : courses) {
            System.out.println(" " + course.getCourseName() + ID + course.getCourseId());
        }
    }
}
