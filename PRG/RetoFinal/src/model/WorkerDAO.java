package model;

import java.time.LocalDate;
import java.util.Map;

/**
 * Interface for managing workers, dealerships, clients, and models.
 */
public interface WorkerDAO {

    /**
     * Retrieves the models from a dealership.
     *
     * @param cardealer the dealership
     * @return a map of model names to Model objects
     */
    public Map<String, Model> getModels(CarDealership cardealer);

    /**
     * Retrieves all workers.
     *
     * @return a map of worker identifiers to Worker objects
     */
    public Map<String, Worker> getWorkers();

    /**
     * Retrieves the co-workers of a worker.
     *
     * @param worker the worker
     * @return a map of co-worker identifiers to Worker objects
     */
    public Map<String, Worker> getCoWorkers(Worker worker);

    /**
     * Retrieves the dealership where a worker is employed.
     *
     * @param worker the worker
     * @return the dealership
     */
    public CarDealership getWorkingPlace(Worker worker);

    /**
     * Retrieves a dealership by its name.
     *
     * @param name the dealership name
     * @return the dealership
     */
    public CarDealership getDealership(String name);
    
    /**
     * Retrieves all available dealerships.
     *
     * @return a map of dealership names to CarDealership objects
     */
    public Map<String, CarDealership> getAllDeals();

    /**
     * Deletes a model.
     *
     * @param model the model to delete
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteModel(Model model);

    /**
     * Checks if a worker exists.
     *
     * @param worker the worker to check
     * @return the found worker, or null if not found
     */
    public Worker checkWorker(Worker worker);

    /**
     * Modifies a worker's data.
     *
     * @param worker the worker to modify
     * @return true if modified successfully, false otherwise
     */
    public boolean modifyWorker(Worker worker);

    /**
     * Retrieves a worker by its identifier.
     *
     * @param worker the worker identifier
     * @return the worker
     */
    public Worker getWorker(String worker);

    /**
     * Deletes a worker.
     *
     * @param worker the worker to delete
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteWorker(Worker worker);

    /**
     * Creates a new worker.
     *
     * @param worker the worker to create
     * @return true if created successfully, false otherwise
     */
    public boolean createWorker(Worker worker);

    /**
     * Retrieves all clients.
     *
     * @return a map of client identifiers to Client objects
     */
    public Map<String, Client> getClients_();

    /**
     * Calls a procedure related to a sale.
     *
     * @param client the client
     * @param model the model
     * @param worker the worker
     * @param actualDate the date of the sale
     * @param quantity the quantity sold
     * @return true if the procedure executed successfully, false otherwise
     */
    public boolean callProcedure(Client client, Model model, Worker worker, LocalDate actualDate, int quantity);

    /**
     * Checks the stock of a model.
     *
     * @param model the model to check
     * @return the number of units available
     */
    public int checkStock(Model model);

    /**
     * Retrieves the models managed by a worker.
     *
     * @param worker the worker
     * @return a map of model names to Model objects
     */
    public Map<String, Model> getModels(Worker worker);

    /**
     * Modifies a model's data.
     *
     * @param model the model to modify
     * @return true if modified successfully, false otherwise
     */
    public boolean modifyModel(Model model);

    /**
     * Inserts a new client.
     *
     * @param client the client to insert
     * @return true if inserted successfully, false otherwise
     */
    public boolean insertClient(Client client);

    /**
     * Creates a new model.
     *
     * @param model the model to create
     * @return true if created successfully, false otherwise
     */
    public boolean createModel(Model model);

    /**
     * Retrieves a model by its name.
     *
     * @param name_model the model name
     * @return the model
     */
    public Model getModel(String name_model);
}
