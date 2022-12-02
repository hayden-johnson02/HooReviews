package edu.virginia.cs;

import java.util.Scanner;

public class LoginMenu {

    private Scanner scanner;
    private Student user;
    private boolean loginSuccess;
    private boolean sessionActive;

    public LoginMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void initialize() {
        user = null;
        loginSuccess = false;
        sessionActive = true;
        System.out.println("Current User: " + user);
    }

    public void run() {
        initialize();
        String input = "";
        while(!loginSuccess && !input.equalsIgnoreCase("quit")) {
            input = getLoginMenuInput();
            if (isValidLoginMenuNumber(input)) {
                loginSuccess = executePromptForInput(Integer.parseInt(input));
            }
        }
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

    private boolean executePromptForInput(int choice) {
        return switch(choice) {
            case 1 -> attemptExistingUserLogin();
            case 2 -> attemptNewUserLogin();
            default -> throw new IllegalArgumentException("Invalid Entry choice: " + choice);
        };
    }

    private boolean attemptNewUserLogin() {
        System.out.print("New Login Name: ");
        String newLoginName = scanner.next();
        System.out.print("New Password: ");
        String newPassword = scanner.next();
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.next();

        if (newPassword.equals(confirmPassword)) {
            // create new Student object and add student to table
            user = new Student(newLoginName, newPassword);
            return true;
        }
        else {
            System.out.println("\nPasswords did not match.");
            return false;
        }
    }

    private boolean attemptExistingUserLogin() {
        System.out.print("Login Name: ");
        String loginName = scanner.next();
        System.out.print("Password: ");
        String password = scanner.next();

        // Search database for loginName and confirm password matches
        // Proceed to main menu
        user = new Student(loginName, password);
        return true;
    }

    public boolean getLoginSuccess() {
        return loginSuccess;
    }

    public Student getUser() {
        return this.user;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }
}
