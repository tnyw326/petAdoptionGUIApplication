package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    private Pet testPet;

    @BeforeEach
    void runBefore() {
        testPet = new Pet("lucky","dog", "poodle", 1);
    }

    @Test
    void testConstructor() {
        assertEquals("lucky", testPet.getName());
        assertEquals("dog", testPet.getSpecie());
        assertEquals("poodle", testPet.getBreed());
        assertEquals(1,testPet.getAge());
    }
}