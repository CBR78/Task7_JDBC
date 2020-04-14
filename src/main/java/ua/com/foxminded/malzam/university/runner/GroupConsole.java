package ua.com.foxminded.malzam.university.runner;

import java.util.Scanner;

import ua.com.foxminded.malzam.university.service.ReportFormatter;

public class GroupConsole {

    public void menuGroupsByStudentCount(Scanner in) {
        System.out.println("\n" + "Enter the count of students:");
        int studentCount = in.nextInt();
        new ReportFormatter().printGroupsByStudentCount(studentCount);
    }    
}
