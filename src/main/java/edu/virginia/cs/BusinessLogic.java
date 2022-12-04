package edu.virginia.cs;

import java.util.*;

public class BusinessLogic {
    private DatabaseManagerImpl databaseManager;
    private ReviewService reviewService;
    private Map<String, Course> courseMap;
    private Map<String, Student> userMap;

    public BusinessLogic(DatabaseManagerImpl dbManager){
        databaseManager = dbManager;
    }

    public void addReviewForCourse(Review review) {
        if(!databaseManager.doesCourseExist(review.getCourse().getDepartment(), review.getCourse().getCatalogNumber())) {
            databaseManager.addCourse(review.getCourse());
        }
        databaseManager.addReview(review);
    }

    public List<ReviewMessage> getReviewsForCourse(Course course) {
        String courseDepartment = course.getDepartment();
        int courseCatalog_Number = course.getCatalogNumber();
        List<ReviewMessage> allReviews = databaseManager.getCourseReviews(courseDepartment, courseCatalog_Number);
        return allReviews;
    }

    public boolean isExistingUser(String username, String password) {
        return databaseManager.checkIfLoginExists(username, password);
    }

    public boolean isExistingCourse(Course courseName) {
        return databaseManager.doesCourseExist(courseName.getDepartment(), courseName.getCatalogNumber());
    }

    public boolean courseHasReviews(Course courseName) {
        List<ReviewMessage> allReviewsForCourse = databaseManager.getCourseReviews(courseName.getDepartment(), courseName.getCatalogNumber());
        return (!allReviewsForCourse.isEmpty());
    }
}
