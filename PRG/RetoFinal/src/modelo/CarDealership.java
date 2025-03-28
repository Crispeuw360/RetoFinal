package modelo;

/**
 * Represents a car dealership.
 * Each car dealership has a name, location, and an identifier.
 * 
 * @author Igor
 * @version 1.0
 */
public class CarDealership {

    private String name;
    private String location;
    private int id;

    /**
     * Constructs a new CarDealership with the given name, location, and id.
     *
     * @param name     The name of the car dealership.
     * @param location The location of the car dealership.
     * @param id       The unique identifier for the car dealership.
     */
    public CarDealership(String name, String location, int id) {
        this.name = name;
        this.location = location;
        this.id = id;
    }

    /**
     * Constructs a new CarDealership with empty name, location, and id set to 0.
     */
    public CarDealership() {
        this.name = "";
        this.location = "";
        this.id = 0;
    }

    /**
     * Gets the name of the car dealership.
     *
     * @return The name of the car dealership.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the car dealership.
     *
     * @param name The new name for the car dealership.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location of the car dealership.
     *
     * @return The location of the car dealership.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the car dealership.
     *
     * @param location The new location for the car dealership.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the unique identifier of the car dealership.
     *
     * @return The ID of the car dealership.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the car dealership.
     *
     * @param id The new ID for the car dealership.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the car dealership.
     *
     * @return A string containing the car dealership's name, location, and ID.
     */
    @Override
    public String toString() {
        return "CarDealership [name=" + name + ", location=" + location + ", id=" + id + "]";
    }
}

