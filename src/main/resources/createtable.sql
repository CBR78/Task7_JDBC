DROP TABLE IF EXISTS groups, students, courses, student_courses CASCADE;
CREATE TABLE groups ( group_id serial PRIMARY KEY, group_name text);
CREATE TABLE students ( student_id serial PRIMARY KEY, first_name text, last_name text, group_id integer REFERENCES groups(group_id));
CREATE TABLE courses ( course_id  serial PRIMARY KEY, course_name  text, course_description text);
CREATE TABLE student_courses ( student_courses_id serial PRIMARY KEY, student_id   integer REFERENCES students(student_id) ON DELETE CASCADE, course_id integer REFERENCES courses(course_id) ON DELETE CASCADE);
