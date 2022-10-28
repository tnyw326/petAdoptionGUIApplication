package persistence;

import model.Pet;
import model.PetsForAdoptionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PetsForAdoptionList pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPetsForAdoptionList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPetsForAdoptionList.json");
        try {
            PetsForAdoptionList pl = reader.read();
            assertEquals(0, pl.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPetsForAdoptionList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPetsForAdoptionList.json");
        try {
            PetsForAdoptionList pl = reader.read();
            assertEquals(2, pl.getSize());
            checkPet("coco", "dog", "poodle", 1, pl.getPet(0));
            checkPet("fido", "dog", "husky", 3, pl.getPet(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
