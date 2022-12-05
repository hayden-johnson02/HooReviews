package edu.virginia.cs;

public class Review {
    private Student student;
    private Course course;
    private String reviewText;
    private int rating;

    public Review (Student student, Course course, String reviewText, int rating) {
        this.student = student;
        this.course = course;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Student getStudent() {
        return student;
    }
    public Course getCourse() {
        return course;
    }
    public String getReviewText() {
        return reviewText;
    }

    public int getRating() {
        return rating;
    }
}
