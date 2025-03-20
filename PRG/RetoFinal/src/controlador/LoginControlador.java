package controlador;

import java.util.Map;
import modelo.*;

public class LoginControlador {

	WorkerDAO dao = new ImplementacionBD();

	
	/*public void visualizarPantalla() {
		VentanaLogin ven = new VentanaLogin(this);
		ven.setVisible(true);	
	}
	public boolean comprobarUsuario(Usuario usuario){
		return dao.comprobarUsuario(usuario);	
	}
	public boolean insertarUsuario(Usuario usuario) {
		return dao.insertarUsuario(usuario);
	}
	
	public Map<String, Usuario> visualizarUsuarios() {
		return dao.visualizarUsuarios();
	}
	
	public boolean eliminarUsuario(String nombre) {
		return dao.eliminarUsuario(nombre);
		
	}
	
	public boolean modificarUsuario(Usuario usuario) {
		return dao.modificarUsuario(usuario);
		
	}*/

	public Map<String, Model> getModels(CarDealership cardealer) {
		return dao.getModels(cardealer);
		
	}
	
	public Map<String, Client_> getClients() {
		return dao.getClients_();
		
	}
	
	
}
