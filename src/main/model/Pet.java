package model;

// Represents an animal with information including name, species, age
public class Pet {
    private String name;          // pet's name
    private String specie;        // pet's specie
    private String breed;         // pet's breed
    private int age;              // pet's age


    /*
     * REQUIRES: name has a non-zero length
     *           specie has a non-zero length,
     *           breed has a non-zero length,
     *           age >= 0, and -1 if it is unknown.
     * EFFECTS: name of the pet is set to name, specie of the pet is set
     *          to specie, age of the pet is set to age
     */
    public Pet(String name, String specie, String breed, int age) {
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSpecie() {
        return specie;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }
}
