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

	final String SAQLINSERT = "INSERT INTO usuario VALUES ( ?,?)";

	final String SQLLOGIN = "SELECT * FROM worker WHERE user_ = ? AND password_ = ?";

	// dej
	final String SQLGETMODELS = "SELECT * FROM model WHERE id_car_dealer = ?";
	final String SQLGETWORKERS = " SELECT * FROM worker WHERE id_car_dealer = ?";
	final String SQLGETDEALER = " SELECT * FROM car_dealership WHERE id_car_dealer = ?";

	final String SQLDELETEMODEL = "DELETE FROM model WHERE name_model = ?";

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

		// Abrimos la conexi n

		this.openConnection();

		try {

			stmt = con.prepareStatement(SQLGETWORKERS);
			stmt.setInt(1, worker.getId_car_dealer());
			rs = stmt.executeQuery();

			// Leemos de uno en uno

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

			System.out.println("Error de SQL");

			e.printStackTrace();

		}

		return workers;

	}

	public Map<String, Model> getModels(CarDealership cardealer) {
		ResultSet rs = null;
		Model model;
		Map<String, Model> models = new TreeMap<>();

		// Open connection

		this.openConnection();

		try {
			stmt = con.prepareStatement(SQLGETMODELS);
			stmt.setInt(1, cardealer.getId());
			rs = stmt.executeQuery();

			// Leemos de uno en uno

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

	public CarDealership getWorkingPlace(Worker worker) {

		CarDealership cardealer = null;

		ResultSet rs = null;

		this.openConnection();

		try {

			stmt = con.prepareStatement(SQLGETDEALER);
			stmt.setInt(1, worker.getId_car_dealer());
			rs = stmt.executeQuery();

			// Leemos de uno en uno

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

			System.out.println("Error de SQL");

			e.printStackTrace();

		}

		return cardealer;

	}

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
	/*
	 * public boolean deleteWorker(Worker worker) { boolean ok = false;
	 * 
	 * this.openConnection();
	 * 
	 * 
	 * try { smtm =con.prepareCall(SQLDELETEWORKER)
	 * 
	 * }
	 * 
	 * return ok;
	 * 
	 * 
	 * }
	 */

	public Worker checkWorker(Worker worker) {
		Worker foundWorker = null; // Inicializamos como null
		this.openConnection(); // Abrimos la conexión a la base de datos

		try {
			// Preparamos la consulta SQL
			stmt = con.prepareStatement(SQLLOGIN);
			stmt.setString(1, worker.getUser()); // Establecemos el nombre de usuario
			stmt.setString(2, worker.getPassword()); // Establecemos la contraseña
			ResultSet resultado = stmt.executeQuery(); // Ejecutamos la consulta

			// Si hay un resultado, el usuario existe
			if (resultado.next()) {
				// Obtenemos los datos del usuario de la base de datos
				boolean esAdmin = resultado.getBoolean("ADMIN_"); // Campo que indica si es administrador
				String usuario = resultado.getString("USER_"); // Nombre de usuario
				String contraseña = resultado.getString("PASSWORD_"); // Contraseña
				int idCarDealer = resultado.getInt("ID_CAR_DEALER"); // Identificador de concesionario

				// Creamos un nuevo objeto Worker con los datos obtenidos
				foundWorker = new Worker(esAdmin, usuario, contraseña, idCarDealer);
			}

		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		} finally {
			// Cerramos los recursos en un bloque finally
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar recursos: " + e.getMessage());
			}
		}

		return foundWorker; // Devolvemos el Worker encontrado (o null si no existe)
	}
}
