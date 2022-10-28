package ui;

import model.Pet;
import model.PetsForAdoptionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// The console based application for pets adoption
public class ConsoleInteractiveApp {
    private static final String JSON_STORE = "./data/petsforadoptionlist.json";
    private PetsForAdoptionList pets;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the application
    public ConsoleInteractiveApp() throws FileNotFoundException {
        pets = new PetsForAdoptionList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
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
            command = command.toLowerCase();

            if (command.equals("q")) {
                savePetsForAdoptionList();
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
        loadPetsForAdoptionList();

        input = new Scanner(System.in);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void runCommand(String command) {
        if (command.equals("p")) {
            createPet();
        } else if (command.equals("a")) {
            adoptPetFromPets();
        } else if (command.equals("v")) {
            viewList();
        }
    }

    // EFFECTS: view the list of pet
    private void viewList() {
        printPets();
    }

    // MODIFIES: pets
    // EFFECTS: remove the selected pet from pets
    private void adoptPetFromPets() {
        System.out.println("here are the pets that are available for adoption: ");
        printPets();
        System.out.println("\nplease enter the name of the pet that you want to adopt");
        String nameOfPetInterested = input.next();
        if (pets.containsPet(nameOfPetInterested)) {
            pets.adoptPet(nameOfPetInterested);
            System.out.println("Congratulations! You've adopted " + nameOfPetInterested);
        } else {
            System.out.println("Sorry the pet is not found, make sure you've entered the correct name");
        }
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

        System.out.println("\nnice! you've added your pet to our list, hope your pet will be adopted soon!");
    }

    // EFFECTS: display all the valid commands
    private void showOptions() {
        System.out.println("\nDo you have a pet for adoption or do you want to adopt a pet?");
        System.out.println("\tp - posting a pet for adoption");
        System.out.println("\ta - adopting a pet");
        System.out.println("\tv - view the list of pets");
        System.out.println("\tq - quit");
    }

    // EFFECTS: print each pet's name, specie and breed
    private void printPets() {
        for (int i = 0; i < pets.getSize(); i++) {
            System.out.println(pets.getPet(i).getName() + "(" + pets.getPet(i).getSpecie() + ") "
                    + ", breed:  " + pets.getPet(i).getBreed() + ", age: " + pets.getPet(i).getAge());
        }
    }

    // EFFECTS: saves the pets for adoption list to file
    private void savePetsForAdoptionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(pets);
            jsonWriter.close();
            System.out.println("Saved data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads pets for adoption list from file
    private void loadPetsForAdoptionList() {
        try {
            pets = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

