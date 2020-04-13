package ua.com.foxminded.malzam.university.service;

import java.util.Set;

import ua.com.foxminded.malzam.university.dao.CourseDao;
import ua.com.foxminded.malzam.university.dao.GroupDao;
import ua.com.foxminded.malzam.university.dao.StudentCoursesDao;
import ua.com.foxminded.malzam.university.dao.StudentDao;
import ua.com.foxminded.malzam.university.model.Course;
import ua.com.foxminded.malzam.university.model.Group;
import ua.com.foxminded.malzam.university.model.Student;
import ua.com.foxminded.malzam.university.model.StudentCourses;

public class DataTableLoader {
    public void loadGeneratedData() {
        DataTableGeneator dataTableGeneator = new DataTableGeneator();
        
        Set<Course> courses = dataTableGeneator.createCourses();
        Set<Group> groups = dataTableGeneator.generateGroups();
        Set<Student> students = dataTableGeneator.generateStudents();
        Set<StudentCourses> studentsAndCourses = dataTableGeneator.generateStudentsAndCourses();

        new CourseDao().addRows(courses);
        new GroupDao().addRows(groups);
        new StudentDao().addRows(students);
        new StudentCoursesDao().addRows(studentsAndCourses);
    }
}
