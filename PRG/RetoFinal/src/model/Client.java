package model;

/**
 * Represents a client in the system.
 * Each client has a DNI, email, username, and password.
 * 
 * @author  Kevin
 * @version 1.0
 */
public class Client {

    private String dni;
    private String email;
    private String user_;
    private String password_;

    /**
     * Constructs a new Client with the given details.
     *
     * @param dni       The client's DNI (identification number).
     * @param email     The client's email address.
     * @param user_     The client's username.
     * @param password_ The client's password.
     */
    public Client(String dni, String email, String user_, String password_) {
        this.dni = dni;
        this.email = email;
        this.user_ = user_;
        this.password_ = password_;
    }

    /**
     * Constructs a new Client with empty attributes.
     */
    public Client() {
        this.dni = "";
        this.email = "";
        this.user_ = "";
        this.password_ = "";
    }

    /**
     * Gets the client's DNI.
     *
     * @return The client's DNI.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Sets the client's DNI.
     *
     * @param dni The new DNI for the client.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Gets the client's email address.
     *
     * @return The client's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the client's email address.
     *
     * @param email The new email for the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the client's username.
     *
     * @return The client's username.
     */
    public String getUser_() {
        return user_;
    }

    /**
     * Sets the client's username.
     *
     * @param user_ The new username for the client.
     */
    public void setUser_(String user_) {
        this.user_ = user_;
    }

    /**
     * Gets the client's password.
     *
     * @return The client's password.
     */
    public String getPassword_() {
        return password_;
    }

    /**
     * Sets the client's password.
     *
     * @param password_ The new password for the client.
     */
    public void setPassword_(String password_) {
        this.password_ = password_;
    }

    /**
     * Returns a string representation of the client.
     *
     * @return A string containing the client's DNI, email, username, and password.
     */
    @Override
    public String toString() {
        return "Client [dni=" + dni + ", email=" + email + ", user_=" + user_ + ", password_=" + password_ + "]";
    }
}

