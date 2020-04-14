package ua.com.foxminded.malzam.university.service;

import java.util.Set;

import ua.com.foxminded.malzam.university.dao.CourseDao;
import ua.com.foxminded.malzam.university.dao.GroupDao;
import ua.com.foxminded.malzam.university.dao.StudentDao;
import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Group;
import ua.com.foxminded.malzam.university.model.Student;
import ua.com.foxminded.malzam.university.model.StudentCourses;

public class DataTableLoader {
    public void loadGeneratedData() {
        DataTableGeneator dataTableGeneator = new DataTableGeneator();

        Set<Student> students = dataTableGeneator.generateStudents();
        for (Student student : students) {
            new StudentDao().insert(student);
        }

        Set<Group> groups = dataTableGeneator.generateGroups();
        for (Group group : groups) {
            new GroupDao().insert(group);
        }

        Set<Course> courses = dataTableGeneator.createCourses();
        for (Course course : courses) {
            new CourseDao().insert(course);
        }

        Set<StudentCourses> studentCourses = dataTableGeneator.generateStudentsAndCourses();
        for (StudentCourses studentCourse : studentCourses) {
            int studentId = studentCourse.getStudentId(); 
            int courseId = studentCourse.getCourseId();
            new StudentDao().insertToCourse(studentId, courseId);
        }
    }
}
