package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ImplementacionBD implements WorkerDAO {
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

	// Sentencias SQL

	final String SQLINSERT = "INSERT INTO model VALUES (?,?,?,?,?)";

	final String SQLLOGIN = "SELECT * FROM worker WHERE user_ = ? AND password_ = ?";
	final String SQLGETWORKER = "SELECT * FROM worker WHERE user_ = ?";

	// dej
	final String SQLMODIFICARMODEL = "UPDATE MODEL SET MARK = ?, STOCK = ?, PRICE = ? WHERE NAME_MODEL = ? AND ID_CAR_DEALER = ?";
	final String SQLGETMODELS = "SELECT * FROM model WHERE id_car_dealer = ?";
	final String SQLGETWORKERS = " SELECT * FROM worker WHERE id_car_dealer = ?";
	final String SQLGETDEALER = " SELECT * FROM car_dealership WHERE id_car_dealer = ?";
	final String SQLGETDEALERBYNAME = " SELECT * FROM car_dealership WHERE name_ = ?";
	final String SQLALLDEALERS = "SELECT * FROM car_dealership";
	final String SQLMODELS = "DELETE FROM model WHERE name_model = ?";


	final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	final String SQLMODIFICAR = "UPDATE usuario SET NOMBRE = ? AND contranea = ?";

	// Para la conexi n utilizamos un fichero de configuaraci n, config que
	// guardamos en el paquete control:
	public ImplementacionBD() {
		this.configFile = ResourceBundle.getBundle("modelo.configClase");
		this.driverBD = this.configFile.getString("Driver");
		this.urlBD = this.configFile.getString("Conn");
		this.userBD = this.configFile.getString("DBUser");
		this.passwordBD = this.configFile.getString("DBPass");
	}

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

	@Override

	public Map<String, Worker> getCoWorkers(Worker worker) {

	    // TODO Auto-generated method stub
	    ResultSet rs = null;
	    Map<String, Worker> workers = new TreeMap<>();

	    // Open database connection
	    this.openConnection();

	    try {
	        // Prepare SQL query to get workers
	        stmt = con.prepareStatement(SQLGETWORKERS);
	        // Set the car dealership ID parameter
	        stmt.setInt(1, worker.getId_car_dealer());
	        // Execute the query
	        rs = stmt.executeQuery();

	        // Process results one by one
	        while (rs.next()) {
	            // Create new Worker object
	            worker = new Worker();

	            // Set worker properties from query results
	            worker.setAdmin(rs.getBoolean("admin_"));
	            worker.setUser(rs.getString("user_"));
	            worker.setPassword(rs.getString("password_"));
	            worker.setId_car_dealer(rs.getInt("id_car_dealer"));

	            // Add worker to the map using username as key
	            workers.put(worker.getUser(), worker);
	        }

	        // Close resources
	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (SQLException e) {
	        // Handle SQL errors
	        System.out.println("Error de SQL");
	        e.printStackTrace();
	    }

	    // Return the map of workers
	    return workers;
	}
	public Map<String, Model> getModels(CarDealership cardealer) {
	    ResultSet rs = null;
	    Model model;
	    Map<String, Model> models = new TreeMap<>();

	    // Open database connection
	    this.openConnection();

	    try {
	        // Prepare SQL query to get models
	        stmt = con.prepareStatement(SQLGETMODELS);
	        // Set the car dealership ID parameter
	        stmt.setInt(1, cardealer.getId());
	        // Execute the query
	        rs = stmt.executeQuery();

	        // Process results one by one
	        while (rs.next()) {
	            // Create new Model object
	            model = new Model();

	            // Set model properties from query results
	            model.setId_car_dealer(rs.getInt("id_car_dealer"));
	            model.setMark(rs.getString("mark"));
	            model.setName_model(rs.getString("name_model"));
	            model.setPrice(rs.getDouble("price"));
	            model.setStock(rs.getInt("stock"));

	            // Add model to the map using name_model as key
	            models.put(model.getName_model(), model);
	        }

	        // Close resources
	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (SQLException e) {
	        // Handle SQL errors
	        System.out.println("Error de SQL");
	        e.printStackTrace();
	    }
	    // Return the map of models
	    return models;
	}
	public Map<String, CarDealership> getCarDealerships() {
	    Map<String, CarDealership> dealerships = new TreeMap<>();
	    ResultSet rs = null;
	    
	    this.openConnection();
	    
	    try {
	        stmt = con.prepareStatement(SQLALLDEALERS);
	        rs = stmt.executeQuery();
	        
	        while (rs.next()) {
	            CarDealership dealer = new CarDealership();
	            dealer.setName(rs.getString("NAME_"));
	            dealer.setLocation(rs.getString("LOCATION"));
	            dealer.setId(rs.getInt("ID_CAR_DEALER"));
	            
	            // Usamos el nombre como clave en el Map
	            dealerships.put(dealer.getName(), dealer);
	        }
	        
	    } catch (SQLException e) {
	        System.out.println("Error al obtener concesionarios: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        // Cerrar recursos en bloque finally
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar recursos: " + e.getMessage());
	        }
	    }
	    
	    return dealerships;
	}

	
	public CarDealership getWorkingPlace(Worker worker) {

	    CarDealership cardealer = null;
	    ResultSet rs = null;
	    
	    this.openConnection(); // Open database connection

	    try {
	        // Prepare SQL query to get car dealership information
	        stmt = con.prepareStatement(SQLGETDEALER);
	        // Set the car dealer ID parameter
	        stmt.setInt(1, worker.getId_car_dealer());
	        // Execute the query
	        rs = stmt.executeQuery();

	        // Process results one by one
	        while (rs.next()) {
	            // Create new CarDealership object
	            cardealer = new CarDealership();
	            
	            // Set dealership properties from query results
	            cardealer.setId(rs.getInt("id_car_dealer"));
	            cardealer.setLocation(rs.getString("location"));
	            cardealer.setName(rs.getString("name_"));
	        }

	        // Close resources
	        rs.close();
	        stmt.close();
	        con.close();

	    } catch (SQLException e) {
	        // Handle SQL errors
	        System.out.println("Error de SQL");
	        e.printStackTrace();
	    }

	    // Return the found car dealership (or null if not found)
	    return cardealer;
	}

	public boolean deleteModel(Model model) {
		boolean ok = false;
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLMODELS);
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

	public boolean modifyModel(Model model) {
		// TODO Auto-generated method stub
		boolean ok=false;

		this.openConnection();
		try {
			// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente

			stmt = con.prepareStatement(SQLMODIFICARMODEL);
			stmt.setString(1, model.getMark());
			stmt.setInt(2, model.getStock());
			stmt.setDouble(3, model.getPrice());
			stmt.setString(4, model.getName_model());
			stmt.setInt(5, model.getId_car_dealer());
			if (stmt.executeUpdate()>0) {
				ok=true;
			}

			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return ok;
	}
	
	
	public boolean createModel(Model model) {  
	    boolean creado = false;  
	    this.openConnection(); // Open database connection  

	    try {  
	        stmt = con.prepareStatement(SQLINSERT); // Prepare the stmt statement with the connection and corresponding SQL query  
	        stmt.setString(1, model.getName_model());  
	        stmt.setString(2, model.getMark());  
	        stmt.setInt(3, model.getStock());  
	        stmt.setDouble(4, model.getPrice());  
	        stmt.setInt(5, model.getId_car_dealer());  

	        if (stmt.executeUpdate() > 0) { // If executed successfully, return true  
	            creado = true;  
	        }  
	        stmt.close();  
	        con.close();  
	    } catch (SQLException e) {  
	        System.out.println("Error al insertar el modelo: " + e.getMessage());  
	    }  
	    return creado;  
	}  
	
	public Worker checkWorker(Worker worker) {
	    Worker foundWorker = null; 
	    this.openConnection(); // Open the database connection

	    try {
	        // Prepare the SQL query
	        stmt = con.prepareStatement(SQLLOGIN);
	        stmt.setString(1, worker.getUser()); 
	        stmt.setString(2, worker.getPassword()); 
	        ResultSet resultado = stmt.executeQuery(); // Execute the query

	        // If there is a result, the user exists
	        if (resultado.next()) {
	            // Get the user data from the database
	            boolean esAdmin = resultado.getBoolean("ADMIN_"); 
	            String usuario = resultado.getString("USER_"); 
	            String contraseña = resultado.getString("PASSWORD_"); 
	            int idCarDealer = resultado.getInt("ID_CAR_DEALER"); 

	            // Create a new Worker object with the retrieved data
	            foundWorker = new Worker(esAdmin, usuario, contraseña, idCarDealer);
	        }

	        stmt.close();
	        con.close();
	    } catch (SQLException e) {
	        System.out.println("Error al verificar credenciales: " + e.getMessage());
	    }
	    return foundWorker;
	}
	public CarDealership getDealership(String name) {
	    CarDealership foundDealership = null;
	    this.openConnection();

	    try {
	        // Preparamos la consulta SQL
	        stmt = con.prepareStatement(SQLGETDEALERBYNAME);
	        stmt.setString(1, name);
	        ResultSet resultado = stmt.executeQuery();

	        if (resultado.next()) {
	            name = resultado.getString("name_");
	            String location = resultado.getString("location");
	            int id = resultado.getInt("ID_CAR_DEALER");  // Changed to match actual column name

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
