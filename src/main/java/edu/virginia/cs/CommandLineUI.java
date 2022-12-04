package edu.virginia.cs;

// Entire class is heavily influenced by McBurney's CommandLineUI in his ThreeTierExample from 10/13

import java.util.Scanner;

public class CommandLineUI {

    private Scanner scanner;
    private LoginMenu loginMenu;
    private MainMenu mainMenu;
    private boolean sessionActive;
    private Student user;
    private boolean loggedIn;

    public static void main(String[] args) {
        CommandLineUI clui = new CommandLineUI();
        clui.run();
    }

    private void initialize() {
        scanner = new Scanner(System.in);
        loginMenu = new LoginMenu(scanner);
        mainMenu = new MainMenu(scanner);
        sessionActive = true;
        user = null;
        loggedIn = false;
    }

    private void run() {
        initialize();
        while(sessionActive) {
            if (!loggedIn) {
                loginMenu.run();
                sessionActive = loginMenu.isSessionActive();
                loggedIn = loginMenu.isLoggedIn();
                user = loginMenu.getUser();
            }
            else {
                mainMenu.run(user);
                sessionActive = mainMenu.isSessionActive();
                loggedIn = mainMenu.isLoggedIn();
                sessionActive = mainMenu.isSessionActive();
                user = mainMenu.getUser();
            }
        }
        scanner.close();
        System.exit(0);
    }


}
