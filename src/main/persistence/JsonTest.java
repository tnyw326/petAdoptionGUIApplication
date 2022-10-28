package persistence;

import model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPet(String name, String specie, String breed, int age, Pet pet) {
        assertEquals(name, pet.getName());
        assertEquals(specie, pet.getSpecie());
        assertEquals(breed, pet.getBreed());
        assertEquals(age, pet.getAge());
    }
}

