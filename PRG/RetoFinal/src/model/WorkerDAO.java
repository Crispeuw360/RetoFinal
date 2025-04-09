package model;

import java.time.LocalDate;
import java.util.Map;

public interface WorkerDAO {

	// public boolean comprobarWorker(Worker worker);
	/*
	 * public boolean insertarUsuario(Usuario usuario); public Map<String, Usuario>
	 * visualizarUsuarios(); public boolean eliminarUsuario(String nombre); public
	 * boolean modificarUsuario(Usuario usuario);
	 */

	public Map<String, Model> getModels(CarDealership cardealer);

	public Map<String, Worker> getWorkers();

	public Map<String, Worker> getCoWorkers(Worker worker);

	public CarDealership getWorkingPlace(Worker worker);

	public CarDealership getDealership(String name);

	Map<String, CarDealership> getCarDealerships();

	Map<String, CarDealership> getAllDeals();

	public boolean deleteModel(Model model);

	public Worker checkWorker(Worker worker);

	public boolean modifyWorker(Worker worker);

	Worker getWorker(String worker);

	public boolean deleteWorker(Worker worker);

	public boolean createWorker(Worker worker);

	public Map<String, Client_> getClients_();

	public boolean callProcedure(Client_ client, Model model, Worker worker, LocalDate actualDate, int quantity);

	public int checkStock(Model model);

	public Map<String, Model> getModels(Worker worker);

	public boolean modifyModel(Model model);

	public boolean insertClient(Client_ client);

}
