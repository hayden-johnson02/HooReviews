package edu.virginia.cs;

import java.util.Map;

public class BusinessLogic {
    private DatabaseManagerImpl databaseManager;
    private ReviewService reviewService;
    private Map<String, Course> courseMap;
    private Map<String, Student> userMap;

    public BusinessLogic(DatabaseManagerImpl dbManager){
        databaseManager = dbManager;
    }

    public void addReviewForCourse(Review review) {
        // dataLayer.addCourse
        databaseManager.addCourse(review.getCourse());
        databaseManager.addReview(review);
    }

    public void getReviewsForCourse(Course course) {
        // dataLayer.getCourse
    }

    public boolean isExistingUser(String username, String password) {
        return databaseManager.checkIfLoginExists(username, password);
        //return false;
    }

    public boolean isExistingCourse(Course courseName) {
        return databaseManager.doesCourseExist(courseName.getDepartment(), courseName.getCatalogNumber());
        //return false;
    }

    public boolean courseHasReviews(Course courseName) {
        // List tempList = dataLayer.getAllCourses
        // for (Course course : tempList) {
        //      if (course.getName == courseName) {
        //          return course.size() > 0
        //      }
        // }
        return false;
    }
}
