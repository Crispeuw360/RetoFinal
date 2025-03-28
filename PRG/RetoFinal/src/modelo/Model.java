package modelo;

/**
 * Represents a car model in the system.
 * Each car model has a name, brand, stock quantity, price, and associated car dealership.
 * 
 * @author Igor
 * @version 1.0
 */
public class Model {

    private String name_model;
    private String mark;
    private int stock;
    private double price;
    private int id_car_dealer;

    /**
     * Constructs a new Model with the specified name, brand, stock quantity, price, and car dealership ID.
     *
     * @param name_model    The name of the car model.
     * @param mark          The brand of the car model.
     * @param stock         The stock quantity of the car model.
     * @param price         The price of the car model.
     * @param id_car_dealer The ID of the associated car dealership.
     */
    public Model(String name_model, String mark, int stock, double price, int id_car_dealer) {
        super();
        this.name_model = name_model;
        this.mark = mark;
        this.stock = stock;
        this.price = price;
        this.id_car_dealer = id_car_dealer;
    }

    /**
     * Constructs a new Model with default values (empty name, empty brand, 0 stock, 0 price, and car dealership ID 0).
     */
    public Model() {
        super();
        this.name_model = "";
        this.mark = "";
        this.stock = 0;
        this.price = 0;
        this.id_car_dealer = 0;
    }

    /**
     * Gets the name of the car model.
     *
     * @return The name of the car model.
     */
    public String getName_model() {
        return name_model;
    }

    /**
     * Sets the name of the car model.
     *
     * @param name_model The new name for the car model.
     */
    public void setName_model(String name_model) {
        this.name_model = name_model;
    }

    /**
     * Gets the brand of the car model.
     *
     * @return The brand of the car model.
     */
    public String getMark() {
        return mark;
    }

    /**
     * Sets the brand of the car model.
     *
     * @param mark The new brand for the car model.
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * Gets the stock quantity of the car model.
     *
     * @return The stock quantity of the car model.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the car model.
     *
     * @param stock The new stock quantity for the car model.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Gets the price of the car model.
     *
     * @return The price of the car model.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the car model.
     *
     * @param price The new price for the car model.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the ID of the car dealership associated with the car model.
     *
     * @return The ID of the car dealership.
     */
    public int getId_car_dealer() {
        return id_car_dealer;
    }

    /**
     * Sets the ID of the car dealership associated with the car model.
     *
     * @param id_car_dealer The new ID of the car dealership.
     */
    public void setId_car_dealer(int id_car_dealer) {
        this.id_car_dealer = id_car_dealer;
    }

    /**
     * Returns a string representation of the car model.
     *
     * @return A string containing the name, brand, stock, price, and associated car dealership ID.
     */
    @Override
    public String toString() {
        return "Model [name_model=" + name_model + ", mark=" + mark + ", stock=" + stock + ", price=" + price
                + ", id_car_dealer=" + id_car_dealer + "]";
    }
}

