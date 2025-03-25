package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class ImplementacionBD implements WorkerDAO{
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

	final String SQL = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";
	final String SQLINSERTCLIENT = "INSERT INTO client_ VALUES ( ?,?,?,?)";
	final String SQLCONSULTA = "SELECT * FROM usuario";
	final String SQLMODELS = "SELECT * FROM model WHERE ID_CAR_DEALER = ?";
	final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	final String SQLMODIFICARMODEL = "UPDATE MODEL SET MARK = ?, STOCK = ?, PRICE = ? WHERE NAME_MODEL = ? AND ID_CAR_DEALER = ?";
	final String SQLCLIENTS = "SELECT * FROM client_";


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
	public Map<String, Client> getClients() {
		// TODO Auto-generated method stub

		ResultSet rs = null;
		Client client;
		Map<String, Client> clientsList = new TreeMap<>();

		// Abrimos la conexi n
		this.openConnection();

		try {
			stmt = con.prepareStatement(SQLCLIENTS);

			rs = stmt.executeQuery();

			// Leemos de uno en uno
			while (rs.next()) {
				client = new Client();
				client.setDni(rs.getString("dni"));
				client.setEmail(rs.getString("email"));
				client.setUser_(rs.getString("user_"));
				client.setPassword_(rs.getString("password_"));
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

	@Override
		public boolean insertClient(Client client) {
			// TODO Auto-generated method stub
			boolean ok=false;
			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente

				stmt = con.prepareStatement(SQLINSERTCLIENT);
				stmt.setString(1, client.getDni());
				stmt.setString(2, client.getEmail());
				stmt.setString(3, client.getUser_());
				stmt.setString(4, client.getPassword_());
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

	public Map<String, Model> getModels(Worker worker) 
	{
		// TODO Auto-generated method stub

		ResultSet rs = null;
		Model model;
		Map<String, Model> models = new TreeMap<>();
		// Abrimos la conexi n
		this.openConnection();
		try {
			stmt = con.prepareStatement(SQLMODELS);
			stmt.setInt(1, worker.getId_car_dealer());
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


	/*@Override
		public boolean eliminarUsuario(String nombre) {
			// TODO Auto-generated method stub
			boolean ok=false;

			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente

				stmt = con.prepareStatement(SQLBORRAR);
				stmt.setString(1, nombre);
				if (stmt.executeUpdate()>0) {
					ok=true;
				}

	            stmt.close();
	            con.close();
			  } catch (SQLException e) {
	             System.out.println("Error al verificar credenciales: " + e.getMessage());
	        }

			return ok;
		}*/

	@Override
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




}






