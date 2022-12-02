package edu.virginia.cs;

// Entire class is heavily influenced by McBurney's CommandLineUI in his ThreeTierExample from 10/13

import java.util.Scanner;

public class CommandLineUI {

    private Scanner scanner;
    private Student user;
    private LoginMenu loginMenu;

    private MainMenu mainMenu;
    private boolean loggedIn;

    public static void main(String[] args) {
        CommandLineUI clui = new CommandLineUI();
        clui.run();
    }

    private void initialize() {
        scanner = new Scanner(System.in);
        loginMenu = new LoginMenu(new Scanner(System.in));
        mainMenu = new MainMenu(scanner);
    }

    private void run() {
        initialize();
        String input = "";
        while(!input.equalsIgnoreCase("quit")) {
            if (!loggedIn) {
                loginMenu.run();
                user = loginMenu.getUser();
                mainMenu.setUser(user);
            }
            else {
                mainMenu.run();
                loggedIn = mainMenu.isLoggedIn();
            }
        }
        scanner.close();

    }


}
