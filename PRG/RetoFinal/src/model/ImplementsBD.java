package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * Implementation of the WorkerDAO interface that provides database access
 * operations using JDBC.
 * 
 * <p>This class handles all database interactions for the application,
 * including CRUD operations for workers, models, clients, and dealerships.</p>
 *
 * @author All
 * @version 1.0
 */
public class ImplementsBD implements WorkerDAO {
    // Atributos
    private Connection con;
    private PreparedStatement stmt;

    // Los siguientes atributos se utilizan para recoger los valores del fich de
    // configuraci n
    private ResourceBundle configFile;
    private String driverBD;
    private String urlBD;
    private String userBD;
    private String passwordBD;

    // SQL queries
    final String SQLGETMODELS = "SELECT * FROM model WHERE id_car_dealer = ?";
    final String SQLGETMODEL = "SELECT * FROM model WHERE name_model = ?";

    final String SQLGETWORKERS = " SELECT * FROM worker";
    final String SQLGETWORKER = "SELECT * FROM worker WHERE user_ = ?";
    final String SQLGETCOWORKERS = " SELECT * FROM worker WHERE id_car_dealer = ?";

    final String SQLGETDEALER = " SELECT * FROM car_dealership WHERE id_car_dealer = ?";
    final String SQLGETDEALS = "SELECT * FROM car_dealership";

    final String SQLDELETEMODEL = "DELETE FROM model WHERE name_model = ?";
    final String SQLDELETEWORKER = "DELETE FROM worker WHERE user_ = ?";
    final String SQLMODIFYWORKER = "UPDATE worker SET password_ = ?, admin_ = ?, id_car_dealer = ? WHERE user_ = ?";

    final String SQLINSERTWORKER = "INSERT INTO worker (admin_, user_, password_, id_car_dealer) VALUES (?, ?, ?, ?)";

    // kev
    final String SQLMODELS = "SELECT * FROM model WHERE ID_CAR_DEALER = ?";
    final String SQLCLIENTS = "SELECT * FROM client_";
    final String SQLSTOCK = "SELECT STOCK FROM model WHERE NAME_MODEL = ? AND ID_CAR_DEALER = ? ";
    final String SQLCALL = "{ CALL REGISTER_PURCHASE(?, ?, ?, ?, ?) }";

    // igor
    final String SQLINSERTCLIENT = "INSERT INTO client_ VALUES ( ?,?,?,?)";
    final String SQLMODIFICARMODEL = "UPDATE MODEL SET MARK = ?, STOCK = ?, PRICE = ? WHERE NAME_MODEL = ? AND ID_CAR_DEALER = ?";

    // pablo
    final String SQLINSERTMODEL = "INSERT INTO model VALUES (?,?,?,?,?)";
    final String SQLLOGIN = "SELECT * FROM worker WHERE user_ = ? AND password_ = ?";
    final String SQLGETDEALERBYNAME = " SELECT * FROM car_dealership WHERE name_ = ?";

    /**
     * Constructs a new ImplementsBD instance and loads database configuration.
     */
    public ImplementsBD() {
        this.configFile = ResourceBundle.getBundle("model.configClase");
        this.driverBD = this.configFile.getString("Driver");
        this.urlBD = this.configFile.getString("Conn");
        this.userBD = this.configFile.getString("DBUser");
        this.passwordBD = this.configFile.getString("DBPass");
    }

