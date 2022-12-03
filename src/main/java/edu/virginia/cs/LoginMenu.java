package edu.virginia.cs;

import java.util.Scanner;

public class LoginMenu {

    private Scanner scanner;
    private Student user;
    private boolean loggedIn;
    private boolean sessionActive;

    public LoginMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize() {
        user = null;
        loggedIn = false;
        sessionActive = true;
    }

    public void run() {
        initialize();
        String input = "";
        while(!loggedIn && !input.equalsIgnoreCase("quit")) {
            input = getLoginMenuInput();
            if (isValidLoginMenuNumber(input)) {
                executePromptForInput(Integer.parseInt(input));
            }
        }
        // If quit is typed at any point, indicate session is no longer active to close app
        if (input.equalsIgnoreCase("quit")) {
            sessionActive = false;
        }
    }

    private String getLoginMenuInput(){
        System.out.println("""
                HooReviews: Login
                
                Enter number to make a selection:
                1) Login to Existing Account
                2) Create New User
                """);
        return scanner.next().strip();
    }

    private boolean isValidLoginMenuNumber(String input) {
        try {
            int choice = Integer.parseInt(input);
            return 1 <= choice && choice <= 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void executePromptForInput(int choice) {
        switch(choice) {
            case 1 -> attemptExistingUserLogin();
            case 2 -> attemptNewUserLogin();
            default -> throw new IllegalArgumentException("Invalid Entry choice: " + choice);
        }
    }

    private void attemptNewUserLogin() {
        System.out.print("New Username: ");
        String newUsername = scanner.next();
        System.out.print("New Password: ");
        String newPassword = scanner.next();
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.next();

        if (newPassword.equals(confirmPassword)) {
            // TODO: create new Student object and add to database
            user = new Student(newUsername, newPassword);
            loggedIn = true;
        }
        else {
            System.out.println("\nPasswords did not match. Try again.");
        }
    }

    private void attemptExistingUserLogin() {
        System.out.print("Username: ");
        String loginName = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        // TODO: Search database for loginName and confirm password matches
        /* if (businessLogic.isExistingUser(username, password)) {
            user = businessLogic.getStudentByName(username)
        }
        else {
            System.out.println("Invalid username or password")
         }
         */
        loggedIn = true;
    }

    public boolean isLoggedIn() {return loggedIn;}
    public Student getUser() {return this.user;}
    public boolean isSessionActive() {return sessionActive;}
}
