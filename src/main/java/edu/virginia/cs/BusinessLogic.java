package edu.virginia.cs;

import java.util.*;

public class BusinessLogic {
    private DatabaseManagerImpl databaseManager;

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
        return databaseManager.getCourseReviews(courseDepartment, courseCatalog_Number);
    }

    public boolean isExistingUser(String username) {
        return databaseManager.checkIfLoginExistsByName(username);
    }

    public boolean isExistingCourse(Course courseName) {
        return databaseManager.doesCourseExist(courseName.getDepartment(), courseName.getCatalogNumber());
    }

    public boolean courseHasReviews(Course courseName) {
        List<ReviewMessage> allReviewsForCourse = databaseManager.getCourseReviews(courseName.getDepartment(), courseName.getCatalogNumber());
        return (!allReviewsForCourse.isEmpty());
    }
}
