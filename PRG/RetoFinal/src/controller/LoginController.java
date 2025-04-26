package controller;

import java.time.LocalDate;
import java.util.Map;

import model.*;
import view.WindowLogin;
import view.WindowMain;

/**
 * The main controller class that manages application logic and data flow
 * between the model and view components.
 * 
 * <p>This controller handles user authentication, data retrieval, and business logic
 * operations. It serves as the intermediary between the DAO layer and the view layer,
 * coordinating all application functionality.</p>
 *
 * @author All
 * @version 1.0
 */
public class LoginController {

    private WorkerDAO dao = new ImplementsBD();

    /**
     * Displays the initial login window to start the application.
     */
    public void visualizarPantalla() {
        WindowLogin ven = new WindowLogin(this);
        ven.setVisible(true);
    }

    /**
     * Retrieves all models associated with a specific car dealership.
     *
     * @param cardealer The car dealership whose models to retrieve
     * @return Map of models keyed by model name
     */
    public Map<String, Model> getModels(CarDealership cardealer) {
        return dao.getModels(cardealer);
    }

    /**
     * Retrieves all models associated with a worker's dealership.
     *
     * @param worker The worker whose dealership's models to retrieve
     * @return Map of models keyed by model name
     */
    public Map<String, Model> getModels(Worker worker) {
        return dao.getModels(worker);
    }

    /**
     * Retrieves all workers in the system.
     *
     * @return Map of workers keyed by username
     */
    public Map<String, Worker> getWorkers() {
        return dao.getWorkers();
    }

    /**
     * Retrieves coworkers from the same dealership as the given worker.
     *
     * @param worker The worker whose coworkers to retrieve
     * @return Map of coworkers keyed by username
     */
    public Map<String, Worker> getCoWorkers(Worker worker) {
        return dao.getCoWorkers(worker);
    }

    /**
     * Retrieves the dealership where a worker is employed.
     *
     * @param worker The worker whose workplace to find
     * @return The car dealership where the worker works
     */
    public CarDealership getWorkingPlace(Worker worker) {
        return dao.getWorkingPlace(worker);
    }

    /**
     * Retrieves all car dealerships in the system.
     *
     * @return Map of car dealerships keyed by dealership name
     */
    public Map<String, CarDealership> getAllDeals() {
        return dao.getAllDeals();
    }

    /**
     * Deletes a model from the system.
     *
     * @param model The model to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteModel(Model model) {
        return dao.deleteModel(model);
    }

    /**
     * Authenticates a worker's credentials.
     *
     * @param worker The worker to authenticate (contains username and password)
     * @return The authenticated worker object if successful, null otherwise
     */
    public Worker checkWorker(Worker worker) {
        return dao.checkWorker(worker);
    }

    /**
     * Updates a worker's information in the system.
     *
     * @param worker The worker with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean modifyWorker(Worker worker) {
        return dao.modifyWorker(worker);
    }

    /**
     * Retrieves a worker by username.
     *
     * @param worker The username of the worker to retrieve
     * @return The worker object if found, null otherwise
     */
    public Worker getWorker(String worker) {
        return dao.getWorker(worker);
    }

    /**
     * Deletes a worker from the system.
     *
     * @param worker The worker to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteWorker(Worker worker) {
        return dao.deleteWorker(worker);
    }

    /**
     * Creates a new worker in the system.
     *
     * @param worker The worker to create
     * @return true if creation was successful, false otherwise
     */
    public boolean createWorker(Worker worker) {
        return dao.createWorker(worker);
    }

    /**
     * Retrieves all clients in the system.
     *
     * @return Map of clients keyed by username
     */
    public Map<String, Client> getClients() {
        return dao.getClients_();
    }

    /**
     * Executes the purchase procedure in the database.
     *
     * @param client The client making the purchase
     * @param model The model being purchased
     * @param worker The worker processing the purchase
     * @param actualDate The date of the purchase
     * @param quantity The quantity being purchased
     * @return true if the procedure executed successfully, false otherwise
     */
    public boolean callProcedure(Client client, Model model, Worker worker, LocalDate actualDate, int quantity) {
        return dao.callProcedure(client, model, worker, actualDate, quantity);
    }

    /**
     * Checks the current stock level of a model.
     *
     * @param model The model to check
     * @return The current stock quantity
     */
    public int checkStock(Model model) {
        return dao.checkStock(model);
    }

    /**
     * Updates a model's information in the system.
     *
     * @param model The model with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean modifyModel(Model model) {
        return dao.modifyModel(model);
    }

    /**
     * Adds a new client to the system.
     *
     * @param client The client to add
     * @return true if insertion was successful, false otherwise
     */
    public boolean insertClient(Client client) {
        return dao.insertClient(client);
    }

    /**
     * Retrieves a car dealership by name.
     *
     * @param name The name of the dealership to retrieve
     * @return The car dealership if found, null otherwise
     */
    public CarDealership getDealership(String name) {
        return dao.getDealership(name);
    }
    
    /**
     * Creates a new model in the system.
     *
     * @param model The model to create
     * @return true if creation was successful, false otherwise
     */
    public boolean createModel(Model model) {
        return dao.createModel(model);
    }

    /**
     * Retrieves a model by name.
     *
     * @param name_model The name of the model to retrieve
     * @return The model object if found, null otherwise
     */
    public Model getModel(String name_model) {
        return dao.getModel(name_model);
    }
}