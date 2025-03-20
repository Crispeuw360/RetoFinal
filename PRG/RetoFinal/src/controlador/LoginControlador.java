package controlador;

import java.util.Map;
import modelo.*;
import vista.VentanaPrincipal;

public class LoginControlador {

	WorkerDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {
		VentanaPrincipal ven = new VentanaPrincipal(this);
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
	public Map<String, Model> visualizeModels(CarDealership cardealer) {
		return dao.visualizeModels(cardealer);
	}
}
