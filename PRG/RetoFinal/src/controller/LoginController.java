package controller;

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

}
