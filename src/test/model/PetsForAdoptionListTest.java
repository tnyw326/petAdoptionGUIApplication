package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetsForAdoptionListTest {
    private PetsForAdoptionList testList;
    private Pet coco;
    private Pet fido;
    private Pet gucci;

    @BeforeEach
    void runBefore() {
        coco = new Pet("coco", "dog", "poodle", 1);
        fido = new Pet("fido", "dog", "husky", 3);
        gucci = new Pet("gucci", "cat", "british shorthair", 10);
        testList = new PetsForAdoptionList();
    }

    @Test
    void testAddPet() {
        testList.addPet(coco);
        assertEquals(1, testList.getSize());
        assertEquals(coco, testList.getPet(0));

    }

    @Test
    void testAddPetBiggerSize() {
        testList.addPet(coco);
        testList.addPet(fido);
        testList.addPet(gucci);
        assertEquals(3, testList.getSize());
        assertEquals(coco, testList.getPet(0));
        assertEquals(fido, testList.getPet(1));
        assertEquals(gucci, testList.getPet(2));
        assertEquals(3, testList.getSize());
    }

    @Test
    void testAddPetAlreadyThere() {
        testList.addPet(coco);
        testList.addPet(fido);
        testList.addPet(gucci);
        assertEquals(3, testList.getSize());
        assertEquals(coco, testList.getPet(0));
        assertEquals(fido, testList.getPet(1));
        assertEquals(gucci, testList.getPet(2));
        assertEquals(3, testList.getSize());
    }

    @Test
    void testAdoptPet() {
        testList.addPet(coco);
        testList.addPet(fido);
        testList.addPet(gucci);
        testList.adoptPet("fido");
        assertEquals(2, testList.getSize());
        assertEquals(coco, testList.getPet(0));
        assertEquals(gucci, testList.getPet(1));
    }

    @Test
    void testAdoptPetFromEmptyList() {
        testList.adoptPet("coco");
        assertEquals(0, testList.getSize());
    }

    @Test
    void testContainsPet() {
        testList.addPet(coco);
        testList.addPet(fido);
        testList.addPet(gucci);
        assertTrue(testList.containsPet("coco"));
        assertTrue(testList.containsPet("fido"));
        assertTrue(testList.containsPet("gucci"));
        assertFalse(testList.containsPet("lucky"));
    }

    @Test
    void testGetSize() {
        assertEquals(0, testList.getSize());
        testList.addPet(coco);
        assertEquals(1, testList.getSize());
    }

    @Test
    void testGetPet() {
        testList.addPet(coco);
        testList.addPet(fido);
        testList.addPet(gucci);
        assertEquals(coco, testList.getPet(0));
        assertEquals(fido, testList.getPet(1));
        assertEquals(gucci, testList.getPet(2));
    }
}
