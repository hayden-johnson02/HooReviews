package edu.virginia.cs;

import java.util.Scanner;

// Entire class is heavily influenced by McBurney's CommandLineUI in his ThreeTierExample from 10/13

public class CommandLineUI {
    private ReviewService reviewService;
    private Scanner scanner;

    public static void main(String[] args) {
        CommandLineUI clui = new CommandLineUI();
        clui.run();
    }

    private void initialize() {
        reviewService = new ReviewService();
        scanner = new Scanner(System.in);
    }

    private void run() {
        initialize();
        String input = "";
        boolean loginSuccess = false;
        while(!input.equalsIgnoreCase("quit") && !loginSuccess) {
            input = getLoginMenuInput();
            if (isValidLoginMenuNumber(input)) {
                loginSuccess = executePromptForInput(Integer.parseInt(input));
            }
        }
        scanner.close();
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
        return true;
    }


}
