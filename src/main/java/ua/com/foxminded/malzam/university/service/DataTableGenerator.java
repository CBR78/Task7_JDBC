package ua.com.foxminded.malzam.university.service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Group;
import ua.com.foxminded.malzam.university.model.Student;
import ua.com.foxminded.malzam.university.model.StudentCourses;

public class DataTableGenerator {
    private static final int SUM_STUDENTS = 200;
    private static final int SUM_GROUPS = 10;
    private static final int SUM_COURSES = 10;
    private final Random random = new Random();

    public Set<Course> createCourses() {
        Set<Course> courses = new HashSet<>();
        courses.add(new Course("Mathematics", "Learning math"));
        courses.add(new Course("Materials Science", "Study of materials science"));
        courses.add(new Course("Metrology", "Study of metrology"));
        courses.add(new Course("Sopromat", "Sopromat study"));
        courses.add(new Course("English", "Learning English"));
        courses.add(new Course("Philosophy", "Study of psychology"));
        courses.add(new Course("Modeling", "Simulation study"));
        courses.add(new Course("Physics", "Physics study"));
        courses.add(new Course("Chemistry", "Chemistry study"));
        courses.add(new Course("Electrical Engineering", "Study of electrical engineering"));
        return courses;
    }

    public Set<Group> generateGroups() {
        final int ASCII_CODE_A = 65;
        final int ASCII_CODE_Z = 90;
        final int ASCII_TARGET_SET = ASCII_CODE_Z - ASCII_CODE_A + 1;
        Set<Group> groups = new HashSet<>();

        for (int i = 0; i < SUM_GROUPS; i++) {
            StringBuilder groupName = new StringBuilder();
            groupName.appendCodePoint(ASCII_CODE_A + this.random.nextInt(ASCII_TARGET_SET));
            groupName.appendCodePoint(ASCII_CODE_A + this.random.nextInt(ASCII_TARGET_SET));
            groupName.append("-");
            groupName.append(this.random.nextInt(10));
            groupName.append(this.random.nextInt(10));
            groups.add(new Group(groupName.toString()));
        }
        return groups;
    }

    public Set<Student> generateStudents() {
        final int SUM_FIRST_NAMES = 10;
        final int SUM_LAST_NAMES = 20;
        Set<Student> students = new HashSet<>();

        String[] firstNames = new String[SUM_FIRST_NAMES];
        firstNames[0] = "James";
        firstNames[1] = "John";
        firstNames[2] = "Robert";
        firstNames[3] = "Michael";
        firstNames[4] = "William";
        firstNames[5] = "Mary";
        firstNames[6] = "Patricia";
        firstNames[7] = "Linda";
        firstNames[8] = "Barbara";
        firstNames[9] = "Elizabeth";

        String[] lastNames = new String[SUM_LAST_NAMES];
        lastNames[0] = "Smith";
        lastNames[1] = "Johnson";
        lastNames[2] = "Williams";
        lastNames[3] = "Jones";
        lastNames[4] = "Brown";
        lastNames[5] = "Davis";
        lastNames[6] = "Miller";
        lastNames[7] = "Wilson";
        lastNames[8] = "Moore";
        lastNames[9] = "Taylor";
        lastNames[10] = "Anderson";
        lastNames[11] = "Thomas";
        lastNames[12] = "Jackson";
        lastNames[13] = "White";
        lastNames[14] = "Harris";
        lastNames[15] = "Martin";
        lastNames[16] = "Thompson";
        lastNames[17] = "Garcia";
        lastNames[18] = "Martinez";
        lastNames[19] = "Robinson";

        for (int i = 0; i < SUM_FIRST_NAMES; i++) {
            for (int j = 0; j < SUM_LAST_NAMES; j++) {
                students.add(new Student(firstNames[i], lastNames[j]));
            }
        }
        setGroupsForStudents(students);
        return students;
    }

    private void setGroupsForStudents(Set<Student> students) {
        int studentsWithoutGroups = SUM_STUDENTS;
        int groupId = 0;
        int studentsForGrouping = 0;

        for (Student student : students) {
            if (studentsWithoutGroups >= 10 && studentsForGrouping == 0) {
                studentsForGrouping = 10 + random.nextInt(21);
                groupId++;
            }

            if (groupId <= 10 && studentsForGrouping > 0) {
                student.setGroupId(groupId);
                studentsWithoutGroups--;
                studentsForGrouping--;
            }
        }
    }

    public Set<StudentCourses> generateStudentsAndCourses() {
        int sumCoursesForStudent;
        int courseId = 0;
        Set<StudentCourses> studentsAndCourses = new HashSet<>();

        for (int studentId = 1; studentId <= SUM_STUDENTS; studentId++) {
            sumCoursesForStudent = 1 + random.nextInt(3);
            for (; sumCoursesForStudent > 0; sumCoursesForStudent--) {
                courseId++;
                if (courseId > SUM_COURSES) {
                    courseId = 1;
                }
                studentsAndCourses.add(new StudentCourses(studentId, courseId));
            }
        }
        return studentsAndCourses;
    }
}
