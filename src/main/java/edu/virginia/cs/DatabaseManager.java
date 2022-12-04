package edu.virginia.cs;

import java.util.List;

public interface DatabaseManager {

    /**
     * Establishes the database connection. Must be called before any other
     * methods are called. Creates database if it doesn't already exist.
     *
     * @throws IllegalStateException if the Manager is already connected
     */
    void connect();

    /**
     * Creates the students, courses, and reviews tables if not already created.
     *
     * @throws IllegalStateException if the tables already exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    void createTables();

    /**
     * Empties all of the tables, but does not delete the tables. I.e.,
     * the table structure is still there, but the data content is emptied.
     *
     * @throws IllegalStateException if the tables don't exist.
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    void clear();

    /**
     * Deletes the tables students, courses, and reviews from the database. This
     * removes both the data and the tables themselves.
     *
     * @throws IllegalStateException if the tables don't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    void deleteTables();

    /**
     * Adds the student list to the student table in the database.
     *
     * @throws IllegalStateException if students table doesn't exist
     * @throws IllegalArgumentException if you add a student that is already
     * in the database.
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    void addStudents(List<Student> studentList);

    /**
     * Return a list of all the students in the database
     *
     * Returns an empty list if the students table is empty.
     *
     * @throws IllegalStateException if students table doesn't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    List<Student> getAllStudents();

    /**
     * Get a specific student by their name/computing ID;
     *
     * @throws IllegalStateException if students table doesn't exist
     * @throws IllegalArgumentException if no student with given id name
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    Student getStudentByName(String name);

    /**
     * Adds all courses in the list to the courses section of the database
     *
     * @throws IllegalStateException if courses table doesn't exist
     * @throws IllegalArgumentException if adding a course that already exists (i.e., has
     * a matching name/course ID).
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    void addCourses(List<Course> courseList);


    /**
     * Returns the course object when given its name.
     *
     *
     * @throws IllegalStateException if students, courses, or reviews table doesn't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     * @throws IllegalArgumentException if the Course doesn't exist
     */
    Course getCourseByName();
    /**
     * Commits any changes and ends the connection.
     *
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    public void disconnect();
}
