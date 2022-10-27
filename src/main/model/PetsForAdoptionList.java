package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of pets that are available for adoption
public class PetsForAdoptionList implements Writable {
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
        for (int i = 0; i < pets.size(); i++) {
            String petName = pets.get(i).getName();
            if (name.equals(petName)) {
                pets.remove(i);
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: return true if pet is contained in list, else return false
     */
    public Boolean containsPet(String name) {
        for (int i = 0; i < pets.size(); i++) {
            String petName = pets.get(i).getName();
            if (name.equals(petName)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return pets.size();
    }

    public Pet getPet(int num) {
        return pets.get(num);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pets", petsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray petsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pet p : pets) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
