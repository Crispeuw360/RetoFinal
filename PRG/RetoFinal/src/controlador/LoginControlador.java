package controlador;

import java.time.LocalDate;
import java.util.Map;
import modelo.*;
import vista.VentanaVender;

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

	public void openWindow(CarDealership cardealer) {
		VentanaVender ven = new VentanaVender(cardealer, this);
		ven.setVisible(true);
	}
	
	public Map<String, Model> getModels(Worker worker) {
		return dao.getModels(worker);
		
	}
	
	public Map<String, Client_> getClients() {
		return dao.getClients_();
		
	}
	
	public boolean callProcedure(Client_ client, Model model, Worker worker, LocalDate actualDate, int quantity) {
		return dao.callProcedure(client, model, worker, actualDate, quantity);
	}
	
	public boolean checkStock(Model model) {
		return dao.checkStock(model);
	}
	
}
