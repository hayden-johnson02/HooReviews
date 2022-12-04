package edu.virginia.cs;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu {
    private Student user;
    private Scanner scanner;
    private BusinessLogic businessLogic;
    private boolean sessionActive;
    private boolean loggedOut;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize(Student user) {
        this.user = user;
        businessLogic = new BusinessLogic(DatabaseManagerImpl.getDatabaseManager());
        sessionActive = true;
        loggedOut = false;
    }

    public void run(Student user) {
        initialize(user);
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
        System.out.println("\nUser: " + user.getUsername());
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
            System.out.print("Write review for " + course + ": ");
            String text = scanner.nextLine();
            System.out.print("Enter rating (1-5): ");
            int rating = scanner.nextInt();
            Review review = new Review(user, course, text, rating);
            businessLogic.addReviewForCourse(review);
        }
    }

    private void seeReviews() {

        Course course = getCourseFromInput();
        if (course != null) {
            // TODO: Query database for course reviews and print - print error message if course not in database
            if (businessLogic.isExistingCourse(course) && businessLogic.courseHasReviews(course)) {
                List<ReviewMessage> allReviews = businessLogic.getReviewsForCourse(course);
                int avgScore = 0;
                for(ReviewMessage currentReview : allReviews) {
                    System.out.println(currentReview.getStudent()+" - "+currentReview.getMessage());
                    avgScore += currentReview.getScore();
                }
                System.out.println("Course average - " + avgScore/allReviews.size()+"/5");

            }
            else if (businessLogic.isExistingCourse(course) && !businessLogic.courseHasReviews(course)) {
                System.out.println("Sorry, "+course.getDepartment()+" "+course.getCatalogNumber()+" does not have any reviews yet.\n");
            }
            else {
                System.out.println(course.getDepartment()+" "+course.getCatalogNumber()+" does not exist as a course\n");
            }
        }
    }

    private Course getCourseFromInput() {
        String[] input = getCourseName();
        if (isValidCourseName(input)) {
            String department = input[0];
            int catalogNumber = Integer.parseInt(input[1]);
            return new Course(department, catalogNumber);
        }
        else {
            System.out.println("Invalid course name. Courses have format of 2-4 capital letters followed by 4 digits.");
        }
        return null;
    }

    private String[] getCourseName() {
        System.out.print("""
                
                Enter a valid course name:\040""");
        String s = scanner.nextLine();
        return scanner.nextLine().split(" ");
    }

    private boolean isValidCourseName(String[] input) {
        if (input.length != 2) {
            return false;
        }
        Pattern pattern = Pattern.compile("[A-Z]{2,4}[\\d]{4}");
        Matcher matcher = pattern.matcher(input[0] + input[1]);
        return matcher.matches();
    }

    public boolean isSessionActive(){return sessionActive;}
    public boolean isLoggedIn() {
        return !loggedOut;
    }
    public Student getUser() {return this.user;}



}
