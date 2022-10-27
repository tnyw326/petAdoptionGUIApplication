package persistence;

import model.Pet;
import model.PetsForAdoptionList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads pets for adoption list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: constructs a reader to read file
    public PetsForAdoptionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePetForAdoptionList(jsonObject);
    }

    // EFFECTS: parses pet for adoption list from JSON object and returns it
    private PetsForAdoptionList parsePetForAdoptionList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PetsForAdoptionList pl = new PetsForAdoptionList();
        addPets(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses pets from JSON object and adds them to pet for adoption list
    private void addPets(PetsForAdoptionList pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pets");
        for (Object json : jsonArray) {
            JSONObject nextPet = (JSONObject) json;
            addPet(pl, nextPet);
        }
    }

    private String readFile(String source) throws  IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: pl
    // EFFECTS: parses pet from JSON object and adds it to pet for adoption list
    private void addPet(PetsForAdoptionList pl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String specie = jsonObject.getString("specie");
        String breed = jsonObject.getString("breed");
        int age = jsonObject.getInt("age");
        Pet pet = new Pet(name, specie,breed, age);
        pl.addPet(pet);
    }

}
