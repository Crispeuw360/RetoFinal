package modelo;

import java.time.LocalDate;

/**
 * Represents a purchase transaction in the system.
 * A purchase is associated with a client (identified by DNI), a car model, 
 * a car dealership, the date of the purchase, the quantity of models bought, 
 * and a unique purchase ID.
 * 
 * @author Pablo
 * @version 1.0
 */
public class Purchase {

    private String dni;
    private String name_model;
    private int id_car_dealer;
    private LocalDate date_purchase;
    private int quantity;
    private int id_purchase;

    /**
     * Constructs a new Purchase with the specified details.
     * 
     * @param dni            The DNI of the client making the purchase.
     * @param name_model     The name of the car model being purchased.
     * @param id_car_dealer  The ID of the car dealership where the purchase is made.
     * @param date_purchase  The date when the purchase was made.
     * @param quantity       The number of units purchased.
     * @param id_purchase    The unique ID of the purchase.
     */
    public Purchase(String dni, String name_model, int id_car_dealer, LocalDate date_purchase, int quantity, int id_purchase) {
        this.dni = dni;
        this.name_model = name_model;
        this.id_car_dealer = id_car_dealer;
        this.date_purchase = date_purchase;
        this.quantity = quantity;
        this.id_purchase = id_purchase;
    }

    /**
     * Constructs a new Purchase with default values.
     * All attributes are initialized to empty or zero values.
     */
    public Purchase() {
        this.dni = "";
        this.name_model = "";
        this.id_car_dealer = 0;
        this.date_purchase = null;
        this.quantity = 0;
        this.id_purchase = 0;
    }

    /**
     * Gets the DNI of the client making the purchase.
     * 
     * @return The client's DNI.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the DNI of the client making the purchase.
     * 
     * @param dni The client's DNI.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Gets the name of the car model being purchased.
     * 
     * @return The car model name.
     */
    public String getName_model() {
        return name_model;
    }

    /**
     * Sets the name of the car model being purchased.
     * 
     * @param name_model The new car model name.
     */
    public void setName_model(String name_model) {
        this.name_model = name_model;
    }

    /**
     * Gets the ID of the car dealership where the purchase was made.
     * 
     * @return The car dealership ID.
     */
    public int getId_car_dealer() {
        return id_car_dealer;
    }

    /**
     * Sets the ID of the car dealership where the purchase was made.
     * 
     * @param id_car_dealer The new car dealership ID.
     */
    public void setId_car_dealer(int id_car_dealer) {
        this.id_car_dealer = id_car_dealer;
    }

    /**
     * Gets the date of the purchase.
     * 
     * @return The purchase date.
     */
    public LocalDate getDate_purchase() {
        return date_purchase;
    }

    /**
     * Sets the date of the purchase.
     * 
     * @param date_purchase The new purchase date.
     */
    public void setDate_purchase(LocalDate date_purchase) {
        this.date_purchase = date_purchase;
    }

    /**
     * Gets the quantity of car models purchased.
     * 
     * @return The quantity purchased.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of car models purchased.
     * 
     * @param quantity The new quantity purchased.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the unique ID of the purchase.
     * 
     * @return The purchase ID.
     */
    public int getId_purchase() {
        return id_purchase;
    }

    /**
     * Sets the unique ID of the purchase.
     * 
     * @param id_purchase The new purchase ID.
     */
    public void setId_purchase(int id_purchase) {
        this.id_purchase = id_purchase;
    }

    /**
     * Returns a string representation of the Purchase object.
     * 
     * @return A formatted string with the purchase details.
     */
    @Override
    public String toString() {
        return "Purchase [dni=" + dni + ", name_model=" + name_model + ", id_car_dealer=" + id_car_dealer
                + ", date_purchase=" + date_purchase + ", quantity=" + quantity + ", id_purchase=" + id_purchase + "]";
    }
}

