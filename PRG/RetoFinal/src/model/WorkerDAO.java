package model;

import java.util.Map;

public interface WorkerDAO {

	// public boolean comprobarWorker(Worker worker);
	/*
	 * public boolean insertarUsuario(Usuario usuario); public Map<String, Usuario>
	 * visualizarUsuarios(); public boolean eliminarUsuario(String nombre); public
	 * boolean modificarUsuario(Usuario usuario);
	 */

	Map<String, Model> getModels(CarDealership cardealer);

	Map<String, Worker> getWorkers();

	Map<String, Worker> getCoWorkers(Worker worker);

	public CarDealership getWorkingPlace(Worker worker);

	Map<String, CarDealership> getAllDeals();

	public boolean deleteModel(Model model);

	public Worker checkWorker(Worker worker);

	public boolean modifyWorker(Worker worker);

	Worker getWorker(String worker);

	public boolean deleteWorker(Worker worker);

	public boolean createWorker(Worker worker);

}
