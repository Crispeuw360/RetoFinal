package modelo;

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

	final String SQLMODELS = "SELECT * FROM model WHERE ID_CAR_DEALER = ?";
	final String SQLCLIENTS = "SELECT * FROM client_";
	final String SQLSTOCK = "SELECT STOCK FROM model WHERE NAME_MODEL = ? AND ID_CAR_DEALER = ? ";


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

	//returns a list of the models of the current cardealership
	@Override
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

	//returns all the clients of all cardealerships
	@Override
	public Map<String, Client_> getClients_() {
		// TODO Auto-generated method stub

		ResultSet rs = null;
		Client_ client;
		Map<String, Client_> clientsList = new TreeMap<>();

		// Abrimos la conexi n
		this.openConnection();

		try {
			stmt = con.prepareStatement(SQLCLIENTS);

			rs = stmt.executeQuery();

			// Leemos de uno en uno
			while (rs.next()) {
				client = new Client_();
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

	//it calls the SQL procedure
	@Override
	public boolean callProcedure(Client_ client, Model model, Worker worker, LocalDate actualDate, int quantity) {
		// TODO Auto-generated method stub
		boolean ok=false;
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String wdate;

		//we give a format to the date to set as string
		wdate =actualDate.format(formateador);

		this.openConnection();

		try {
			CallableStatement stmt = con.prepareCall("{ CALL REGISTER_PURCHASE(?, ?, ?, ?, ?) }");

			// Parameters
			stmt.setString(1, client.getUser_());
			stmt.setString(2, model.getName_model());
			stmt.setInt(3, worker.getId_car_dealer());
			stmt.setString(4, wdate);
			stmt.setInt(5, quantity);

			if (stmt.executeUpdate()>0) { 
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

	//if the stock is more than 0 returns true, is used to manage the exception
	@Override
	public boolean checkStock(Model model) {
		// TODO Auto-generated method stub

		boolean stock=false;
		this.openConnection();


		try {
			stmt = con.prepareStatement(SQLSTOCK);
			stmt.setString(1, model.getName_model());
			stmt.setInt(2, model.getId_car_dealer());
			ResultSet result = stmt.executeQuery();

			//If there is stock, will return true
			if (result.next()) { 
				int stockValue = result.getInt("STOCK"); 
				if (stockValue > 0) {
					stock = true;
				}
			}


			result.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("Error al verificar credenciales: " + e.getMessage());
		}

		return stock;
	}






	/*@Override
		public boolean comprobarUsuario(Worker worker){
			// Abrimos la conexion
			boolean existe=false;
			this.openConnection();


			try {
				stmt = con.prepareStatement(SQL);
	            stmt.setString(1, usuario.getNombre());
	            stmt.setString(2, usuario.getContrasena());
	            ResultSet resultado = stmt.executeQuery();

	            //Si hay un resultado, el usuario existe
	            if (resultado.next()) {
	                existe = true;
	            }


	            resultado.close();
	            stmt.close();
	            con.close();

	        } catch (SQLException e) {
	            System.out.println("Error al verificar credenciales: " + e.getMessage());
	        }

	        return existe;
	    }*/

	/*@Override
		public boolean insertarUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			boolean ok=false;
			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente

				stmt = con.prepareStatement(SAQLINSERT);
				stmt.setString(1, usuario.getNombre());
				stmt.setString(2, usuario.getContrasena());
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

	/*public Map<String, Usuario> visualizarUsuarios() {
			// TODO Auto-generated method stub

			ResultSet rs = null;
			Usuario usuario;
			Map<String, Usuario> usuarios = new TreeMap<>();

			// Abrimos la conexi n
			this.openConnection();

			try {
				stmt = con.prepareStatement(SQLCONSULTA);

				rs = stmt.executeQuery();

				// Leemos de uno en uno
				while (rs.next()) {
					usuario = new Usuario();
					usuario.setNombre(rs.getString("nombre"));
					usuario.setContrasena(rs.getString("contrasena"));
					usuarios.put(usuario.getNombre(), usuario);

				}
				rs.close();
	            stmt.close();
	            con.close();
			} catch (SQLException e) {
				System.out.println("Error de SQL");
				e.printStackTrace();
			}
			return usuarios;

		}*/


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

	/*@Override
		public boolean modificarUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			boolean ok=false;

			this.openConnection();
			try {
				// Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente

				stmt = con.prepareStatement(SQLMODIFICAR);
				stmt.setString(1, usuario.getNombre());
				stmt.setString(2, usuario.getContrasena());
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




}






