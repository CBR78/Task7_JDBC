package ua.com.foxminded.malzam.university.service;

import java.util.Set;

import ua.com.foxminded.malzam.university.dao.CourseDao;
import ua.com.foxminded.malzam.university.dao.GroupDao;
import ua.com.foxminded.malzam.university.dao.StudentDao;
import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Group;
import ua.com.foxminded.malzam.university.model.Student;

public class ReportFormatter {
    private static final String ID = " Id = ";

    public void printStudentsByCourseName(String courseName) {
        Set<Student> students = new StudentDao().findByCourseName(courseName);
        printStudentsSet(students);
    }

    public void printAddStudent(String firstName, String lastName) {
        Student student = new Student(firstName, lastName);
        new StudentDao().insert(student);
        System.out.println(" " + "Student added.");
    }

    public void printDeleteStudent(int studentId) {
        new StudentDao().delete(studentId);
        System.out.println(" " + "Student deleted");
    }

    public void printAddStudentToCourse(int studentId, int courseId) {
        new StudentDao().insertToCourse(studentId, courseId);
        System.out.println(" " + "Student added to course");
    }

    public void printDeleteStudentCourse(int studentId, int courseId) {
        new StudentDao().deleteFromCourse(studentId, courseId);
        System.out.println(" " + "Student removed from course");
    }

    public void printGroupsByStudentCount(int studentCount) {
        Set<Group> groups = new GroupDao().findByStudentCount(studentCount);

        System.out.println("Groups of " + studentCount + " or fewer students:");
        for (Group group : groups) {
            System.out.println(" " + group.getName() + " Id = " + group.getId());
        }
    }

    public void printStudentsAll() {
        System.out.println("\n" + "List of students:");
        Set<Student> students = new StudentDao().findAll();
        printStudentsSet(students);
    }

    public void printCoursesAll() {
        System.out.println("\n" + "List of courses:");
        Set<Course> courses = new CourseDao().findAll();
        printCourse(courses);
    }

    public void printStudentAndHisCourses(int studentId) {
        System.out.println("\n" + "Student:");
        Student student = new StudentDao().findById(studentId);
        printStudent(student);

        System.out.println("Student courses:");
        Set<Course> courses = new CourseDao().findByStudentId(studentId);
        printCourse(courses);
    }

    public void printStudentsSet(Set<Student> students) {
        for (Student student : students) {
            printStudent(student);
        }
    }

    private void printStudent(Student student) {
        System.out.println(
                " " + student.getFirstName() + " " + student.getLastName() + ID + student.getId());
    }

    private void printCourse(Set<Course> courses) {
        for (Course course : courses) {
            System.out.println(" " + course.getName() + ID + course.getId());
        }
    }
}
