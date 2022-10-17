package ui;

import model.Pet;
import model.PetsForAdoptionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.StreamSupport;

// The console based application for pets adoption
public class ConsoleInteractiveApp {
    private PetsForAdoptionList pets;
    private Scanner input;

    // EFFECTS: run the application
    public ConsoleInteractiveApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;
        initializeApp();

        while (keepGoing) {
            showOptions();
            command = input.next();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                runCommand(command);
            }
        }

        System.out.println("\nThank you for using our app, have a nice day!");
    }

    // MODIFIES: this and pets
    // EFFECTS: initializes the list of pets for adoption
    public void initializeApp() {
        Pet coco = new Pet("coco", "dog", "golden retriever", 4);
        Pet lucky = new Pet("lucky", "dog", "poodle", 3);
        Pet odin = new Pet("odin", "cat", "british shorthair", 7);
        Pet teddy = new Pet("teddy", "dog", "husky", 2);

        pets = new PetsForAdoptionList();
        pets.addPet(coco);
        pets.addPet(lucky);
        pets.addPet(odin);
        pets.addPet(teddy);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void runCommand(String command) {
        if (command.equals("p")) {
            createPet();
        } else if (command.equals("a")) {
            adoptPetFromPets();
        }
    }

    // MODIFIES: pets
    // EFFECTS: remove the selected pet from pets
    private void adoptPetFromPets() {
        System.out.println("here are the pets that are available for adoption: ");
        printPets();
        System.out.println("\nplease enter the name of the pet that you want to adopt");
        String nameOfPetInterested = input.next();
        pets.adoptPet(nameOfPetInterested);
        System.out.println("Congratulations!");
    }

    // MODIFIES: pets
    // EFFECTS: create a pet with given information and add it
    //          to the petList
    private void createPet() {
        System.out.println("what is the name of your pet?");
        String nameOfPet = input.next();
        System.out.println("what is the specie of your pet?");
        String specieOfPet = input.next();
        System.out.println("what is the breed of your pet?");
        String breedOfPet = input.next();
        System.out.println("how old is your pet?(enter a whole number)");
        int ageOfPet = input.nextInt();

        Pet pet = new Pet(nameOfPet, specieOfPet, breedOfPet, ageOfPet);
        pets.addPet(pet);

        System.out.println("nice! you've successfully added your pet to our list, hope your pet will be adopted soon!");
    }

    // EFFECTS: display all the valid commands
    private void showOptions() {
        System.out.println("Do you have a pet for adoption or do you want to adopt a pet?");
        System.out.println("p - posting a pet for adoption");
        System.out.println("a - adopting a pet");
        System.out.println("s - select a pet and view it's information");
        System.out.println("q - quit");
    }

    // EFFECTS: print each pet's name, specie and breed
    private void printPets() {
        for (int i = 0; i < pets.getSize(); i++) {
            System.out.println(pets.getPet(i).getName() + "(" + pets.getPet(i).getSpecie() + ") "
                    + ", breed:  " + pets.getPet(i).getBreed());
        }
    }
}
