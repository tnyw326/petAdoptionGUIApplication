package ui;

import java.io.FileNotFoundException;

// creates a new ConsoleInteractiveApp()
public class Main {
    public static void main(String[] args) {
        try {
            new ConsoleInteractiveApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}