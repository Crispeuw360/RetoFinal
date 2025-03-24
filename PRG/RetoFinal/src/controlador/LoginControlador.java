package controlador;

import java.util.Map;
import modelo.*;
import vista.VentanaModificar;

public class LoginControlador {

	WorkerDAO dao = new ImplementacionBD();

	public Map<String, Model> getModels(CarDealership cardealer) {
		return dao.getModels(cardealer);
	}
	public Map<String, Client> getClients(){
		return dao.getClients();
	}
	public void visualizarPantalla() {

		CarDealership car = new CarDealership("AutoMadrid","Madrid",1);
		Worker work = new Worker("juan23","pass123",true,1);

		VentanaModificar ven = new VentanaModificar(car,this,work);
		ven.setVisible(true);
	}
	public boolean modifyModel(Model model) 
	{
		return dao.modifyModel(model);
	}
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
	
	
	
	public boolean eliminarUsuario(String nombre) {
		return dao.eliminarUsuario(nombre);
		
	}
	
	public boolean modificarUsuario(Usuario usuario) {
		return dao.modificarUsuario(usuario);
		
	}*/
	
}
