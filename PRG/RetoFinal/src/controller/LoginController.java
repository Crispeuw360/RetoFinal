package controller;

import java.time.LocalDate;
import java.util.Map;

import model.*;
import view.WindowLogin;
import view.WindowMain;

public class LoginController {

	WorkerDAO dao = new ImplementsBD();

	public void visualizarPantalla() {

		WindowLogin ven = new WindowLogin(this);
		ven.setVisible(true);
	}

	public Map<String, Model> getModels(CarDealership cardealer) {
		return dao.getModels(cardealer);
	}

	public Map<String, Model> getModels(Worker worker) {
		return dao.getModels(worker);

	}

	public Map<String, Worker> getWorkers() {
		return dao.getWorkers();
	}

	public Map<String, Worker> getCoWorkers(Worker worker) {
		return dao.getCoWorkers(worker);
	}

	public CarDealership getWorkingPlace(Worker worker) {
		return dao.getWorkingPlace(worker);
	}

	public Map<String, CarDealership> getAllDeals() {
		return dao.getAllDeals();
	}

	public boolean deleteModel(Model model) {
		return dao.deleteModel(model);
	}

	public Worker checkWorker(Worker worker) {

		return dao.checkWorker(worker);
	}

	public boolean modifyWorker(Worker worker) {
		return dao.modifyWorker(worker);

	}

	public Worker getWorker(String worker) {
		return dao.getWorker(worker);

	}

	public boolean deleteWorker(Worker worker) {
		return dao.deleteWorker(worker);
	}

	public boolean createWorker(Worker worker) {
		return dao.createWorker(worker);
	}
	public Map<String, Client> getClients() {
		return dao.getClients_();

	}

	public boolean callProcedure(Client client, Model model, Worker worker, LocalDate actualDate, int quantity) {
		return dao.callProcedure(client, model, worker, actualDate, quantity);
	}

	public int checkStock(Model model) {
		return dao.checkStock(model);
	}

	public boolean modifyModel(Model model) {
		return dao.modifyModel(model);
	}

	public boolean insertClient(Client client) {
		return dao.insertClient(client);
	}

	public CarDealership getDealership(String name) {
		return dao.getDealership(name);
	}
	
	public Map<String, CarDealership> getCarDealerships(){
		return dao.getCarDealerships();
	}
	
	public boolean createModel(Model model) {
		return dao.createModel(model);
	}

	public Model getModel(String name_model) {
		
		return dao.getModel(name_model);
	}
	

}
