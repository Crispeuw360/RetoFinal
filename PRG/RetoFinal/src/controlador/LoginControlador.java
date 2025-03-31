package controlador;

import java.util.Map;
import modelo.*;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class LoginControlador {

	WorkerDAO dao = new ImplementacionBD();

	public void visualizarPantalla() {

	

		VentanaLogin ven = new VentanaLogin(this);
		ven.setVisible(true);
	}

	public Map<String, CarDealership> getCarDealerships(){
		return dao.getCarDealerships();
	}
	
	public Map<String, Worker> getCoWorkers(Worker worker) {
		return dao.getCoWorkers(worker);
	}

	public CarDealership getWorkingPlace(Worker worker) {
		return dao.getWorkingPlace(worker);
	}

	public boolean deleteModel(Model model) {
		return dao.deleteModel(model);
	}

	public Worker checkWorker(Worker worker) {

		return dao.checkWorker(worker);
	}
	public boolean createModel(Model model) {
		return dao.createModel(model);
	}


	public Map<String, Model> getModels(CarDealership cardealer) {
		return dao.getModels(cardealer);
	}
	
	public CarDealership getDealership(String name) {
        return dao.getDealership(name);
    }

	public boolean modifyModel(Model model) {
		return dao.modifyModel(model);
	}

}
