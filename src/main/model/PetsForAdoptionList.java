package model;

import java.util.ArrayList;

// Represents a list of pets that are available for adoption
public class PetsForAdoptionList {
    ArrayList<Pet> pets;

    public PetsForAdoptionList() {
        pets = new ArrayList<Pet>();
    }

    /*
     *
     * MODIFIES: this
     * EFFECTS: add a pet into the list unless it is already there,
     *          if already contained in the list, do nothing.
     */
    public void addPet(Pet pet) {
        if (!pets.contains(pet)) {
            pets.add(pet);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: remove the selected pet from the list if list is not empty, if
     *          list is empty, do nothing
     */
    public void adoptPet(String name) {
        for (Pet pet : pets) {
            if (name == pet.getName()) {
                pets.remove(pet);
            }
        }
    }

    /*
     * EFFECTS: show info about the selected pet
     */
    public Pet selectAndViewPet(String name) {
        for (Pet pet : pets) {
            if (pet.getName() == name) {
                return pet;
            }
        }
        return null;
    }

    public int getSize() {
        return pets.size();
    }

    public Pet getPet(int num) {
        return pets.get(num);
    }
}
