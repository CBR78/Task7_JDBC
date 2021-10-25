package ua.com.foxminded.malzam.university.runner;

import java.util.Scanner;

public class MainConsole {

    public void printMainMenu() {
        System.out.println("\n" + "Select function (a, b, c, d, e, f or x) and press Enter:");
        System.out.println("a. Find all groups with less or equals student count;");
        System.out.println("b. Find all students related to course with given name;");
        System.out.println("c. Add new student;");
        System.out.println("d. Delete student by STUDENT_ID;");
        System.out.println("e. Add a student to the course (from a list);");
        System.out.println("f. Remove the student from one of his or her courses;");
        System.out.println("x. Close the program.");

        Scanner in = new Scanner(System.in);
        String selectedFunction = in.next();
        if (!selectedFunction.equals("x")) {
            printFunctionMenu(in, selectedFunction);
        }
        in.close();
    }

    private void printFunctionMenu(Scanner in, String selectedFunction) {
        GroupConsole groupConsole = new GroupConsole();
        StudentConsole studentConsole = new StudentConsole();

        switch (selectedFunction) {
            case "a":
                groupConsole.menuGroupsByStudentCount(in);
                break;
            case "b":
                studentConsole.menuStudentsByCourseName(in);
                break;
            case "c":
                studentConsole.menuAddStudent(in);
                break;
            case "d":
                studentConsole.menuDeleteStudent(in);
                break;
            case "e":
                studentConsole.menuAddStudentToCourse(in);
                break;
            case "f":
                studentConsole.menuDeleteStudentCourse(in);
                break;
            default:
                System.out.println("Invalid value entered");
                break;
        }
        printMainMenu();
    }
}
