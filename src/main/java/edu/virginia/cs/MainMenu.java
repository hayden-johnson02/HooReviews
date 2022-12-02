package edu.virginia.cs;

import java.util.Scanner;

public class MainMenu {
    private Student user;
    private Scanner scanner;
    private ReviewService reviewService;
    private boolean loggedIn;

    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize() {
        scanner = new Scanner(System.in);
        reviewService = new ReviewService();
        loggedIn = false;
    }

    public void run() {
        initialize();
        String input = "";
        while(!loggedIn) {
            input = getMenuChoice();
            if (isValidLoginMenuNumber(input)) {
                executePromptForInput(Integer.parseInt(input));
            }
        }
        scanner.close();
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

    private boolean isValidLoginMenuNumber(String input) {
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
        };
    }

    private void logout() {
        user = null;
        loggedIn = false;
    }

    private void seeReviews() {
    }

    private void submitReview() {
    }

    private Course getCourseName() {
        String input = getCourseInput();
        if (isValidCourse(input)) {
            String[] courseName = input.split(" ");
            String department = courseName[0];
            String catalogNumber = courseName[1];
            return new Course(department, Integer.parseInt(catalogNumber));
        }
        else {
            System.out.println("Invalid course name. Courses have format of 2-4 capital letters followed by 4 digits.");
            getMenuChoice();
        }
        return null;
    }

    private boolean isValidCourse(String input) {
        String[] courseName = input.split(" ");
        if (courseName.length != 2) {
            return false;
        }
        else if (isValidDepartment(courseName[0]) && isValidCatalogNumber(courseName[1])) {
            return true;
        }
        return false;
    }

    private boolean isValidCatalogNumber(String catalogNumber) {
        if (catalogNumber.length() == 4) {
            for (int i=0; i<catalogNumber.length(); i++) {
                if((int) catalogNumber.charAt(i) < 0 || (int) catalogNumber.charAt(i) > 9) {
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
        System.out.println("""
                HooReviews: Main
                
                Enter a valid course name: 
                """);
        return scanner.next().strip();
    }

    public Student getUser() {
        return this.user;
    }
    public void setUser(Student user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return isLoggedIn();
    }



}
