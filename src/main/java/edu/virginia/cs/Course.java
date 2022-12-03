package edu.virginia.cs;

import java.util.HashMap;
import java.util.Map;

public class Course {
    private String department;
    private int catalogNumber;
    private Map<Student,Review> reviews;

    public Course(String department, int catalogNumber, Map<Student,Review> reviews) {
        this.department = department;
        this.catalogNumber = catalogNumber;
        this.reviews = reviews;
    }

    public Course(String department, int catalogNumber) {
        this.department = department;
        this.catalogNumber = catalogNumber;
        this.reviews = new HashMap<>();
    }

    public boolean isEmpty() {return reviews.isEmpty();}

    public int reviewCount() {return reviews.size();}

    public Review getReview(Student student) {return reviews.get(student);}

    public void addReview(Student student, Review newReview) {reviews.put(student, newReview);}

    public boolean hasStudentReviewedCourse(Student student) {return reviews.containsKey(student);}


    public String getDepartment() {return department;}

    public void setDepartment(String name) {this.department = department;}

    public int getCatalogNumber() {return catalogNumber;}

    public void setCatalogNumber(int catalogNumber) {this.catalogNumber = catalogNumber;}

    public String toString() {
        return getDepartment() + " " + getCatalogNumber();
    }


}