package edu.virginia.cs;

import java.util.Scanner;

public class MainMenu {
    private Student user;
    private Scanner scanner;
    private ReviewService reviewService;
    private boolean sessionActive;
    private boolean loggedOut;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize() {
        reviewService = new ReviewService();
        sessionActive = true;
        loggedOut = false;
    }

    public void run() {
        initialize();
        String input = "";
        while(!loggedOut && !input.equalsIgnoreCase("quit")) {
            input = getMenuChoice();
            if (isValidMenuChoice(input)) {
                executePromptForInput(Integer.parseInt(input));
            }
        }
        if (input.equalsIgnoreCase("quit")) {
            sessionActive = false;
        }
    }

    private String getMenuChoice(){
        System.out.println("""
                HooReviews: Main
                
                Enter number to make a selection:
                1) Submit Review for a Course
                2) See Reviews for a Course
                3) Log Out
                """);
        return scanner.next().strip();
    }

    private boolean isValidMenuChoice(String input) {
        try {
            int choice = Integer.parseInt(input);
            return 1 <= choice && choice <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void executePromptForInput(int choice) {
        switch(choice) {
            case 1 -> submitReview();
            case 2 -> seeReviews();
            case 3 -> logout();
            default -> throw new IllegalArgumentException("Invalid Entry choice: " + choice);
        }
    }

    private void logout() {
        user = null;
        loggedOut = true;
    }

    private void submitReview() {
        Course course = getCourseFromInput();
        if (course != null) {
            // TODO: Get review as string from user and write to database
            // Printing for now to validate course object
            System.out.println(course.getDepartment() + " " + course.getCatalogNumber());
            System.out.println("Write review for course: ");
            String review = scanner.next();
            System.out.println(review);
        }
    }

    private void seeReviews() {
        Course course = getCourseFromInput();
        if (course != null) {
            // TODO: Query database for course reviews and print - print error message if course not in database

            // Printing for now to validate course object
            System.out.println(course.getDepartment() + " " + course.getCatalogNumber());
        }
    }

    private Course getCourseFromInput() {
        String input = getCourseInput();
        if (isValidCourse(input)) {
            String[] courseName = input.split(" ");
            String department = courseName[0];
            String catalogNumber = courseName[1];
            return new Course(department, Integer.parseInt(catalogNumber));
        }
        else {
            System.out.println("Invalid course name. Courses have format of 2-4 capital letters followed by 4 digits.");
        }
        return null;
    }

    private boolean isValidCourse(String input) {
        String[] courseName = input.split(" ");
        if (courseName.length != 2) {
            return false;
        }
        else return isValidDepartment(courseName[0]) && isValidCatalogNumber(courseName[1]);
    }

    private boolean isValidCatalogNumber(String catalogNumber) {
        if (catalogNumber.length() == 4) {
            for (int i=0; i<catalogNumber.length(); i++) {
                if((int) catalogNumber.charAt(i) > 9) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isValidDepartment(String department) {
        if (department.length() >= 2 && department.length() <= 4) {
            for (int i=0; i<department.length(); i++) {
                if((int) department.charAt(i) < 65 || (int) department.charAt(i) > 90) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    private String getCourseInput() {
        System.out.print("""
                HooReviews: Main
                
                Enter a valid course name:\040""");
        return scanner.next();
    }

    public boolean isSessionActive(){return sessionActive;}
    public boolean isLoggedIn() {
        return !loggedOut;
    }
    public Student getUser() {return this.user;}



}
