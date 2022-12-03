package edu.virginia.cs;

import java.util.Map;

public class BusinessLogic {

    private ReviewService reviewService;
    private Map<String, Course> courseMap;
    private Map<String, Student> userMap;

    public void addReviewForCourse(Review review) {
        // dataLayer.addCourse
    }

    public void getReviewsForCourse(Course course) {
        // dataLayer.getCourse
    }

    public boolean isExistingUser(String username) {
        // List tempList = dataLayer.getAllCourses
        // for (Student student : tempList) {
        //      if (student.getName == courseName) {
        //          return true;
        //      }
        // }
        return false;
    }

    public boolean isExistingCourse(Course courseName) {
        // List tempList = dataLayer.getAllCourses
        // for (Course course : tempList) {
        //      if (course.getName == courseName) {
        //          return true;
        //      }
        // }
        return false;
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