    /**
     * Opens a connection to the database using configured parameters.
     */
    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlBD, this.userBD, this.passwordBD);
        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all workers from the database.
     *
     * @return Map of all workers keyed by username
     */
    public Map<String, Worker> getWorkers() {
        ResultSet rs = null;
        Worker worker;
        Map<String, Worker> workers = new TreeMap<>();

        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETWORKERS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                worker = new Worker();
                worker.setAdmin(rs.getBoolean("admin_"));
                worker.setUser(rs.getString("user_"));
                worker.setPassword(rs.getString("password_"));
                worker.setId_car_dealer(rs.getInt("id_car_dealer"));
                workers.put(worker.getUser(), worker);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    /**
     * Retrieves coworkers from the same dealership as the given worker.
     *
     * @param worker The worker whose coworkers to retrieve
     * @return Map of coworkers keyed by username
     */
    public Map<String, Worker> getCoWorkers(Worker worker) {
        ResultSet rs = null;
        Map<String, Worker> workers = new TreeMap<>();
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETCOWORKERS);
            stmt.setInt(1, worker.getId_car_dealer());
            rs = stmt.executeQuery();

            while (rs.next()) {
                worker = new Worker();
                worker.setAdmin(rs.getBoolean("admin_"));
                worker.setUser(rs.getString("user_"));
                worker.setPassword(rs.getString("password_"));
                worker.setId_car_dealer(rs.getInt("id_car_dealer"));
                workers.put(worker.getUser(), worker);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }

    /**
     * Retrieves all models for a specific dealership.
     *
     * @param cardealer The dealership whose models to retrieve
     * @return Map of models keyed by model name
     */
    public Map<String, Model> getModels(CarDealership cardealer) {
        ResultSet rs = null;
        Model model;
        Map<String, Model> models = new TreeMap<>();

        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETMODELS);
            stmt.setInt(1, cardealer.getId());
            rs = stmt.executeQuery();

            while (rs.next()) {
                model = new Model();
                model.setId_car_dealer(rs.getInt("id_car_dealer"));
                model.setMark(rs.getString("mark"));
                model.setName_model(rs.getString("name_model"));
                model.setPrice(rs.getDouble("price"));
                model.setStock(rs.getInt("stock"));
                models.put(model.getName_model(), model);
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }
        return models;
    }

    /**
     * Retrieves the dealership where a worker is employed.
     *
     * @param worker The worker whose workplace to find
     * @return The car dealership object
     */
    public CarDealership getWorkingPlace(Worker worker) {
        CarDealership cardealer = null;
        ResultSet rs = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETDEALER);
            stmt.setInt(1, worker.getId_car_dealer());
            rs = stmt.executeQuery();
            while (rs.next()) {
                cardealer = new CarDealership();
                cardealer.setId(rs.getInt("id_car_dealer"));
                cardealer.setLocation(rs.getString("location"));
                cardealer.setName(rs.getString("name_"));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardealer;
    }

    /**
     * Deletes a model from the database.
     *
     * @param model The model to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteModel(Model model) {
        boolean ok = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLDELETEMODEL);
            stmt.setString(1, model.getName_model());
            if (stmt.executeUpdate() > 0) {
                ok = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al borrar el modelo: " + e.getMessage());
        }
        return ok;
    }

    /**
     * Deletes a worker from the database.
     *
     * @param worker The worker to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteWorker(Worker worker) {
        boolean ok = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLDELETEWORKER);
            stmt.setString(1, worker.getUser());
            if (stmt.executeUpdate() > 0) {
                ok = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ok;
    }

    /**
     * Retrieves all car dealerships from the database.
     *
     * @return Map of all dealerships keyed by name
     */
    public Map<String, CarDealership> getAllDeals() {
        CarDealership cardealer = null;
        Map<String, CarDealership> dealers = new TreeMap<>();
        ResultSet rs = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETDEALS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                cardealer = new CarDealership();
                cardealer.setId(rs.getInt("id_car_dealer"));
                cardealer.setLocation(rs.getString("location"));
                cardealer.setName(rs.getString("name_"));
                dealers.put(cardealer.getName(), cardealer);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dealers;
    }

    /**
     * Updates a worker's information in the database.
     *
     * @param worker The worker with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean modifyWorker(Worker worker) {
        boolean ok = false;

        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFYWORKER);
            stmt.setString(1, worker.getPassword());
            stmt.setBoolean(2, worker.isAdmin());
            stmt.setInt(3, worker.getId_car_dealer());
            stmt.setString(4, worker.getUser());

            ok = stmt.executeUpdate() > 0;

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return ok;
    }

    /**
     * Retrieves a worker by username.
     *
     * @param worker The username to search for
     * @return The Worker object if found, null otherwise
     */
    public Worker getWorker(String worker) {
        Worker foundWorker = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETWORKER);
            stmt.setString(1, worker);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                boolean admin = resultado.getBoolean("admin_");
                String userName = resultado.getString("user_");
                String password = resultado.getString("password_");
                int idCarDealer = resultado.getInt("id_car_dealer");

                foundWorker = new Worker(userName, password, admin, idCarDealer);
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return foundWorker;
    }

    /**
     * Retrieves a model by name.
     *
     * @param modelName The name of the model to retrieve
     * @return The Model object if found, null otherwise
     */
    public Model getModel(String modelName) {
        Model foundModel = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETMODEL);
            stmt.setString(1, modelName);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                String name_model = resultado.getString("name_model");
                String mark = resultado.getString("mark");
                int stock = resultado.getInt("stock");
                double price = resultado.getDouble("price");
                int id_car_dealer = resultado.getInt("id_car_dealer");

                foundModel = new Model(name_model, mark, stock, price, id_car_dealer);
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener el modelo: " + e.getMessage());
        }

        return foundModel;
    }

    /**
     * Creates a new worker in the database.
     *
     * @param worker The worker to create
     * @return true if creation was successful, false otherwise
     */
    @Override
    public boolean createWorker(Worker worker) {
        boolean ok = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLINSERTWORKER);
            stmt.setBoolean(1, worker.isAdmin());
            stmt.setString(2, worker.getUser());
            stmt.setString(3, worker.getPassword());
            stmt.setInt(4, worker.getId_car_dealer());

            if (stmt.executeUpdate() > 0) {
                ok = true;
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return ok;
    }

    /**
     * Creates a new model in the database.
     *
     * @param model The model to create
     * @return true if creation was successful, false otherwise
     */
    public boolean createModel(Model model) {
        boolean creado = false;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLINSERTMODEL);
            stmt.setString(1, model.getName_model());
            stmt.setString(2, model.getMark());
            stmt.setInt(3, model.getStock());
            stmt.setDouble(4, model.getPrice());
            stmt.setInt(5, model.getId_car_dealer());

            if (stmt.executeUpdate() > 0) {
                creado = true;
            }
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el modelo: " + e.getMessage());
        }
        return creado;
    }

    /**
     * Authenticates a worker's credentials.
     *
     * @param worker The worker to authenticate
     * @return The authenticated Worker object if successful, null otherwise
     */
    public Worker checkWorker(Worker worker) {
        Worker foundWorker = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLLOGIN);
            stmt.setString(1, worker.getUser());
            stmt.setString(2, worker.getPassword());
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                boolean esAdmin = resultado.getBoolean("ADMIN_");
                String usuario = resultado.getString("USER_");
                String contraseña = resultado.getString("PASSWORD_");
                int idCarDealer = resultado.getInt("ID_CAR_DEALER");

                foundWorker = new Worker(usuario, contraseña, esAdmin, idCarDealer);
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return foundWorker;
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return Map of all clients keyed by username
     */
    @Override
    public Map<String, Client> getClients_() {
        ResultSet rs = null;
        Client client;
        Map<String, Client> clientsList = new TreeMap<>();

        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLCLIENTS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                client = new Client();
                client.setDni(rs.getString("dni"));
                client.setEmail(rs.getString("email"));
                client.setPassword_((rs.getString("password_")));
                client.setUser_((rs.getString("user_")));
                clientsList.put(client.getUser_(), client);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }
        return clientsList;
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
    @Override
    public boolean callProcedure(Client client, Model model, Worker worker, LocalDate actualDate, int quantity) {
        boolean ok = false;
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String wdate = actualDate.format(formateador);

        this.openConnection();

        try {
            CallableStatement stmt = con.prepareCall(SQLCALL);

            stmt.setString(1, client.getUser_());
            stmt.setString(2, model.getName_model());
            stmt.setInt(3, worker.getId_car_dealer());
            stmt.setString(4, wdate);
            stmt.setInt(5, quantity);

            if (stmt.executeUpdate() > 0) {
                ok = true;
            }
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }

        return ok;
    }

    /**
     * Checks the current stock level of a model.
     *
     * @param model The model to check
     * @return The current stock quantity
     */
    @Override
    public int checkStock(Model model) {
        int stockValue = 0;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLSTOCK);
            stmt.setString(1, model.getName_model());
            stmt.setInt(2, model.getId_car_dealer());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                stockValue = result.getInt("STOCK");
            }

            result.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return stockValue;
    }

    /**
     * Inserts a new client into the database.
     *
     * @param client The client to insert
     * @return true if insertion was successful, false otherwise
     */
    public boolean insertClient(Client client) {
        boolean ok = false;
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLINSERTCLIENT);
            stmt.setString(1, client.getUser_());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.getDni());
            stmt.setString(4, client.getPassword_());
            if (stmt.executeUpdate() > 0) {
                ok = true;
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return ok;
    }

    /**
     * Updates a model's information in the database.
     *
     * @param model The model with updated information
     * @return true if update was successful, false otherwise
     */
    public boolean modifyModel(Model model) {
        boolean ok = false;

        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODIFICARMODEL);
            stmt.setString(1, model.getMark());
            stmt.setInt(2, model.getStock());
            stmt.setDouble(3, model.getPrice());
            stmt.setString(4, model.getName_model());
            stmt.setInt(5, model.getId_car_dealer());
            if (stmt.executeUpdate() > 0) {
                ok = true;
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return ok;
    }

    /**
     * Retrieves all models for a specific worker's dealership.
     *
     * @param worker The worker whose dealership's models to retrieve
     * @return Map of models keyed by model name
     */
    public Map<String, Model> getModels(Worker worker) {
        ResultSet rs = null;
        Model model;
        Map<String, Model> models = new TreeMap<>();
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLMODELS);
            stmt.setInt(1, worker.getId_car_dealer());
            rs = stmt.executeQuery();
            while (rs.next()) {
                model = new Model();
                model.setId_car_dealer(rs.getInt("id_car_dealer"));
                model.setMark(rs.getString("mark"));
                model.setName_model(rs.getString("name_model"));
                model.setPrice(rs.getDouble("price"));
                model.setStock(rs.getInt("stock"));
                models.put(model.getName_model(), model);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error de SQL");
            e.printStackTrace();
        }
        return models;
    }

    /**
     * Retrieves a dealership by name.
     *
     * @param name The name of the dealership to retrieve
     * @return The CarDealership object if found, null otherwise
     */
    public CarDealership getDealership(String name) {
        CarDealership foundDealership = null;
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLGETDEALERBYNAME);
            stmt.setString(1, name);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                name = resultado.getString("name_");
                String location = resultado.getString("location");
                int id = resultado.getInt("ID_CAR_DEALER");

                foundDealership = new CarDealership(name, location, id);
            }

            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return foundDealership;
    }
}