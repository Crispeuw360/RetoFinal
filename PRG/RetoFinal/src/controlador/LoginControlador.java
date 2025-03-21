package controlador;

import java.util.Map;
import modelo.*;
import vista.VentanaPrincipal;

public class LoginControlador {

	WorkerDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {

		Worker worker = new Worker(true, "Alex", "1234", 1);

		VentanaPrincipal ven = new VentanaPrincipal(this, worker);
		ven.setVisible(true);
	}

	/*
	 * public boolean comprobarUsuario(Usuario usuario){ return
	 * dao.comprobarUsuario(usuario); } public boolean insertarUsuario(Usuario
	 * usuario) { return dao.insertarUsuario(usuario); }
	 * 
	 * 
	 * 
	 * public boolean eliminarUsuario(String nombre) { return
	 * dao.eliminarUsuario(nombre);
	 * 
	 * }
	 * 
	 * public boolean modificarUsuario(Usuario usuario) { return
	 * dao.modificarUsuario(usuario);
	 * 
	 * }
	 */
	public Map<String, Model> getModels(CarDealership cardealer) {
		return dao.getModels(cardealer);
	}
	
	public CarDealership getWorkingPlace(Worker worker) {
		return dao.getWorkingPlace(worker);
	}

}
