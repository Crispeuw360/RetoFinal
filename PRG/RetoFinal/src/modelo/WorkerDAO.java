package modelo;

import java.util.Map;

public interface WorkerDAO {

	// public boolean comprobarWorker(Worker worker);
	/*
	 * public boolean insertarUsuario(Usuario usuario); public Map<String, Usuario>
	 * visualizarUsuarios(); public boolean eliminarUsuario(String nombre); public
	 * boolean modificarUsuario(Usuario usuario);
	 */

	Map<String, Worker> getCoWorkers(Worker worker);
	
	public boolean modifyModel(Model model) ;

	public CarDealership getWorkingPlace(Worker worker);

	public boolean deleteModel(Model model);

	public Worker checkWorker(Worker worker);

	public boolean createModel(Model model);

	Map<String, CarDealership> getCarDealerships();

	Map<String, Model> getModels(CarDealership cardealer);
	
	public CarDealership getDealership(String name);

}
