package ua.com.foxminded.malzam.university.runner;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import ua.com.foxminded.malzam.university.dao.CourseDao;
import ua.com.foxminded.malzam.university.dao.GroupDao;
import ua.com.foxminded.malzam.university.dao.StudentAndCourseDao;
import ua.com.foxminded.malzam.university.dao.StudentDao;
import ua.com.foxminded.malzam.university.dao.TableReCreatorDao;
import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Group;
import ua.com.foxminded.malzam.university.model.Student;
import ua.com.foxminded.malzam.university.model.StudentAndCourse;
import ua.com.foxminded.malzam.university.service.DataTableGeneator;

public class Runner {

    public static void main(String[] args) {
        TableReCreatorDao tableManager = new TableReCreatorDao();
        DataTableGeneator dataTableGeneator = new DataTableGeneator();
        Scanner in = new Scanner(System.in);

        tableManager.reCreateTable();
        dataTableGeneator.generateData();

        System.out.println("a. Find all groups with less or equals student count");
        System.out.println("b. Find all students related to course with given name");
        System.out.println("c. Add new student");
        System.out.println("d. Delete student by STUDENT_ID");
        System.out.println("e. Add a student to the course (from a list)");
        System.out.println("f. Remove the student from one of his or her courses");
        System.out.println("Select function (a, b, c, d, e, f): ");

        String selectedFunction = in.next();

        if (selectedFunction.equals("a")) {
            System.out.println("Enter the count of students");
            int studentCount = in.nextInt();

            GroupDao groupDao = new GroupDao();
            Set<Group> groups = groupDao.searchGroups(studentCount);
            for (Group group : groups) {
                System.out.println(group.toString());
            }
        }

        if (selectedFunction.equals("b")) {
            System.out.println("Enter course name");
            String courseName = in.next();

            StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
            Set<Student> students = studentAndCourseDao.showCourseStudents(courseName);
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }

        if (selectedFunction.equals("c")) {
            System.out.println("Enter student firstname");
            String firstName = in.next();
            System.out.println("Enter student lastname");
            String lastName = in.next();

            Set<Student> students = new HashSet<>();
            students.add(new Student(firstName, lastName));
            StudentDao studentDao = new StudentDao();
            studentDao.addRows(students);

            System.out.println("Student added");
        }

        if (selectedFunction.equals("d")) {
            System.out.println("Enter STUDENT_ID to remove student");
            int studentId = in.nextInt();

            StudentDao studentDao = new StudentDao();
            studentDao.removeStudent(studentId);

            System.out.println("Student removed");
        }

        if (selectedFunction.equals("e")) {
            StudentDao studentDao = new StudentDao();
            Set<Student> students = studentDao.showAllRows();
            for (Student student : students) {
                System.out.println(student.toString());
            }

            System.out.println("Enter STUDENT_ID to add the student to a course");
            int studentId = in.nextInt();

            CourseDao courseDao = new CourseDao();
            Set<Course> courses = courseDao.showAllRows();
            for (Course course : courses) {
                System.out.println(course.toString());
            }
            System.out.println("Enter COURSE_ID to add a student to it");
            int courseId = in.nextInt();

            Set<StudentAndCourse> studentsAndCourses = new HashSet<>();
            studentsAndCourses.add(new StudentAndCourse(studentId, courseId));
            StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
            studentAndCourseDao.addRows(studentsAndCourses);

            System.out.println("Student removed");
        }

        if (selectedFunction.equals("f")) {
            System.out.println("Enter STUDENT_ID to remove the student from a course");
            int studentId = in.nextInt();

            StudentDao studentDao = new StudentDao();
            Student student = studentDao.showRow(studentId);
            System.out.println(student.toString());

            StudentAndCourseDao studentAndCourseDao = new StudentAndCourseDao();
            Set<Course> courses = studentAndCourseDao.showStudentCourses(studentId);
            for (Course course : courses) {
                System.out.println(course.toString());
            }

            System.out.println("Enter COURCE_ID to remove a student from the course");
            int course_id = in.nextInt();
            studentAndCourseDao.removestudentAndCourse(studentId, course_id);
            System.out.println("Student removed from course");
        }
        in.close();
    }
}