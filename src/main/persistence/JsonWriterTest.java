package persistence;

import model.Pet;
import model.PetsForAdoptionList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PetsForAdoptionList pl = new PetsForAdoptionList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PetsForAdoptionList pl = new PetsForAdoptionList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPetsForAdoptionList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPetsForAdoptionList.json");
            pl = reader.read();
            assertEquals(0, pl.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            PetsForAdoptionList pl = new PetsForAdoptionList();
            pl.addPet(new Pet("coco", "dog", "poodle", 1));
            pl.addPet(new Pet("fido", "dog", "husky", 3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPetsForAdoptionList.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPetsForAdoptionList.json");
            pl = reader.read();
            assertEquals(2, pl.getSize());
            checkPet("coco", "dog", "poodle", 1, pl.getPet(0));
            checkPet("fido", "dog", "husky", 3, pl.getPet(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}





