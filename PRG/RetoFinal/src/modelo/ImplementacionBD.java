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

	final String SQL = "SELECT * FROM usuario WHERE nombre = ? AND contrasena = ?";
	final String SAQLINSERT = "INSERT INTO usuario VALUES ( ?,?)";
	final String SQLCONSULTA = "SELECT * FROM model";
	final String SQLBORRAR = "DELETE FROM usuario WHERE nombre=?";
	final String SQLMODIFICAR = "UPDATE usuario SET NOMBRE = ? AND contranea = ?";

	final String SQLGETDEALER = " SELECT * FROM car_dealership WHERE id_car_dealer = ?";

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

	public Map<String, Model> getModels(CarDealership cardealer) {

		// TODO Auto-generated method stub

		ResultSet rs = null;

		Model model;

		Map<String, Model> models = new TreeMap<>();

		// Abrimos la conexi n

		this.openConnection();

		try {

			stmt = con.prepareStatement(SQLCONSULTA);

			rs = stmt.executeQuery();

			// Leemos de uno en uno

			while (rs.next()) {

				model = new Model();

				model.setId_car_dealer(rs.getInt("id_car_dealer"));

				model.setMark(rs.getString("mark"));

				model.setName_model(rs.getString("name_model"));

				model.setPrice(rs.getDouble("price"));

				model.setStock(rs.getInt("stock"));

				if (model.getId_car_dealer() == cardealer.getId())

				{

					models.put(model.getName_model(), model);

				}

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

	/*
	 * @Override public boolean comprobarUsuario(Worker worker){ // Abrimos la
	 * conexion boolean existe=false; this.openConnection();
	 * 
	 * 
	 * try { stmt = con.prepareStatement(SQL); stmt.setString(1,
	 * usuario.getNombre()); stmt.setString(2, usuario.getContrasena()); ResultSet
	 * resultado = stmt.executeQuery();
	 * 
	 * //Si hay un resultado, el usuario existe if (resultado.next()) { existe =
	 * true; }
	 * 
	 * 
	 * resultado.close(); stmt.close(); con.close();
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Error al verificar credenciales: " + e.getMessage()); }
	 * 
	 * return existe; }
	 */

	/*
	 * @Override public boolean insertarUsuario(Usuario usuario) { // TODO
	 * Auto-generated method stub boolean ok=false; this.openConnection(); try { //
	 * Preparamos la sentencia stmt con la conexion y sentencia sql correspondiente
	 * 
	 * stmt = con.prepareStatement(SAQLINSERT); stmt.setString(1,
	 * usuario.getNombre()); stmt.setString(2, usuario.getContrasena()); if
	 * (stmt.executeUpdate()>0) { ok=true; }
	 * 
	 * stmt.close(); con.close(); } catch (SQLException e) {
	 * System.out.println("Error al verificar credenciales: " + e.getMessage()); }
	 * return ok;
	 * 
	 * }
	 */

	/*
	 * public Map<String, Usuario> visualizarUsuarios() { // TODO Auto-generated
	 * method stub
	 * 
	 * ResultSet rs = null; Usuario usuario; Map<String, Usuario> usuarios = new
	 * TreeMap<>();
	 * 
	 * // Abrimos la conexi n this.openConnection();
	 * 
	 * try { stmt = con.prepareStatement(SQLCONSULTA);
	 * 
	 * rs = stmt.executeQuery();
	 * 
	 * // Leemos de uno en uno while (rs.next()) { usuario = new Usuario();
	 * usuario.setNombre(rs.getString("nombre"));
	 * usuario.setContrasena(rs.getString("contrasena"));
	 * usuarios.put(usuario.getNombre(), usuario);
	 * 
	 * } rs.close(); stmt.close(); con.close(); } catch (SQLException e) {
	 * System.out.println("Error de SQL"); e.printStackTrace(); } return usuarios;
	 * 
	 * }
	 */

	/*
	 * @Override public boolean eliminarUsuario(String nombre) { // TODO
	 * Auto-generated method stub boolean ok=false;
	 * 
	 * this.openConnection(); try { // Preparamos la sentencia stmt con la conexion
	 * y sentencia sql correspondiente
	 * 
	 * stmt = con.prepareStatement(SQLBORRAR); stmt.setString(1, nombre); if
	 * (stmt.executeUpdate()>0) { ok=true; }
	 * 
	 * stmt.close(); con.close(); } catch (SQLException e) {
	 * System.out.println("Error al verificar credenciales: " + e.getMessage()); }
	 * 
	 * return ok; }
	 */

	/*
	 * @Override public boolean modificarUsuario(Usuario usuario) { // TODO
	 * Auto-generated method stub boolean ok=false;
	 * 
	 * this.openConnection(); try { // Preparamos la sentencia stmt con la conexion
	 * y sentencia sql correspondiente
	 * 
	 * stmt = con.prepareStatement(SQLMODIFICAR); stmt.setString(1,
	 * usuario.getNombre()); stmt.setString(2, usuario.getContrasena()); if
	 * (stmt.executeUpdate()>0) { ok=true; }
	 * 
	 * stmt.close(); con.close(); } catch (SQLException e) {
	 * System.out.println("Error al verificar credenciales: " + e.getMessage()); }
	 * 
	 * return ok; }
	 */

}
