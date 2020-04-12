package ua.com.foxminded.malzam.university.runner;

import java.util.Scanner;

import ua.com.foxminded.malzam.university.dao.TableReCreatorDao;
import ua.com.foxminded.malzam.university.service.DataTableGeneator;
import ua.com.foxminded.malzam.university.service.ReportMethods;

public class Runner {

    public static void main(String[] args) {
        new TableReCreatorDao().reCreateTable();
        new DataTableGeneator().generateData();
        Scanner in = new Scanner(System.in);
        showMainMenus(in);
        in.close();
    }

    private static void showMainMenus(Scanner in) {
        System.out.println("\n" + "Select function (a, b, c, d, e, f or x) and press Enter:");
        System.out.println("a. Find all groups with less or equals student count;");
        System.out.println("b. Find all students related to course with given name;");
        System.out.println("c. Add new student;");
        System.out.println("d. Delete student by STUDENT_ID;");
        System.out.println("e. Add a student to the course (from a list);");
        System.out.println("f. Remove the student from one of his or her courses;");
        System.out.println("x. Close the program.");
        String selectedFunction = in.next();
        if (!selectedFunction.equals("x")) {
            showFunctionMenu(in, selectedFunction);
        }
    }

    private static void showFunctionMenu(Scanner in, String selectedFunction) {
        ReportMethods reportMethods = new ReportMethods();

        if (selectedFunction.equals("a")) {
            System.out.println("\n" + "Enter the count of students:");
            int studentCount = in.nextInt();
            reportMethods.showGroupsByStudentCount(studentCount);
        }

        else if (selectedFunction.equals("b")) {
            reportMethods.showCoursesAll();
            System.out.println("\n" + "Enter course name:");
            // String courseNameLine = in.nextLine();
            // По неизвестной причине nextLine()не работает, по этому
            // курс "Electrical Engineering" ввести нельзя
            String courseName = in.next();
            reportMethods.showStudentsByCourseName(courseName);
        }

        else if (selectedFunction.equals("c")) {
            System.out.println("Enter student firstname:");
            String firstName = in.next();
            System.out.println("Enter student lastname:");
            String lastName = in.next();
            reportMethods.addStudent(firstName, lastName);
        }

        else if (selectedFunction.equals("d")) {
            reportMethods.showStudentsAll();
            System.out.println("\n" + "Enter STUDENT_ID to delete student:");
            int studentId = in.nextInt();
            reportMethods.deleteStudent(studentId);
        }

        else if (selectedFunction.equals("e")) {
            reportMethods.showStudentsAll();
            System.out.println("\n" + "Enter STUDENT_ID to add the student to a course:");
            int studentId = in.nextInt();
            reportMethods.showStudentCourses(studentId);
            reportMethods.showCoursesAllFromId();
            System.out.println("\n" + "Enter COURSE_ID to add a student to it:");
            int courseId = in.nextInt();
            reportMethods.addStudentCourse(studentId, courseId);
            reportMethods.showStudentCourses(studentId);
        }

        else if (selectedFunction.equals("f")) {
            reportMethods.showStudentsAll();
            System.out.println("\n" + "Enter STUDENT_ID to remove the student from a course:");
            int studentId = in.nextInt();
            reportMethods.showStudentCourses(studentId);
            System.out.println("\n" + "Enter COURSE_ID to remove a student from the course:");
            int courseId = in.nextInt();
            reportMethods.deleteStudentCourse(studentId, courseId);
            reportMethods.showStudentCourses(studentId);
        } else {
            System.out.println("Invalid value entered");
        }
        showMainMenus(in);
    }
}
