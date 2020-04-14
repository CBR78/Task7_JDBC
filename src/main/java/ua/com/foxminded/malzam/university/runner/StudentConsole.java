package ua.com.foxminded.malzam.university.runner;

import java.util.Scanner;

import ua.com.foxminded.malzam.university.service.ReportFormatter;

public class StudentConsole {
    ReportFormatter reportFormatter = new ReportFormatter();

    public void menuStudentsByCourseName(Scanner in) {
        reportFormatter.printCoursesAll();
        System.out.println("\n" + "Enter course name:");
        String courseName = in.next();
        reportFormatter.printStudentsByCourseName(courseName);
    }

    public void menuAddStudent(Scanner in) {
        System.out.println("Enter student firstname:");
        String firstName = in.next();
        System.out.println("Enter student lastname:");
        String lastName = in.next();
        reportFormatter.printAddStudent(firstName, lastName);
    }

    public void menuDeleteStudent(Scanner in) {
        reportFormatter.printStudentsAll();
        System.out.println("\n" + "Enter STUDENT_ID to delete student:");
        int studentId = in.nextInt();
        reportFormatter.printDeleteStudent(studentId);
    }

    public void menuAddStudentToCourse(Scanner in) {
        reportFormatter.printStudentsAll();
        System.out.println("\n" + "Enter STUDENT_ID to add the student to a course:");
        int studentId = in.nextInt();
        reportFormatter.printStudentAndHisCourses(studentId);
        
        reportFormatter.printCoursesAll();
        System.out.println("\n" + "Enter COURSE_ID to add a student to it:");
        int courseId = in.nextInt();
        reportFormatter.printAddStudentToCourse(studentId, courseId);
        
        reportFormatter.printStudentAndHisCourses(studentId);
    }

    public void menuDeleteStudentCourse(Scanner in) {
        reportFormatter.printStudentsAll();
        System.out.println("\n" + "Enter STUDENT_ID to remove the student from a course:");
        int studentId = in.nextInt();

        reportFormatter.printStudentAndHisCourses(studentId);
        System.out.println("\n" + "Enter COURSE_ID to remove a student from the course:");
        int courseId = in.nextInt();
        reportFormatter.printDeleteStudentCourse(studentId, courseId);
        
        reportFormatter.printStudentAndHisCourses(studentId);
    }
}
