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
     * Adds the student to the student table in the database. Returns true if successful, false if not
     *
     * @throws IllegalStateException if students table doesn't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    public boolean addStudent(String name, String password);


    /**
     *
     * Returns a boolean based on if the student currently has a username and password in the database.
     *
     * @throws IllegalStateException if not yet connected
     * @throws IllegalStateException if the students table does not exist
     *
     */
    public boolean checkIfLoginExists(String username, String password);

    /**
     * Adds all courses in the list to the courses section of the database
     *
     * @throws IllegalStateException if courses table doesn't exist
     * @throws IllegalArgumentException if adding a course that already exists (i.e., has
     * a matching name/course ID).
     * @throws IllegalStateException if the Manager hasn't connected yet
     */


    /**
     * Checks if a course with the given info exists.
     *
     *
     * @throws IllegalStateException if courses table doesn't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    public boolean doesCourseExist(String department, int catalog_number);

    void addCourses(List<Course> courseList);

    /**
     * Adds the info from the given review object into the database.
     *
     *
     * @throws IllegalStateException if reviews table doesn't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     * @throws IllegalArgumentException if the review data is not correct
     */
    public void addReview(Review review);
    /**
     * Return all the reviews for the given course department and catalog number. The ReviewMessage objects contain
     * the message of the review and the score given.
     *
     * Returns an empty list if the course has no reviews.
     *
     * @throws IllegalStateException if students, courses, or reviews table doesn't exist
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    List<ReviewMessage> getCourseReviews(String department, int catalog_number);

    /**
     * Commits any changes and ends the connection.
     *
     * @throws IllegalStateException if the Manager hasn't connected yet
     */
    public void disconnect();
}
