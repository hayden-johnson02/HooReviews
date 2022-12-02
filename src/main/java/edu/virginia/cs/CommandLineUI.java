package edu.virginia.cs;

// Entire class is heavily influenced by McBurney's CommandLineUI in his ThreeTierExample from 10/13

import java.util.Scanner;

public class CommandLineUI {

    private Scanner scanner;
    private LoginMenu loginMenu;
    private MainMenu mainMenu;
    private boolean sessionActive;

    public static void main(String[] args) {
        CommandLineUI clui = new CommandLineUI();
        clui.run();
    }

    private void initialize() {
        scanner = new Scanner(System.in);
        loginMenu = new LoginMenu(scanner);
        mainMenu = new MainMenu(scanner);
        sessionActive = true;
    }

    private void run() {
        initialize();
        while(sessionActive) {
            if (!loginMenu.getLoginSuccess()) {
                loginMenu.run();
                sessionActive = loginMenu.isSessionActive();
                mainMenu.signInUser(loginMenu.getUser());
            }
            else if (mainMenu.isLoggedIn()){
                mainMenu.run();
            }
        }
        scanner.close();

    }


}
