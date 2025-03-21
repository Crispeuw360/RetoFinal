package modelo;

import java.util.Map;

public interface WorkerDAO {

	public boolean comprobarWorker(Worker worker);
	/*
	 * public boolean insertarUsuario(Usuario usuario); public Map<String, Usuario>
	 * visualizarUsuarios(); public boolean eliminarUsuario(String nombre); public
	 * boolean modificarUsuario(Usuario usuario);
	 */

	Map<String, Model> getModels(CarDealership cardealer);

	public CarDealership getWorkingPlace(Worker worker);
}
