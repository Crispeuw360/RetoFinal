package modelo;

/**
 * Represents a worker in the system.
 * A worker can either be an administrator or a regular employee, 
 * and is associated with a specific car dealership.
 * 
 * @author Alex Deorbe
 * @version 1.0
 */
public class Worker {

    private boolean admin;
    private String user;
    private String password;
    private int id_car_dealer;

    /**
     * Constructs a new Worker with the specified username, password, admin status, and car dealership ID.
     *
     * @param user          The username of the worker.
     * @param password      The password of the worker.
     * @param admin         Indicates if the worker is an administrator.
     * @param id_car_dealer The ID of the car dealership where the worker is assigned.
     */
    public Worker(String user, String password, boolean admin, int id_car_dealer) {
        this.admin = admin;
        this.user = user;
        this.password = password;
        this.id_car_dealer = id_car_dealer;
    }

    /**
     * Constructs a new Worker with default values (not an admin, empty username, empty password, and no dealership assigned).
     */
    public Worker() {
        this.admin = false;
        this.user = "";
        this.password = "";
        this.id_car_dealer = 0;
    }

    /**
     * Checks if the worker is an administrator.
     *
     * @return {@code true} if the worker is an administrator, {@code false} otherwise.
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * Sets the administrator status of the worker.
     *
     * @param admin {@code true} to make the worker an administrator, {@code false} otherwise.
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Gets the username of the worker.
     *
     * @return The username of the worker.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the username of the worker.
     *
     * @param user The new username for the worker.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Gets the password of the worker.
     *
     * @return The password of the worker.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the worker.
     *
     * @param password The new password for the worker.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the ID of the car dealership assigned to the worker.
     *
     * @return The car dealership ID.
     */
    public int getId_car_dealer() {
        return id_car_dealer;
    }

    /**
     * Sets the ID of the car dealership assigned to the worker.
     *
     * @param id_car_dealer The new car dealership ID.
     */
    public void setId_car_dealer(int id_car_dealer) {
        this.id_car_dealer = id_car_dealer;
    }

    /**
     * Returns a string representation of the worker.
     *
     * @return A string containing the worker's admin status, username, password, and dealership ID.
     */
    @Override
    public String toString() {
        return "Worker [admin=" + admin + ", user=" + user + ", password=" + password + ", id_car_dealer="
                + id_car_dealer + "]";
    }
}

