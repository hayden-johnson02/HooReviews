package edu.virginia.cs;

import java.util.Scanner;

public class LoginMenu {
    private DatabaseManagerImpl databaseManager;
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
        databaseManager = DatabaseManagerImpl.getDatabaseManager();
        String input = "";
        while(!loggedIn && !input.equalsIgnoreCase("quit")) {
            input = getLoginMenuInput();
            if (isValidLoginMenuNumber(input)) {
                executePromptForInput(Integer.parseInt(input));
            }
            else if (!input.equalsIgnoreCase("quit")){System.out.println("Invalid entry choice: " + input);}
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
                3) Exit
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
            case 1 -> attemptExistingUserLogin();
            case 2 -> attemptNewUserLogin();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid Entry choice: " + choice);
        }
    }

    private void attemptNewUserLogin() {
        System.out.println("Username and password must only be 1 word: ");
        System.out.print("New Username: ");
        String newUsername = scanner.next();
        System.out.print("New Password: ");
        String newPassword = scanner.next();
        System.out.print("Confirm Password: ");
        String confirmPassword = scanner.next();

        if (newPassword.equals(confirmPassword)) {
            if(databaseManager.checkIfLoginExistsByName(newUsername)) {
                System.out.println("\nStudent with username "+newUsername+" already exists. Please try again.");
            }
            else if(databaseManager.addStudent(newUsername, newPassword)){
                user = new Student(newUsername, newPassword);
                loggedIn = true;
            }
            else {
                System.out.println("Please try again");
            }
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

        if(databaseManager.checkIfLoginExists(loginName, password)){
            user = new Student(loginName, password);  //Try to see if you can use hibernate to automatically do this
            loggedIn = true;
        }
        else{
            System.out.println("\nYour username or password is incorrect. P" +
                    "lease check the info or create a new login if needed.");
        }
    }

    public boolean isLoggedIn() {return loggedIn;}
    public Student getUser() {return this.user;}
    public boolean isSessionActive() {return sessionActive;}
}
