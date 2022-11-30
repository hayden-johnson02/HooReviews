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

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
